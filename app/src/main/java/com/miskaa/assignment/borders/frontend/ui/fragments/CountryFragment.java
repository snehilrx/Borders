package com.miskaa.assignment.borders.frontend.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miskaa.assignment.borders.MainActivity;
import com.miskaa.assignment.borders.R;
import com.miskaa.assignment.borders.frontend.BordersViewModel;
import com.miskaa.assignment.borders.frontend.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class CountryFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private MyCountryRecyclerViewAdapter mAdapter = null;

    private ProgressDialog mProgressDialog = null;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CountryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CountryFragment newInstance(int columnCount) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BordersViewModel vm = getAct().getViewModel();
        if(vm == null) return;
        vm.getCountries().observe(this.getViewLifecycleOwner(), (state) -> {
            switch (state.getStatus()){
                case CREATED:
                case LOADING:
                    mProgressDialog = ProgressDialog.show(getActivity(), "",
                            "Loading. Please wait...", true);
                    break;
                case SUCCESS:
                case COMPLETE:
                    if(mProgressDialog != null)
                        mProgressDialog.dismiss();
                    assert state.getData() != null;
                    mAdapter.update(state.getData());
                    break;
                case ERROR:
                    assert state.getError() != null;
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Failed to get list")
                            .setMessage(state.getError().getMessage())
                            .setPositiveButton("Retry", (dialog, which) -> {

                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);

        View list = view.findViewById(R.id.list);
        // Set the adapter
        if (list instanceof RecyclerView) {
            Context context = list.getContext();
            RecyclerView recyclerView = (RecyclerView) list;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mAdapter = new MyCountryRecyclerViewAdapter(new ArrayList<>(), (root) -> {
                getAct().gotoDetails(root);
            });
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(mAdapter);
        }

        return view;
    }

}