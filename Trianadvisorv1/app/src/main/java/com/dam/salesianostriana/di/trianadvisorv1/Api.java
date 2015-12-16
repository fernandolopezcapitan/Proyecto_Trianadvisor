package com.dam.salesianostriana.di.trianadvisorv1;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Login;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Result;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Sitios;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoraciones.Valoraciones;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

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

    @GET("/1/login")
    Call<Login> obtenerLogin(@Query ("username") String username,@Query ("password") String password);

    @GET("/1/users/me")
    Call<Login> obtenerMisDatos(@Header("X-Parse-Session-Token") String sesion, @Query("sessionToken") String sessionToken);

    @GET("/1/classes/sitio/{objectId}")
    Call<Result> obtenerDatosSitio(@Path("objectId") String objectId);

    @GET("/1/classes/valoracion?&include=usuario")
    Call<Valoraciones> obtenerValoracionesSitio(@Query("objectId") String objectId);


}
