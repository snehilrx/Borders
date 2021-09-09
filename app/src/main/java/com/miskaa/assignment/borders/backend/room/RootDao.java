package com.miskaa.assignment.borders.backend.room;

import static com.miskaa.assignment.borders.Utils.*;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.backend.room.model.Borders;
import com.miskaa.assignment.borders.backend.room.model.Country;
import com.miskaa.assignment.borders.backend.room.model.RootModel;
import com.miskaa.assignment.borders.backend.room.model.SpokenLanguage;

import java.util.List;

@Dao
public interface RootDao {

    @Query("SELECT * from RootModel")
    public LiveData<List<Country>> getListCountries();

    @Insert
    public void insertRoot(List<RootModel> root);

    @Insert
    public void insertLanguage(List<SpokenLanguage> languages);

    @Insert
    public void insertBorders(List<Borders> borders);

    default void insert(List<Root> roots){
        insertRoot(toRootModels(roots));
        insertLanguage(toLanguage(roots));
        insertBorders(toBorders(roots));
    }
}
