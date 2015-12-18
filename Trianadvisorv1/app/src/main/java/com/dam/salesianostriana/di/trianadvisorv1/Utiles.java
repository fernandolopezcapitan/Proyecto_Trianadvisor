package com.dam.salesianostriana.di.trianadvisorv1;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by flopez on 16/12/2015.
 */
public class Utiles {
    public static Api makeServiceWithInterceptors() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {

                //Aqui es donde añade las cabeceras anteriores para poder acceder al servicio
                Request newRequest = chain.request().newBuilder()
                        .addHeader("X-Parse-Application-Id", "Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy")
                        .addHeader("X-Parse-REST-API-Key","4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR")
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.parse.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        Api service = retrofit.create(Api.class);

        return service;
    }

    public static String codificarEnUtf8(String datoAcodificar) {
        String encodedData = "";

        try {
            encodedData = URLEncoder.encode(datoAcodificar, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return encodedData;
    }


}
