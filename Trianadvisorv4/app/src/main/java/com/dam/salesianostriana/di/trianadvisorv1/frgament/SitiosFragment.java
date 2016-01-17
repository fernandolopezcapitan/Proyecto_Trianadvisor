package com.dam.salesianostriana.di.trianadvisorv1.frgament;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.salesianostriana.di.trianadvisorv1.R;
import com.dam.salesianostriana.di.trianadvisorv1.adaptadores.SitiosAdapter;
import com.dam.salesianostriana.di.trianadvisorv1.greendao.SitiosDao;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Foto;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Result;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Sitios;
import com.dam.salesianostriana.di.trianadvisorv1.utiles.Utiles;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class SitiosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Result> listadoSitios;
    List<com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios> lista_dao;
    SitiosDao sitioDao;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SitiosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sitios, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.comentarios_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        listadoSitios = new ArrayList();

        // GREENDAO (HITO 2)
        // Creo la base de datos y la guardo en SitiosDao
        sitioDao = Utiles.makeDataBase(getContext()).getSitiosDao();


        preferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        editor = preferences.edit();
        String fecha = preferences.getString("fecha_sitios",null);


        String JSON_BASE = "{\"updatedAt\":{\"$gt\":{\"__type\":\"Date\",\"iso\":\""+fecha+"\"}}}";


        // GREENDAO (HITO 2)
        // Chequeo la conexión.
        // Inserto los datos en la base de datos cuando haya conexión, para usarlos
        // cuando no tenga internet.
        if (Utiles.checkInternet(getActivity())) {
            loadDataSitiosActualizados(Utiles.codificarEnUtf8(JSON_BASE));
        } else {
            List<com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios> sitioDaos = sitioDao.queryBuilder().list();
            List<Result> lista = new ArrayList<>();

            for (int i = 0; i < sitioDaos.size(); i++){
                String objectId, nombre, tlf, categoria, foto_Url, direccion;

                objectId = sitioDaos.get(i).getObjectId();
                nombre = sitioDaos.get(i).getNombre();
                tlf = sitioDaos.get(i).getTlf();
                foto_Url = sitioDaos.get(i).getFotoUrl();
                categoria = sitioDaos.get(i).getCategoria();
                direccion = sitioDaos.get(i).getDireccion();

                lista.add(new Result(objectId,nombre,tlf,foto_Url,categoria,direccion));
            }

            mRecyclerView.setAdapter(new SitiosAdapter(lista));
        }

        //loadDataSitios();
        return view;

    }

    private void loadDataSitiosActualizados(String fecha){
        final Call<Sitios> sitiosCallAc = Utiles.pedirServicioConInterceptores().obtenerSitiosActualizados(fecha);
        sitiosCallAc.enqueue(new Callback<Sitios>() {
            @Override
            public void onResponse(Response<Sitios> response, Retrofit retrofit) {
                Sitios result = response.body();

                List<Result> lista = new ArrayList<Result>();
                lista_dao = new ArrayList<com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios>();

                if (result!=null) {

                    for (int i = 0; i < result.getResults().size(); i++) {
                        if (result.getResults().get(i).getNombre() != null) {

                            String objectId, nombre, tlf, categoria, direccion, descripcion, lat, longitud, fotoUrl, updateAt;
                            Foto foto;

                            objectId = result.getResults().get(i).getObjectId();
                            nombre = result.getResults().get(i).getNombre();
                            tlf = result.getResults().get(i).getTelefono();
                            foto = result.getResults().get(i).getFoto();
                            categoria = result.getResults().get(i).getCategoria();
                            direccion = result.getResults().get(i).getDireccion();

                            descripcion = result.getResults().get(i).getDescripcion();
                            lat = String.valueOf(result.getResults().get(i).getCoordenadas().getLatitude());
                            longitud = String.valueOf(result.getResults().get(i).getCoordenadas().getLongitude());
                            fotoUrl = result.getResults().get(i).getFoto().getUrl();
                            updateAt = result.getResults().get(i).getUpdatedAt();

                            lista.add(new Result(objectId, nombre, tlf, foto, categoria, direccion));
                            //listadoSitios.add(new Result(objectId,nombre,tlf,foto,categoria,direccion));
                            lista_dao.add(new com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios(objectId, updateAt, nombre, categoria, direccion, tlf, descripcion, fotoUrl, lat, longitud));

                        }

                    }
                    //mRecyclerView.setAdapter(new SitiosAdapter(listadoSitios));
                    //Si existe lo reemplaza y si no lo crea
                    for (int i = 0; i < lista_dao.size(); i++) {
                        sitioDao.insertOrReplaceInTx(lista_dao.get(i));
                    }


                    List<Result> lista_final = new ArrayList<>();
                    List<com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios> lista_dao_final = sitioDao.queryBuilder().list();

                    for (int i = 0; i < lista_dao_final.size(); i++) {

                        String objectId, nombre, tlf, categoria, foto_Url, direccion;

                        objectId = lista_dao_final.get(i).getObjectId();
                        nombre = lista_dao_final.get(i).getNombre();
                        tlf = lista_dao_final.get(i).getTlf();
                        foto_Url = lista_dao_final.get(i).getFotoUrl();
                        categoria = lista_dao_final.get(i).getCategoria();
                        direccion = lista_dao_final.get(i).getDireccion();


                        lista_final.add(new Result(objectId, nombre, tlf, foto_Url, categoria, direccion));

                    }

                    editor.putString("fecha_sitios", Utiles.obtenerFechaSistema());
                    editor.apply();

                    mRecyclerView.setAdapter(new SitiosAdapter(lista_final));

                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    private void loadDataSitios(){
        final Call<Sitios> sitiosCall = Utiles.pedirServicioConInterceptores().obtenerSitios();
        sitiosCall.enqueue(new Callback<Sitios>() {
            @Override
            public void onResponse(Response<Sitios> response, Retrofit retrofit) {
                Sitios result = response.body();

                lista_dao = new ArrayList<com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios>();

                if (result!=null){

                    for(int i=0;i<result.getResults().size();i++){
                        if(result.getResults().get(i).getNombre()!=null){

                            String objectId, nombre, tlf, categoria, direccion, descripcion, lat, longitud, fotoUrl, updateAt;
                            Foto foto;

                            objectId = result.getResults().get(i).getObjectId();
                            nombre = result.getResults().get(i).getNombre();
                            tlf = result.getResults().get(i).getTelefono();
                            foto = result.getResults().get(i).getFoto();
                            categoria = result.getResults().get(i).getCategoria();
                            direccion = result.getResults().get(i).getDireccion();

                            descripcion = result.getResults().get(i).getDescripcion();
                            lat = String.valueOf(result.getResults().get(i).getCoordenadas().getLatitude());
                            longitud = String.valueOf(result.getResults().get(i).getCoordenadas().getLongitude());
                            fotoUrl = result.getResults().get(i).getFoto().getUrl();
                            updateAt = result.getResults().get(i).getUpdatedAt();

                            listadoSitios.add(new Result(objectId,nombre,tlf,foto,categoria,direccion));
                            lista_dao.add(new com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios(objectId,updateAt,nombre,categoria,direccion,tlf,descripcion,fotoUrl,lat,longitud));

                        }

                    }
                    mRecyclerView.setAdapter(new SitiosAdapter(listadoSitios));
                }

                //Si existe lo reemplaza y si no lo crea
                for (int i = 0; i < lista_dao.size(); i++){
                    sitioDao.insertOrReplaceInTx(lista_dao.get(i));
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
