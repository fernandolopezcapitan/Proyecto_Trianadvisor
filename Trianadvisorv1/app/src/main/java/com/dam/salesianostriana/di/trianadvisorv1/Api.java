package com.dam.salesianostriana.di.trianadvisorv1;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Comentario;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Login;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Result;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Sitios;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoBares.Bares;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoracion.Valoracion;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.sitios.Sitio;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by flopez on 15/12/2015.
 * Para acceder a los servicios web accederán al backend de parse.com. Las peticiones se harán a la raiz
 * "https://api.parse.com", añadiéndoles las cabeceras (headers):
 * X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy
 * X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR
 *
 */
public interface Api {

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
    @GET("/1/classes/sitio")
    Call<Sitio> obtenerSitios();

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
    @GET("/1/classes/valoracion?")
    Call<Valoracion> obtenerValoracionesSitio(@Query(value = "where", encoded = true) String where);

    // Petición 8. Almacenar una valoración.
    // INSERTA una valoración sobre un sitio.
    // ESPERA Añadir el parámetro Content-Type con valor application/json a la cabecera.
    // Además, espera dentro del cuerpo de la petición, en formato json, el objeto a dar de alta.
    // DEVUELVE con la fecha y el objectId de la valoración creada.
    //@Headers("{Content-Type: application/json}")
    //@POST("/1/classes/valoracion")
    //Call<NuevaValoracion> almacenarValoracion(@Body NuevaValoracion resultValoracion);

    // Petición 9. Confirmar si un usuario ha valorado ya un sitio.
    // INSERTA la devolución de la valoración realizada por un usuario a un sitio, si es que ya la ha realizado antes.
    // ESPERA identificador del sitio y del usuario, dentro del WHERE, como parámetros URL-encoded.
    // DEVUELVE si la valoración está creada, devuelve un json con la misma.
    //@GET("/1/classes/valoracion/?")
    //Call<Valoracion> comprobarSiSeHaValorado(@Query(value = "where", encoded = true) String json);

    // Petición 10. Actualizar una valoración.
    // ACTUALIZA una valoración sobre un sitio.
    // ESPERA Añadir el parámetro Content-Type con valor application/json a la cabecera.
    // Además, espera dentro del cuerpo de la petición, en formato json, el dato que vamos a actualizar.
    // Debe tener la siguiente estructura:
    //  {
    //    "valoracion": X
    //  }
    // donde X es la nueva puntuación (entre 0 y 5) que vamos a darle al sitio.
    // DEVUELVE devuelve un json con la fecha de actualización.
    //@Headers("{Content-Type: application/json}")
    //@PUT("/1/classes/valoracion/{objectId}")
    //Call<Actualizado> actualizarValoracion(@Body ActuValoracion nueva, @Path("objectId") String obj);

    // Petición 11. Obtener todos los comentarios de un sitio.
    // DEVUELVE el listado con todos los comentarios de un sitio concreto.
    // Si se quiere que el objeto usuario contenga todos los datos del usuario,
    // se puede añadir como parámetro  URL-enconded include=usuario.
    // ESPERA identificador del sitio, dentro del WHERE, como parámetros URL-encoded.
    // DEVUELVE devuelve un json con todas los comentario de un sitio concreto.
    //@GET("/1/classes/comentario/?&include=usuario")
    //Call<Comentario> obtenerComentariosSitio(@Query(value = "where", encoded = true) String json);

    // Petición 12. Almacenar un comentario.
    // INSERTA un comentario sobre un sitio.
    // ESPERA Añadir el parámetro Content-Type con valor application/json a la cabecera.
    // Además, espera dentro del cuerpo de la petición, en formato json, el objeto a dar de alta.
    // DEVUELVE devuelve un json con la fecha y el objectId del comentario creado.
    //@Headers("{Content-Type: application/json}")
    //@POST("/1/classes/comentario")
    //Call<NuevoComentario> almacenarComentario(@Body NuevoComentario resultComentario);

}
