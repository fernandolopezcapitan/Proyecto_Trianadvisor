package com.dam.salesianostriana.di.trianadvisorv1.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.salesianostriana.di.trianadvisorv1.R;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoracion.ComentariosAux;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by flopez on 16/12/2015.
 */
public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder>{
    Context context;
    private List<ComentariosAux> mDataset;

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
    public ComentariosAdapter(List<ComentariosAux> myDataset) {mDataset = myDataset;}

    @Override
    public ComentariosAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_valoraciones,viewGroup,false);
        context = v.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.usuario.setText(mDataset.get(position).getNombre());
        holder.comentarios.setText(mDataset.get(position).getComentario());
        holder.fecha.setText(formatearFechaString(fechaFormateada,mDataset.get(position).getFecha()));
        Picasso.with(context).load(mDataset.get(position).getImagen()).fit().placeholder(R.drawable.logoconletra).into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public String fechaFormateada = "yyyy-MM-dd'T'HH:mm:ss";

    public String formatearFechaString(String formato, String fecha) {
        //formato de entrada
        SimpleDateFormat f = new SimpleDateFormat(formato);
        //formato de salida
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_formateada = "";
        try {
            Date date = f.parse(fecha);
            fecha_formateada = f1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha_formateada;
    }

}
