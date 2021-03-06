package com.miskaa.assignment.borders.backend.retrofit;

import com.miskaa.assignment.borders.backend.model.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/v2/all?fields=name,capital,flag,region,subregion,population,borders,languages")
    public Call<List<Root>> getListOfCountriesInAsia();

}
