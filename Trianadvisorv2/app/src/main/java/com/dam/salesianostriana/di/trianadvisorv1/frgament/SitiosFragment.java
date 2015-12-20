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
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Result;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Sitios;
import com.dam.salesianostriana.di.trianadvisorv1.utiles.Api;
import com.dam.salesianostriana.di.trianadvisorv1.utiles.Utiles;

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
    private List<Result> listadoSitios;

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

        loadDataSitios();

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
                                result.getResults().get(i).getCategoria(),
                                result.getResults().get(i).getDireccion()));
                    }

                }
                mRecyclerView.setAdapter(new SitiosAdapter(listadoSitios));

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
