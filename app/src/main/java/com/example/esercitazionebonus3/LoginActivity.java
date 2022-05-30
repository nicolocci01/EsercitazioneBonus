package com.example.esercitazionebonus3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button loginButton; //registerButton;
    TextView registrati;
    EditText username, password;
    Utente utente;
    Boolean loginFlag = false;
    public static String PERSONA_EXTRA= "com.example.esercitazioneBonus.Persona";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utente.listaUtenti.add(new Utente("admin", "admin", true));

        username = findViewById(R.id.attrUsernameLogin);
        password = findViewById(R.id.attrPasswordLogin);
        loginButton=findViewById(R.id.login);
        registrati=findViewById(R.id.registrati);
        registrati.setText("Clicca qui per registrarti");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                for(Utente u : Utente.listaUtenti){
                    if(username.getText().toString().equals(u.getUsername()) && password.getText().toString().equals(u.getPassword())){
                        loginFlag=true;
                        utente =u;
                        break;
                    }
                }
                if(utente.getUsername().equals("admin")) {
                    intent = new Intent(LoginActivity.this,HomeActivity.class); //TODO: cambiare quando ci sarà l'activity lista
                }else{
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                }

                Utente.utenteCorrente = utente;
                startActivity(intent);
            }
        });

        registrati.setPaintFlags(registrati.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        registrati.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegistrazioneActivity.class);
                startActivity(intent);
            }
        });

    }
}