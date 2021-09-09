package com.miskaa.assignment.borders.backend.room.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

public class Country {
    @Embedded
    public RootModel root;

    @Relation(parentColumn = "name",
            entityColumn = "country_name") public List<SpokenLanguage> languages;

    @Relation(parentColumn = "name",
            entityColumn = "country_name") public List<Borders> bordersList;
}
