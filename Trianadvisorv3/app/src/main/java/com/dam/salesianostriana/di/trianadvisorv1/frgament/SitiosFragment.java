package com.dam.salesianostriana.di.trianadvisorv1.frgament;


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
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Result> listadoSitios;

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

        SitiosDao sitiosDao = Utiles.crearBD(getContext()).getSitiosDao();

        if(Utiles.checkInternet(getActivity())){
            loadDataSitios();
        }else{

            List<com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios> lista_sitios = sitiosDao.queryBuilder().list();

            List<Result> lista_recycler = new ArrayList<>();
            for(int i = 0;i<lista_sitios.size();i++){

                //lista_recycler.add(new Result(objectid,nombre));

            }

            mRecyclerView.setAdapter(new SitiosAdapter(lista_recycler));

        }



        return view;

    }




    private void loadDataSitios(){
        final Call<Sitios> sitiosCall = Utiles.pedirServicioConInterceptores().obtenerSitios();
        sitiosCall.enqueue(new Callback<Sitios>() {
            @Override
            public void onResponse(Response<Sitios> response, Retrofit retrofit) {
                Sitios result = response.body();

                for(int i=0;i<result.getResults().size();i++){
                    if(result.getResults().get(i).getNombre()!=null){
                        listadoSitios.add(new Result(
                                result.getResults().get(i).getObjectId(),
                                result.getResults().get(i).getNombre(),
                                result.getResults().get(i).getTelefono(),
                                result.getResults().get(i).getFoto(),
                                //result.getResults().get(i).getCoordenadas().getLatitude(),
                                result.getResults().get(i).getCategoria(),
                                result.getResults().get(i).getDireccion()));


                    }

                   // com.dam.salesianostriana.di.trianadvisorv1.greendao.Sitios sitio_green = null;

                    //sitio_green = (new Sitio(object,nombre,telefono....));

                    //sitiosDao.insert(sitio_green);

                }
                mRecyclerView.setAdapter(new SitiosAdapter(listadoSitios));

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
