package com.miskaa.assignment.borders.frontend;

import androidx.annotation.NonNull;

import com.miskaa.assignment.borders.ServiceLocator;
import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.backend.room.BordersDb;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BordersRepository {

    private final ServiceLocator serviceLocator;

    private Call<List<Root>> mCallback;

    private BordersRepository(ServiceLocator service){
        this.serviceLocator = service;
    }

    public void close() {
        mCallback.cancel();
    }

    public void fetch(IErrorCallback callback){
        mCallback =  serviceLocator.getApi().getListOfCountriesInAsia();
        mCallback.enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(@NonNull Call<List<Root>> call, @NonNull Response<List<Root>> response) {
                List<Root> roots = response.body();
                BordersDb database = serviceLocator.getDatabase();
                BordersDb.databaseWriteExecutor.execute(()->{
                    database.rootDao().insert(roots);
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Root>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public interface IErrorCallback {
        public void onError(Throwable t);
    }


    public static class Factory {
        public static BordersRepository create(ServiceLocator serviceLocator){
            return new BordersRepository(serviceLocator);
        }
    }

}
