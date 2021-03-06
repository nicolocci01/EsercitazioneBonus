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
    Button logout, modificaPassword, admin;

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
        admin = findViewById(R.id.adminButton);

        benvenuto.setText("Benvenuto " + Utente.utenteCorrente.getUsername().trim());
        username.setText(Utente.utenteCorrente.getUsername());
        dataText.setText(Utente.utenteCorrente.getData());
        cittaText.setText(Utente.utenteCorrente.getCitta());

        if(Utente.utenteCorrente.getAdminPrivilege())
            admin.setVisibility(View.VISIBLE);
        else
            admin.setVisibility(View.GONE);



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

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }
}