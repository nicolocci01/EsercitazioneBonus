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
    TextView registrati, error;
    EditText username, password;
    Utente utente = new Utente();
    Boolean foundUsernameFlag = false, foundPasswordFlag = false;

    public static String PERSONA_EXTRA= "com.example.esercitazioneBonus.Persona";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utente.listaUtenti.add(new Utente("admin", "admin", true));

        username = findViewById(R.id.attrUsernameLogin);
        password = findViewById(R.id.attrPasswordLogin);
        error = findViewById(R.id.errorTextLogin);
        loginButton=findViewById(R.id.login);
        registrati=findViewById(R.id.registrati);
        registrati.setText("Clicca qui per registrarti");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                for(Utente u : Utente.listaUtenti){
                    if(username.getText().toString().equals(u.getUsername())){
                        if(password.getText().toString().equals(u.getPassword())) {
                            foundPasswordFlag = true;
                            utente = u;
                            break;
                        }
                        foundUsernameFlag = true;
                    }
                }
                if(utente.getUsername().equals("admin") && foundPasswordFlag) {
                    intent = new Intent(LoginActivity.this, AdminActivity.class);
                    Utente.utenteCorrente = utente;
                    startActivity(intent);
                }else if (foundPasswordFlag){
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Utente.utenteCorrente = utente;
                    startActivity(intent);
                }else{
                    if(!foundPasswordFlag || !foundUsernameFlag)
                        error.setVisibility(View.VISIBLE);
                }
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