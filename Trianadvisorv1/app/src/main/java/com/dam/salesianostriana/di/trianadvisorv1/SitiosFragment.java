package com.dam.salesianostriana.di.trianadvisorv1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SitiosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemSitios> sitios;

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
        sitios = new ArrayList();
        sitios.add(new ItemSitios("La esquinita","c/ Pagés del Corro, 12","Tapeo", "955444333",R.drawable.logoconletra));
        sitios.add(new ItemSitios("Bodega Santa Ana","c/ Miño, 8","Desayuños", "955221113",R.drawable.logoconletra));
        sitios.add(new ItemSitios("Bar Paletas","c/ Evangelista, 34","Desayunos", "955000666",R.drawable.logoconletra));
        sitios.add(new ItemSitios("Monte Fuji","c/ Salado, 59","Restaurante japonés", "954999333",R.drawable.logoconletra));
        mAdapter = new SitiosAdapter(sitios);
        mRecyclerView.setAdapter(mAdapter);

        return view;

    }

}
