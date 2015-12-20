package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Login;
import com.squareup.okhttp.internal.Util;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView avatarCabecera;
    TextView nombreCabecera;
    int error;
    String sessionToken="";
    SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recoger sessionToken obtenido del login con los extras.

        Bundle extras = getIntent().getExtras();

        if(extras!=null){
           sessionToken =  extras.getString("sessionToken");
        }

        //Se obtiene la sessionToken que hay guardada en las preferencias
        preferencias = getSharedPreferences("preferencias", MODE_PRIVATE);
        sessionToken = preferencias.getString("sessionToken", null);

        //Con dicho sessionToken hacer la consulta con el servicio "ME" al que se le debe pasar el sessionToken

        //La cabecera de la consulta debe contener el sessiontokem

        // Call<Login>.....  = instarciarServicio.obtenerMisDatos(session,session);
        // Devuelve el response tus datos.
        //http://api.parse.com/1/........&sessionToken=.....

        loadDataSessionToken(sessionToken,sessionToken);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View cabeceraMenuLateral = navigationView.getHeaderView(0);
        avatarCabecera = (ImageView)cabeceraMenuLateral.findViewById(R.id.icono_navegation);
        nombreCabecera = (TextView)cabeceraMenuLateral.findViewById(R.id.usuario_navigation);

        // Abre de inicio Sitios Fragment
        transicionPagina(new SitiosFragment());
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
            f = new SitiosFragment();
        } else if (id == R.id.ir_de_tapas) {
            Intent i = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(i);
            mensaje = "Mapas";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cerrar_sesion) {
            //loadDataCerrarSesion(sessionToken);
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            //SharedPreferences.Editor editor = preferencias.edit();
            //editor.clear();
            //editor.apply();
            startActivity(i);
            this.finish();

        }



        if(f!=null) {
            transicionPagina(f);
        }

        // Marco el elemento del menú como elemento
        // seleccionado.
        item.setChecked(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

        }
    public void transicionPagina(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor,f).commit();
    }
    private void loadDataSessionToken(final String sessionToken, final String sessionToken1){

        final Call<Login> loginCall = Utiles.makeServiceWithInterceptors().obtenerMisDatos(sessionToken, sessionToken1);
        loginCall.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {
                Login login = response.body();

                if (login != null) {
                    if (login.getSessionToken().equals(sessionToken)) {
                        nombreCabecera.setText(login.getNombre().toString());
                        Picasso.with(MainActivity.this).load(login.getFoto().getUrl()).fit().placeholder(R.drawable.ic_usuarios).into(avatarCabecera);

                    } else {
                        Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    private void loadDataCerrarSesion (final String sessionToken ){
        final Call<Login> logoutCall = Utiles.makeServiceWithInterceptors().cerrarSesion(sessionToken);
        logoutCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {
                Login logout = response.body();

                error = response.code();
                Log.i("ErrorValoracionesSitio", String.valueOf(error));
                String cadena = String.valueOf(error);
                String primer_numero = cadena.substring(0, 1);
                int formateado = Integer.parseInt(primer_numero);

                if(formateado == 2){
                    if(logout != null){
                        Toast.makeText(MainActivity.this, "Has cerrado sesión", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Hemos detectado un error. Estamos trabajando para solucionarlo", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, "Error en la URL", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
