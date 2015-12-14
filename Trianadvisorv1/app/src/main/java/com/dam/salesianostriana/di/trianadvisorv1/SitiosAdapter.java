package com.dam.salesianostriana.di.trianadvisorv1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by flopez on 14/12/2015.
 */
public class SitiosAdapter extends RecyclerView.Adapter<SitiosAdapter.ViewHolder>{

    private ArrayList<ItemSitios> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, categoria, direccion, telefono;
        public ImageView foto;

        public ViewHolder(View v) {
            super (v);
            nombre = (TextView) v.findViewById(R.id.tv_nombre_sitio);
            categoria = (TextView) v.findViewById(R.id.tv_categoria_sitio);
            direccion = (TextView) v.findViewById(R.id.tv_direcc_sitio);
            telefono = (TextView) v.findViewById(R.id.tv_tlf_sitio);
            foto = (ImageView) v.findViewById(R.id.foto_sitio);
        }

    }
    public SitiosAdapter(ArrayList<ItemSitios> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public SitiosAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_sitios,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SitiosAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(mDataset.get(position).getNombre());
        holder.categoria.setText(mDataset.get(position).getCategoria());
        holder.direccion.setText(mDataset.get(position).getDireccion());
        holder.telefono.setText(mDataset.get(position).getTelefono());
        holder.foto.setImageResource(mDataset.get(position).getFoto());
    }

    @Override
    public int getItemCount()   {
        return mDataset.size();
    }
}
