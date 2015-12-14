package com.dam.salesianostriana.di.trianadvisorv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                LoginActivity.this.finish();
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }


}
