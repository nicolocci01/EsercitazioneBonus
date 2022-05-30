package com.example.esercitazionebonus3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificaPasswordActivity extends AppCompatActivity {
    EditText password;
    Button back, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_password);

        password = findViewById(R.id.attrPasswordMod);
        back = findViewById(R.id.back);
        confirm = findViewById(R.id.confirm);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utente.listaUtenti.remove(Utente.utenteCorrente);
                Utente.utenteCorrente.setPassword(password.getText().toString());
                Utente.listaUtenti.add(Utente.utenteCorrente);
                //PopupWindow popupWindow = new PopupWindow();
                //popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                //popupWindow.setOnDismissListener();
                Intent intent = new Intent(ModificaPasswordActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

}