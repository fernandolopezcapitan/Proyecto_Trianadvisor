package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemSitios> sitios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        sitios = new ArrayList();
        sitios.add(new ItemSitios("La esquinita","c/ Pagés del Corro, 12","Tapeo", "955444333",R.drawable.logoconletra));
        sitios.add(new ItemSitios("Bodega Santa Ana","c/ Miño, 8","Desayuños", "955221113",R.drawable.logoconletra));
        sitios.add(new ItemSitios("Bar Paletas","c/ Evangelista, 34","Desayunos", "955000666",R.drawable.logoconletra));
        sitios.add(new ItemSitios("Monte Fuji","c/ Salado, 59","Restaurante japonés", "954999333",R.drawable.logoconletra));
        mAdapter = new SitiosAdapter(sitios);
        mRecyclerView.setAdapter(mAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        String mensaje = "";
        Fragment f = null;

        if (id == R.id.bares_registrados) {
            //f = new AmigosFragment();
            // Intent i .... Scrolling
            Intent i = new Intent(MainActivity.this,ScrollingActivity.class);
            startActivity(i);
            mensaje = "Bares registrados";
        } else if (id == R.id.ir_de_tapas) {
            //Intent i = new Intent(MainActivity.this, MapsActivity.class);
            //startActivity(i);
            mensaje = "Mapas";
        } else if (id == R.id.nav_cerrar_sesion) {
            mensaje = "Cerrar sesión";

        }

        if(f!=null) {
            transicionPagina(f);
        }

        // Marco el elemento del menú como elemento
        // seleccionado.
        item.setChecked(true);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

        }
    public void transicionPagina(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor,f).commit();
    }

}
