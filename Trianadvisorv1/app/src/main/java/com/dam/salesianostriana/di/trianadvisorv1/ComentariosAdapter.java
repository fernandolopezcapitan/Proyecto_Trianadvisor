package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoraciones.ResultValoraciones;

import java.util.List;

/**
 * Created by flopez on 16/12/2015.
 */
public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder>{
    Context context;
    private List<ResultValoraciones> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView usuario, fecha, comentarios;
        public ImageView foto;

        public ViewHolder(View v) {
            super(v);
            usuario = (TextView) v.findViewById(R.id.tv_usuario_valoraciones);
            fecha = (TextView) v.findViewById(R.id.tv_fecha_valoraciones);
            comentarios = (TextView) v.findViewById(R.id.tv_comentarios_valoraciones);
            foto =(ImageView) v.findViewById(R.id.foto_user);

        }
    }
    public ComentariosAdapter(List<ResultValoraciones> myDataset) {mDataset = myDataset;}

    @Override
    public ComentariosAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_valoraciones,viewGroup,false);
        context = v.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.usuario.setText(mDataset.get(position).getUsuario().getNombre());
        holder.fecha.setText(mDataset.get(position).getCreatedAt());
        //holder.comentarios.setText(mDataset.get(position).);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
