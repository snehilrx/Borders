package com.miskaa.assignment.borders.frontend.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.miskaa.assignment.borders.R;
import com.miskaa.assignment.borders.backend.model.Language;
import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.databinding.FragmentDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentDetailsBinding mBindings;

    public Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Details.
     */
    // TODO: Rename and change types and number of parameters
    public static Details newInstance(String param1, String param2) {
        Details fragment = new Details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBindings = FragmentDetailsBinding.inflate(inflater, container, false);
        Root root = DetailsArgs.fromBundle(getArguments()).getCountry();
        mBindings.setD(root);
        Context context = mBindings.getRoot().getContext();
        for(String borders : root.getBorders()){
            addNewChip(borders, mBindings.chipGroup2);
        }
        for(Language language : root.getLanguages()){
            addNewChip(language.getNativeName(), mBindings.chipGroup1);
        }
        GlideToVectorYou.init().with(context).load(Uri.parse(root.getFlag()), mBindings.flag);
        return mBindings.getRoot();
    }

    private void addNewChip(String borders, @NonNull ChipGroup to) {
        Chip chip = new Chip(to.getContext());
        chip.setText(borders);
        chip.setClickable(true);
        int lr = dp(16);
        int tb = dp(8);
        chip.setPadding(lr,tb,lr,tb);

        chip.setId(View.generateViewId());
        to.addView(chip);
    }

    private int dp(int sizeInDp){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (sizeInDp*scale + 0.5f);
    }
}