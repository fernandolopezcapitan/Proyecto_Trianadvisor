package com.dam.salesianostriana.di.trianadvisorv1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.sitios.ResultSitio;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.sitios.Sitio;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class SitiosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ResultSitio> listadoSitios;

    public SitiosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sitios, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);



        listadoSitios = new ArrayList();
        /*
        listadoSitios.add(new ItemSitios("La esquinita", "c/ Pagés del Corro, 12", "Tapeo", "955444333", R.drawable.logoconletra));
        listadoSitios.add(new ItemSitios("Bodega Santa Ana", "c/ Miño, 8", "Desayuños", "955221113", R.drawable.logoconletra));
        listadoSitios.add(new ItemSitios("Bar Paletas", "c/ Evangelista, 34", "Desayunos", "955000666", R.drawable.logoconletra));
        listadoSitios.add(new ItemSitios("Monte Fuji", "c/ Salado, 59", "Restaurante japonés", "954999333", R.drawable.logoconletra));
        mAdapter = new SitiosAdapter(listadoSitios);
        mRecyclerView.setAdapter(mAdapter);*/

        loadDataSitios();

        return view;

    }

    private Api makeService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.parse.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);
        return service;
    }

    private void loadDataSitios(){
        final Call<Sitio> sitiosCall = makeService().obtenerSitios();
        sitiosCall.enqueue(new Callback<Sitio>() {
            @Override
            public void onResponse(Response<Sitio> response, Retrofit retrofit) {
                Sitio result = response.body();

                if (result != null) {
                    for (int i = 0; i < result.getResultSitioses().size(); i++) {
                        if (result.getResultSitioses().get(i).getNombre() != null) {
                            listadoSitios.add(new ResultSitio(
                                    result.getResultSitioses().get(i).getObjectId(),
                                    result.getResultSitioses().get(i).getNombre(),
                                    result.getResultSitioses().get(i).getTelefono(),
                                    result.getResultSitioses().get(i).getFoto(),
                                    result.getResultSitioses().get(i).getCategoria(),
                                    result.getResultSitioses().get(i).getDireccion()));
                        }

                    }

                    mRecyclerView.setAdapter(new SitiosAdapter(listadoSitios));
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
