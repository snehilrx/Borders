package com.miskaa.assignment.borders.backend.room.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"l_id", "r_id"})
public class Relations {

    public Relations() {
        name = "";
        iso6392 = "";
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    @ColumnInfo(name = "r_id", index = true)
    private String name;


    @NonNull
    @ColumnInfo(name = "l_id", index = true)
    private String iso6392;

    @NonNull
    public String getIso6392() {
        return iso6392;
    }

    public void setIso6392(@NonNull String iso6392) {
        this.iso6392 = iso6392;
    }
}
