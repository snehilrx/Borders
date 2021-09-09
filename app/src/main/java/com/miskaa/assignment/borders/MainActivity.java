package com.miskaa.assignment.borders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;

import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.frontend.BordersViewModel;
import com.miskaa.assignment.borders.frontend.ui.fragments.CountryFragment;
import com.miskaa.assignment.borders.frontend.ui.fragments.MainFragment;
import com.miskaa.assignment.borders.frontend.ui.fragments.MainFragmentDirections;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Borders/Activity";

    private BordersViewModel mViewModel = null;

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
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Created");
    }

    /** @return BordersViewModel or null value */
    public BordersViewModel getViewModel(){
        return mViewModel;
    }

    public void hideToolBar(){

    }

    public void showToolBar(){

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