package com.dam.salesianostriana.di.trianadvisorv1.utiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dam.salesianostriana.di.trianadvisorv1.greendao.DaoMaster;
import com.dam.salesianostriana.di.trianadvisorv1.greendao.DaoSession;
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
    public static Api pedirServicioConInterceptores() {

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

    private Api pedirServicio(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.parse.com")
                .addConverterFactory(GsonConverterFactory.create())
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

    public static DaoSession crearBD (Context context){
        // Lineas importantes:
        // 1. Crea la base de datos
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes", null);
        // 2. Obtenemos la base de datos
        SQLiteDatabase db = helper.getWritableDatabase();
        // Nos permiten conectarnos a los CRUD
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        return daoSession;
    }

    // Este método pide conexión a internet para continuar la app según el activity
    // En caso de no tenerla, y si quieres que abra los ajustes de conexión, ver clase LoginActivity

    public static boolean checkInternet(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null || !i.isConnected() || !i.isAvailable()) {

            return false;

        } else {

            return true;
        }
    }

}
