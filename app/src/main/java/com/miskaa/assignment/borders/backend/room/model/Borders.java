package com.miskaa.assignment.borders.backend.room.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = RootModel.class,
        parentColumns = "name",
        childColumns = "country_name",
        onDelete = ForeignKey.CASCADE)})
public class Borders {
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "border_name")
        private String name;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCountry_name() {
                return country_name;
        }

        public void setCountry_name(String country_name) {
                this.country_name = country_name;
        }

        @ColumnInfo(name = "country_name")
        private String country_name;
}
