package com.dam.salesianostriana.di.trianadvisorv1.actitities;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.salesianostriana.di.trianadvisorv1.R;
import com.dam.salesianostriana.di.trianadvisorv1.frgament.SitiosFragment;
import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Login;
import com.dam.salesianostriana.di.trianadvisorv1.utiles.Utiles;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView avatarCabecera;
    TextView nombreCabecera;
    SharedPreferences prefs;
    String sessionToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        //Recoger sessionToken obtenido del login con los extras.
        /*
        Bundle extras = getIntent().getExtras();
        String sessionToken="";
        if(extras!=null){
           sessionToken =  extras.getString("sessionToken");
        }*/

        prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        sessionToken = prefs.getString("sessionToken", null);

        // GREENDAO (HITO 2)
        // Chequeo la conexión. Si no la hay dejo el icono y el nombre de usuario anónimos
        if(Utiles.checkInternet(MainActivity.this)){
            loadDataSessionToken(sessionToken,sessionToken);
        } else {
            avatarCabecera.setImageResource(R.drawable.ic_usuarios);
            nombreCabecera.setText("Anónimo");
        }


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

            /////////////////////////////////////////
            // FUNCIONALIDAD INTEGRADA PARA HITO 2 //
            /////////////////////////////////////////
            SharedPreferences.Editor editor;

            // GREENDAO (HITO 2)
            // Chequeo la conexión. Borrará sessiontoken de las preferencias y nos llevará a LoginActivity
            if(Utiles.checkInternet(MainActivity.this)){

                cancelDataSessionToken(sessionToken);

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                editor = prefs.edit();
                editor.remove("sessionToken");
                editor.apply();
                startActivity(i);
                this.finish();
            } else {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                editor = prefs.edit();
                editor.remove("sessionToken");
                editor.apply();
                startActivity(i);
                this.finish();
            }

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

        final Call<Login> loginCall = Utiles.pedirServicioConInterceptores().obtenerMisDatos(sessionToken, sessionToken1);
        loginCall.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {
                Login login = response.body();

                if (login != null) {
                    if (login.getSessionToken().equals(sessionToken)) {
                        nombreCabecera.setText(login.getNombre().toString());
                        Picasso.with(MainActivity.this).load(login.getFoto().getUrl()).fit().placeholder(R.drawable.ic_usuarios).into(avatarCabecera);


                    } else {
                        Toast.makeText(MainActivity.this, "Fallo de usuario o contraseña", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Fallo de usuario o contraseña", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    private void cancelDataSessionToken(final String sessionToken){

        final Call<Login> loginCall = Utiles.pedirServicioConInterceptores().cerrarSesion(sessionToken);
        loginCall.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {

                Toast.makeText(MainActivity.this, "Cerrar sesión", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
