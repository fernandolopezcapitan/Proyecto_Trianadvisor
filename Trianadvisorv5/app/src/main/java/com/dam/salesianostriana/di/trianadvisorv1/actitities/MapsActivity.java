package com.dam.salesianostriana.di.trianadvisorv1.actitities;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.dam.salesianostriana.di.trianadvisorv1.R;
import com.dam.salesianostriana.di.trianadvisorv1.adaptadores.PopupAdapter;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoBares.Bares;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoBares.Foto;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoBares.Result;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.pojoValoracion.Valoracion;
import com.dam.salesianostriana.di.trianadvisorv1.utiles.Utiles;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient = null;
    private Map<Marker, Result> allMarkersMap = new HashMap<Marker, Result>();
    private Location mCurrentLocation;
    int error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //LatLng dondeEstoy = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        LatLng dondeEstoy = new LatLng(37.380346,-6.007744);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dondeEstoy, 15));

        loadDataBaresCercanos(dondeEstoy.latitude, dondeEstoy.longitude);

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //mMap.setInfoWindowAdapter(new PopupAdapter(MapsActivity.this, getLayoutInflater(), allMarkersMap, mediaValoraciones));
    }



    private void loadDataBaresCercanos(final double latitud, final double longitud) {

        final Call<Bares> baresCall = Utiles.pedirServicioConInterceptores().irdetapas(latitud,longitud);
        baresCall.enqueue(new Callback<Bares>() {
            @Override
            public void onResponse(Response<Bares> response, Retrofit retrofit) {
                Bares bares = response.body();

                for (int i = 0; i < bares.getResults().size(); i++) {
                    if (bares.getResults().get(i).getNombre() != null) {
                        double latitudBar = bares.getResults().get(i).getCoordenadas().getLatitude();
                        double longitudBar = bares.getResults().get(i).getCoordenadas().getLongitude();
                        String objectIdBar = bares.getResults().get(i).getObjectId();
                        String nombreBar = bares.getResults().get(i).getNombre();
                        Foto fotoBar = bares.getResults().get(i).getFoto();
                        String direccionBar = bares.getResults().get(i).getDireccion();

                        LatLng coordenadasBar = new LatLng(latitudBar, longitudBar);
                        Marker marcadorBar = mMap.addMarker(new MarkerOptions().position(coordenadasBar).title(nombreBar));
                        marcadorBar.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapabar60));

                        allMarkersMap.put(marcadorBar, new Result(objectIdBar, nombreBar, direccionBar, fotoBar));
                        loadDataValoracionesSitio(objectIdBar);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
    private void loadDataValoracionesSitio(final String objectId){

        final Call<Valoracion> valoracionCall = Utiles.pedirServicioConInterceptores().obtenerValoracionesSitio(objectId);
        valoracionCall.enqueue(new Callback<Valoracion>() {
            @Override
            public void onResponse(Response<Valoracion> response, Retrofit retrofit) {
                Valoracion valoracion = response.body();

                error = response.code();

                /*String cadena = String.valueOf(error);
                String primer_numero = cadena.substring(0, 1);
                int formateado = Integer.parseInt(primer_numero);*/

                Log.i("fallo",String.valueOf(error));

                float mediaValoraciones = 0;
                if (valoracion != null) {
                    for (int i = 0; i < valoracion.getResults().size(); i++) {
                        if(valoracion.getResults().get(i).getValoracion()!= null)
                            mediaValoraciones = valoracion.getResults().get(i).getValoracion() + mediaValoraciones;
                    }
                    mediaValoraciones = mediaValoraciones / valoracion.getResults().size();
                }
                mMap.setInfoWindowAdapter(new PopupAdapter(MapsActivity.this, getLayoutInflater(), allMarkersMap, mediaValoraciones));

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}


