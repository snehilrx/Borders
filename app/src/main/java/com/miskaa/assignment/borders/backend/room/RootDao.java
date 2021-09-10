package com.miskaa.assignment.borders.backend.room;

import com.miskaa.assignment.borders.Utils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.backend.room.model.Borders;
import com.miskaa.assignment.borders.backend.room.model.Country;
import com.miskaa.assignment.borders.backend.room.model.Relations;
import com.miskaa.assignment.borders.backend.room.model.RootModel;
import com.miskaa.assignment.borders.backend.room.model.SpokenLanguage;

import java.util.List;

@Dao
public interface RootDao {
    @Transaction
    @Query("SELECT * from RootModel")
    public LiveData<List<Country>> getListCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRoot(List<RootModel> root);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSpokenLanguage(List<SpokenLanguage> languages);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertLanguageRoot(List<Relations> languages);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBorders(List<Borders> borders);

    default void insert(List<Root> roots){
        insertRoot(Utils.toRootModels(roots));
        insertSpokenLanguage(Utils.toLanguage(roots));
        insertLanguageRoot(Utils.toSpokenLanguageRoot(roots));
        insertBorders(Utils.toBorders(roots));
    }
}
