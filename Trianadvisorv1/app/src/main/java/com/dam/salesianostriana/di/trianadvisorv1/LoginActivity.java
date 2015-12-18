package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Login;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    EditText user, pass;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().hide(); Si la dejo puesta da error de version support v7

        user = (EditText) findViewById(R.id.usuario);
        pass = (EditText) findViewById(R.id.pass);
        register = (Button) findViewById(R.id.btn_entrar);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = user.getText().toString();
                String passw = pass.getText().toString();

                if (checkInternet()==true) {
                    loadDataLogin(usuario, passw);
                } else {
                    checkInternet();
                }

            }
        });

    }

    private void loadDataLogin(final String user, final String password){

        final Call<Login> loginCall = Utiles.makeServiceWithInterceptors().obtenerLogin(user, password);
        loginCall.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {
                Login login = response.body();

                if (login != null) {
                    if (login.getUsername().equals(user) && password.equals("12345")) {
                        LoginActivity.this.finish();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        String sessionToken = login.getSessionToken();
                        i.putExtra("sessionToken", sessionToken);
                        startActivity(i);

                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private boolean checkInternet() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null || !i.isConnected() || !i.isAvailable()) {
            builder = new AlertDialog.Builder(this);

            builder.setTitle(R.string.alert_title_internet);
            builder.setMessage(R.string.alert_need_internet_to_download);
            builder.setPositiveButton(R.string.dialog_settings_internet,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lanzo un intent implícito que hace que se abra
                            // la pantalla de Configuración del móvil
                            // para que el usuario se conecte a Internet
                            // Internet
                            Intent intent = new Intent(
                                    Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    });

            builder.setNegativeButton(R.string.alert_dialog_cancel,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();


                        }
                    });

            builder.create();
            builder.show();
            return false;

        } else {

            // Si hay conexión...
            return true;
        }

    }


}
