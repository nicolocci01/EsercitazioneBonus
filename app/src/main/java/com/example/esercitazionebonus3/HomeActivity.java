package com.example.esercitazionebonus3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class HomeActivity extends AppCompatActivity {
    TextView username, dataText, cittaText, benvenuto;
    Utente utenteRicevuto;
    Button logout, modificaPassword;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        benvenuto = findViewById(R.id.benvenuto);
        username = findViewById(R.id.attrUsernameRes);
        dataText = findViewById(R.id.attrDataRes);
        cittaText = findViewById(R.id.attrCittaRes);
        logout = findViewById(R.id.logout);
        modificaPassword = findViewById(R.id.modifica);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(RegistrazioneActivity.PERSONA_EXTRA);    //recuperiamo la nostra persona

        //da sostituire, causa nullpoiner, giustamente anche
        utenteRicevuto = Utente.utenteCorrente;

        benvenuto.setText("Benvenuto " + utenteRicevuto.getUsername().trim());
        username.setText(utenteRicevuto.getUsername());
        dataText.setText(utenteRicevuto.getData());
        cittaText.setText(utenteRicevuto.getCitta());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        modificaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ModificaPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}