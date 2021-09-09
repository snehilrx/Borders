package com.miskaa.assignment.borders;

import android.app.Application;

public class BordersApplication extends Application {

    public ServiceLocator service = null;

    @Override
    public void onCreate() {
        super.onCreate();
        service = ServiceLocator.getInstance(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        service.close();
    }
}
