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
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

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
    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
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
