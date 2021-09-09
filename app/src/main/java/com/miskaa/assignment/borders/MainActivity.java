package com.miskaa.assignment.borders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.databinding.ActivityMainBinding;
import com.miskaa.assignment.borders.frontend.BordersViewModel;
import com.miskaa.assignment.borders.frontend.ui.fragments.CountryFragment;
import com.miskaa.assignment.borders.frontend.ui.fragments.MainFragment;
import com.miskaa.assignment.borders.frontend.ui.fragments.MainFragmentDirections;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Borders/Activity";

    private BordersViewModel mViewModel = null;
    private ActivityMainBinding mBinding = null;
    private NavController mController = null;
    private View mContainer;

    public BordersApplication getApp(){
        return (BordersApplication) getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelMaker<BordersViewModel>(){
            @NonNull
            @Override
            public BordersViewModel newInstance() {
                return new BordersViewModel(getApp().service);
            }
        }).get(BordersViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mContainer = findViewById(R.id.mainAppContainer);
        mController = Navigation.findNavController(mContainer);

        setupActionBar();
    }

    private void setupActionBar() {
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(mController.getGraph()).build();
        NavigationUI.setupWithNavController(
                mBinding.mainToolbar, mController, appBarConfiguration);
    }

    /** @return BordersViewModel or null value */
    public BordersViewModel getViewModel(){
        return mViewModel;
    }

    public void hideToolBar(){
        if(mBinding != null) {
            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) mContainer.getLayoutParams();
            params.setBehavior(null);
            mContainer.requestLayout();
            mBinding.mainToolbar.setVisibility(View.GONE);
            mBinding.mainToolbarLayout.setVisibility(View.GONE);
        }
    }

    public void showToolBar(String name){
        if(mBinding != null) {
            mBinding.mainToolbarLayout.setVisibility(View.VISIBLE);
            mBinding.mainToolbar.setVisibility(View.VISIBLE);
            mBinding.mainToolbar.setTitle(name);
            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) mContainer.getLayoutParams();
            params.setBehavior(new AppBarLayout.ScrollingViewBehavior());
            mContainer.requestLayout();
        }
    }

    public void gotoDetails(Root root) {
        Navigation.findNavController(findViewById(R.id.mainAppContainer)).navigate(MainFragmentDirections.actionMainFragmentToDetails(root));
    }

    private abstract static class ViewModelMaker<F extends ViewModel> implements ViewModelProvider.Factory {

        @NonNull
        public abstract F newInstance();

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
            F instance = newInstance();
            return (T) instance;
        }
    }
}