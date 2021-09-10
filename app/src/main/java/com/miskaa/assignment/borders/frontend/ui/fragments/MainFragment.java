package com.miskaa.assignment.borders.frontend.ui.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miskaa.assignment.borders.R;
import com.miskaa.assignment.borders.base.StateData;
import com.miskaa.assignment.borders.frontend.ui.base.BaseFragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends BaseFragment {

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getAct().hideToolBar();
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ((Toolbar)v.findViewById(R.id.menubar)).setOnMenuItemClickListener((item) -> {
            if(Objects.requireNonNull(getAct().getViewModel().getCountries().getValue()).getStatus() != StateData.DataStatus.LOADING){
                if (item.getItemId() == R.id.menu_delete) {
                    getAct().getViewModel().deleteData();
                }else{
                    Toast.makeText(this.getContext(), "Not implemented yet", Toast.LENGTH_LONG).show();
                }
            }
            return true;
        });
        return v;
    }
}