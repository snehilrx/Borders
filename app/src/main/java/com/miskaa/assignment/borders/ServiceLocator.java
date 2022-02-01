package com.miskaa.assignment.borders;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.miskaa.assignment.borders.backend.retrofit.Api;
import com.miskaa.assignment.borders.backend.room.BordersDb;
import com.miskaa.assignment.borders.backend.room.model.Borders;
import com.miskaa.assignment.borders.frontend.BordersRepository;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceLocator {

    private static ServiceLocator instance = null;

    private final Api api;
    private final BordersDb bordersDb;
    private final BordersRepository repo;

    private ServiceLocator(Context context) {
        api = new Retrofit.Builder()
                .baseUrl("https://restcountries.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(Api.class);
        bordersDb = BordersDb.getInstance(context);
        repo = BordersRepository.Factory.create(this);
    }

    public static ServiceLocator getInstance(Context context) {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator(context);
            }
        }
        return instance;
    }

    public Api getApi() {
        return api;
    }

    public BordersDb getDatabase(){
        return bordersDb;
    }

    public BordersRepository getRepository(){
        return repo;
    }

    public void close() {
        getDatabase().close();
        repo.close();
    }
}
