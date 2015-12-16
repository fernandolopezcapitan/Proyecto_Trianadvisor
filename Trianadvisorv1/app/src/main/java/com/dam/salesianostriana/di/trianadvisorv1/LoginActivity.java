package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.salesianostriana.di.trianadvisorv1.pojoschema.Login;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

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


                loadDataLogin(usuario, passw);
            }
        });

    }
    private Api makeService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.parse.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);
        return service;
    }
    private void loadDataLogin(final String user, final String password){

        final Call<Login> loginCall = Utiles.makeServiceWithInterceptors().obtenerLogin(user, password);
        loginCall.enqueue(new Callback<Login>(){

            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {
                Login login = response.body();

                if(login!=null){
                    if(login.getUsername().equals(user) && password.equals("12345")){
                        LoginActivity.this.finish();
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        String sessionToken = login.getSessionToken();
                        i.putExtra("sessionToken",sessionToken);
                        startActivity(i);

                    }else{
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


}
