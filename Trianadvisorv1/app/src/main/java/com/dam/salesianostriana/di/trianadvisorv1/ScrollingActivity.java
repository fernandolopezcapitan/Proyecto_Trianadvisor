package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Comentario;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ScrollingActivity extends AppCompatActivity {

    RatingBar rating;
    TextView categoria, direccion, tlf, descripcion;
    Toolbar toolbar;
    ImageView imgBar;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Result> listadoComentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rating = (RatingBar) findViewById(R.id.rating_valoraciones);
        categoria = (TextView) findViewById(R.id.tv_categoria_scroll);
        direccion = (TextView) findViewById(R.id.tv_direcc_scroll);
        tlf =(TextView) findViewById(R.id.tv_tlf_scroll);
        descripcion = (TextView) findViewById(R.id.tv_descripcion_scroll);
        imgBar = (ImageView) findViewById(R.id.img_logo_scroll);

        Bundle extras = getIntent().getExtras();
        String objectId="";
        if(extras!=null){
            objectId =  extras.getString("objectId");
        }

        loadDataSitios(objectId);

        //Carga del recycler de comentarios
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(ScrollingActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        listadoComentarios = new ArrayList();

        //loadDataComentarios(objectId);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(ScrollingActivity.this, EnviarcomentariosActivity.class);
                startActivity(i);
            }
        });
    }

    private void loadDataSitios(final String objectId) {
        final Call<Result> sitiosCall = Utiles.makeServiceWithInterceptors().obtenerDatosSitio(objectId);
        sitiosCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit) {
                Result result = response.body();

                toolbar.setTitle(result.getNombre());
                setSupportActionBar(toolbar);
                categoria.setText(result.getCategoria());
                direccion.setText(result.getDireccion());
                tlf.setText(result.getTelefono());
                descripcion.setText(result.getDescripcion());
                Picasso.with(ScrollingActivity.this).load(result.getFoto().getUrl()).fit().placeholder(R.drawable.fondologin1).into(imgBar);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    private void loadDataComentarios(final String objectId){
        final Call<Comentario> comentariosCall = Utiles.makeServiceWithInterceptors().obtenerComentariosSitio(objectId);
        comentariosCall.enqueue(new Callback<Comentario>() {
            @Override
            public void onResponse(Response<Comentario> response, Retrofit retrofit) {
                Comentario result = response.body();

                for (int i = 0;i<result.getResults().size();i++){
                    if (result.getResults().get(i).getObjectId()!= null){
                        listadoComentarios.add(new Result(
                                result.getResults().get(i).getFecha(),
                                result.getResults().get(i).getComentario(),
                                result.getResults().get(i).getFoto(),
                                result.getResults().get(i).getNombre()));
                    }
                }
                mRecyclerView.setAdapter(new SitiosAdapter(listadoComentarios));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });



    }
}
