package com.miskaa.assignment.borders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.miskaa.assignment.borders.frontend.BordersViewModel;

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