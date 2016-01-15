package com.dam.salesianostriana.di.trianadvisorv1.utiles;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Comentario;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Login;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Result;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Sitios;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoBares.Bares;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoracion.Valoracion;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by flopez on 15/12/2015.
 * Para acceder a los servicios web accederán al backend de parse.com.
 */
public interface Api {

    // Las peticiones se harán a la raiz
    // "https://api.parse.com", añadiéndoles las cabeceras (headers):
    // X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy
    // X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR
    @Headers({
            "X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy",
            "X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR"
    })

    // Petición 1. Login.
    // REALIZA el proceso de autentificación del usuario.
    // ESPERA username y password, como parámetros URL-encoded.
    // DEVUELVE un json con todos los datos del usuario menos el password.
    @GET("/1/login")
    Call<Login> obtenerLogin(@Query ("username") String username,@Query ("password") String password);

    // Petición 2. Me.
    // REALIZA la identificación del usuario logueado a partir de un session token.
    // ESPERA X-Parse-Session-Token (obtenido en el login) como cabecera de la petición.
    // DEVUELVE un json con todos los datos del usuario menos el password.
    @GET("/1/users/me")
    Call<Login> obtenerMisDatos(@Header("X-Parse-Session-Token") String sesion, @Query("sessionToken") String sessionToken);

    // Petición 3. Logout.
    // REALIZA el proceso de logout, eliminando el session token.
    // ESPERA X-Parse-Session-Token (obtenido en el login) como cabecera de la petición.
    // DEVUELVE un json con todos los datos del usuario menos el password.
    @POST("/1/logout")
    Call<Login> cerrarSesion(@Header("X-Parse-Session-Token") String header);

    // Petición 4. Obtener todos los sitios.
    // REALIZA la devolución del listado con todos los sitios.
    // ESPERA (no espera ningún dato).
    // DEVUELVE un json con todos los datos de los sitios.
    @GET("/1/classes/sitio")//incluir where
    Call<Sitios> obtenerSitios();

    // Petición 5. Obtener los datos de un sitio concreto.
    // REALIZA la devolución de toda la información del sitio.
    // ESPERA objectId, como parámetro en la URL.
    // DEVUELVE un json con todos los datos del sitio concreto, si es que existe dicho objectId.
    @GET("/1/classes/sitio/{objectId}")
    Call<Result> obtenerDatosSitio(@Path("objectId") String objectId);

    // Petición 6. Obtener los 10 sitios cercanos a mi.
    // REALIZA la devolución del listado con todos los 10 sitios más cercanos a mi.
    // ESPERA Latitud y longitud dentro del WHERE, como parámetros URL-encoded.
    // DEVUELVE un json con todos los datos de los sitios.
    @GET("/1/classes/sitio?limit=10")
    Call<Bares> irdetapas(@Query("latitude") double latitud,@Query("longitude") double longitud);

    // Petición 7. Obtener todas las valoraciones de un sitio.
    // REALIZA la devolución del listado con todas las valoraciones (numéricas) de un determinado sitio.
    // Si se quiere que el objeto usuario contenga todos los datos del usuario,
    // se puede añadir como parámetro  URL-enconded include=usuario.
    // ESPERA identificador del sitio, dentro del WHERE, como parámetros URL-encoded.
    // DEVUELVE un json con todas las valoraciones de un sitio concreto.
    @GET("/1/classes/valoracion?")//where
    Call<Valoracion> obtenerValoracionesSitio(@Query(value = "where", encoded = true) String where);

    // Petición 11. Obtener todos los comentarios de un sitio.
    // DEVUELVE el listado con todos los comentarios de un sitio concreto.
    // Si se quiere que el objeto usuario contenga todos los datos del usuario,
    // se puede añadir como parámetro  URL-enconded include=usuario.
    // ESPERA identificador del sitio, dentro del WHERE, como parámetros URL-encoded.
    // DEVUELVE devuelve un json con todas los comentario de un sitio concreto.
    @GET("/1/classes/comentario/?&include=usuario")//where
    Call<Comentario> obtenerComentariosSitio(@Query(value = "where", encoded = true) String json);

    // Petición GreenDao. Obtener sitios actualizados
    // Idem Pedición 4 Obtener Sitios pero actualizados
    @GET("/1/classes/sitio?")
    Call<Sitios> obtenerSitiosActualizados(@Query(value = "where", encoded = true) String json);



}
