package com.dam.salesianostriana.di.trianadvisorv1;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Sitios;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by flopez on 15/12/2015.
 */
public interface Api {

    @Headers({
            "X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy",
            "X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR"
    })
    @GET("/1/classes/sitio")
    Call<Sitios> obtenerSitios();




}
