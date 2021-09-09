package com.miskaa.assignment.borders.frontend.ui.base;

import androidx.fragment.app.Fragment;

import com.miskaa.assignment.borders.MainActivity;

public class BaseFragment extends Fragment {
    public MainActivity getAct() {
        return (MainActivity) getActivity();
    }
}
