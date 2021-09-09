package com.miskaa.assignment.borders;

import androidx.annotation.NonNull;

import com.miskaa.assignment.borders.backend.model.Language;
import com.miskaa.assignment.borders.backend.model.Root;
import com.miskaa.assignment.borders.backend.room.BordersDb;
import com.miskaa.assignment.borders.backend.room.model.Borders;
import com.miskaa.assignment.borders.backend.room.model.Country;
import com.miskaa.assignment.borders.backend.room.model.RootModel;
import com.miskaa.assignment.borders.backend.room.model.SpokenLanguage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Utils {
    @NonNull
    public static List<RootModel> toRootModels(@NonNull List<Root> roots){
        ArrayList<RootModel> rootList = new ArrayList<>();
        for (Root r: roots){
            RootModel root = new RootModel();
            root.setName(r.getName());
            root.setCapital(r.getCapital());
            root.setSubregion(r.getSubregion());
            root.setRegion(r.getRegion());
            root.setFlag(r.getFlag());
            root.setPopulation(r.getPopulation());
            rootList.add(root);
        }
        return  rootList;
    }

    @NonNull
    public static List<Borders> toBorders(@NonNull List<Root> roots){
        ArrayList<Borders> borders = new ArrayList<>();
        for (Root r: roots){
            for(String s: r.getBorders()){
                Borders b = new Borders();
                b.setName(s);
                b.setCountry_name(r.getName());
                borders.add(b);
            }
        }
        return  borders;
    }

    @NonNull
    public static List<SpokenLanguage> toLanguage(@NonNull List<Root> roots){
        ArrayList<SpokenLanguage> sp = new ArrayList<>();
        for (Root r: roots){
            for(Language l : r.getLanguages()){
                SpokenLanguage sl = new SpokenLanguage();
                sl.setName(l.getName());
                sl.setIso6392(l.getIso6392());
                sl.setIso6391(l.getIso6391());
                sl.setNativeName(l.getNativeName());
                sl.setCountry_name(r.getName());
                sp.add(sl);
            }
        }
        return sp;
    }
}
