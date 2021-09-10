package com.miskaa.assignment.borders.backend.room.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class Country {
    @Embedded
    public RootModel root;

    @Relation(
            parentColumn = "id",
            entity = SpokenLanguage.class,
            entityColumn = "id",
            associateBy = @Junction(
                value = Relations.class,
                parentColumn = "r_id",
                entityColumn = "l_id"
            )) public List<SpokenLanguage> languages;

    @Relation(parentColumn = "id",
            entityColumn = "country_name") public List<Borders> bordersList;
}
