package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoBares.Result;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.Map;

/**
 * Created by flopez on 17/12/2015.
 */
public class PopupAdapter implements GoogleMap.InfoWindowAdapter{
    private View popup=null;
    private LayoutInflater inflater=null;
    private Map<Marker, Result> bar;
    Context context;
    float valoracion;

    public PopupAdapter(Context context, LayoutInflater inflater, Map<Marker,Result> bar, float valoracion) {

        this.context = context;
        this.inflater = inflater;
        this.bar = bar;
        this.valoracion = valoracion;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (popup == null) {
            popup=inflater.inflate(R.layout.popup_marker, null);
        }
        // Obtengo la información de la ciudad actual, a partir del Marker
        Result barActual = bar.get(marker);

        // Nombre del bar
        TextView tv=(TextView)popup.findViewById(R.id.popup_nombrebar);
        tv.setText(barActual.getNombre());

        // Foto del bar
        ImageView fotobar = (ImageView)popup.findViewById(R.id.popup_fotobar);
        Picasso.with(context).load(barActual.getFoto().getUrl()).fit().into(fotobar);
        // icono.setImageResource(R.drawable.ic_launcher);

        // Direccion del bar
        tv=(TextView)popup.findViewById(R.id.popup_direccionbar);
        tv.setText(barActual.getDireccion());
        //tv.setText(marker.getSnippet());

        // Valoración media del bar
        RatingBar valoracionbar = (RatingBar) popup.findViewById(R.id.popup_valoracionbar);
        valoracionbar.setRating(valoracion);
        valoracionbar.setIsIndicator(true);

        return(popup);
    }
}
