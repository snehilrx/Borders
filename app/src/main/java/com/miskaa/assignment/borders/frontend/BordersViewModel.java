package com.miskaa.assignment.borders.frontend;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.miskaa.assignment.borders.ServiceLocator;
import com.miskaa.assignment.borders.backend.model.Language;
import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.backend.room.BordersDb;
import com.miskaa.assignment.borders.backend.room.model.Borders;
import com.miskaa.assignment.borders.backend.room.model.SpokenLanguage;
import com.miskaa.assignment.borders.backend.room.model.Country;
import com.miskaa.assignment.borders.base.StateLiveData;

import java.util.ArrayList;
import java.util.List;

public class BordersViewModel extends ViewModel {

    public static final String TAG = "Borders/VM";

    private final ServiceLocator service;

    private final StateLiveData<List<Root>> countries;

    public BordersViewModel(ServiceLocator service){
        this.service = service;
        countries = new StateLiveData<List<Root>>() {
            @Override
            public void fallback(StateLiveData<List<Root>> s) {
                s.postLoading();
                service.getRepository().fetch(s::postError);
            }

            @Override
            public boolean isEmpty(List<Root> s) {
                return s.isEmpty();
            }
        };
    }


    public StateLiveData<List<Root>> getCountries() {
        loadData();
        return countries;
    }

    private void loadData() {
        BordersDb database = service.getDatabase();
        database.getQueryExecutor().execute(()->{
            LiveData<List<Country>> listCountries = database.rootDao().getListCountries();
            new Handler(getMainLooper()).post(() -> {
                countries.attach(Transformations.map(listCountries, this::toRoots));
            });
        });
    }

    @NonNull
    private List<Root> toRoots(@NonNull List<Country> countries) {
        ArrayList<Root> roots = new ArrayList<>();
        for(Country c : countries){
            roots.add(toRoot(c));
        }
        return roots;
    }

    @NonNull
    private Root toRoot(@NonNull Country root){
        Root _root = new Root();
        ArrayList<String> borders = new ArrayList<>();
        for(Borders b : root.bordersList){
            borders.add(b.getName());
        }

        ArrayList<Language> languages = new ArrayList<>();
        for(SpokenLanguage s : root.languages){
            Language language = new Language();
            language.setIso6391(s.getIso6391());
            language.setName(s.getName());
            language.setNativeName(s.getNativeName());
            language.setIso6392(s.getIso6392());
            languages.add(language);
        }

        _root.setBorders(borders);
        _root.setLanguages(languages);
        _root.setCapital(root.root.getCapital());
        _root.setFlag(root.root.getFlag());
        _root.setPopulation(root.root.getPopulation());
        _root.setRegion(root.root.getRegion());
        _root.setSubregion(root.root.getSubregion());
        _root.setName(root.root.getName());

        return _root;
    }

    public void deleteData() {
        BordersDb database = service.getDatabase();
        database.getQueryExecutor().execute(database::clearAllTables);
    }
}
