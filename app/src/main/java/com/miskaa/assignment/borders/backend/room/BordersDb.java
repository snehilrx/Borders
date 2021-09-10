package com.miskaa.assignment.borders.backend.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.miskaa.assignment.borders.backend.room.model.SpokenLanguage;
import com.miskaa.assignment.borders.backend.room.model.RootModel;
import com.miskaa.assignment.borders.backend.room.model.Borders;
import com.miskaa.assignment.borders.backend.room.model.Relations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RootModel.class, SpokenLanguage.class, Borders.class, Relations.class}, version = 1, exportSchema = false)
public abstract class  BordersDb extends RoomDatabase {
    public abstract RootDao rootDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static BordersDb INSTANCE = null;

    public synchronized static BordersDb getInstance(Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, BordersDb.class, "borders.db").build();
        }
        return  INSTANCE;
    }
}
