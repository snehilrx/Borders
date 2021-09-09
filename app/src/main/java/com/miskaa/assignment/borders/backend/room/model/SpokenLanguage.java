package com.miskaa.assignment.borders.backend.room.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(
        primaryKeys = {
                "iso639_2",
                "country_name"
        }
)
public class SpokenLanguage {
    @ColumnInfo(name = "iso639_1")
    private String iso6391;
    @NonNull
    @ColumnInfo(name = "iso639_2")
    private String iso6392;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "nativeName")
    private String nativeName;

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @NonNull
    @ColumnInfo(name = "country_name", index = true)
    private String country_name;

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    @NonNull
    public String getIso6392() {
        return iso6392;
    }

    public void setIso6392(String iso6392) {
        this.iso6392 = iso6392;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
