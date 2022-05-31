package com.example.esercitazionebonus3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificaPasswordActivity extends AppCompatActivity {
    EditText password, conferma;
    Button back, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_password);

        password = findViewById(R.id.attrPasswordMod);
        conferma = findViewById(R.id.attrConfermaMod);
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
                Boolean errors=false;
                if (!password.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)(?=.*[A-Z])(?=.*[!@#&()–:;',?/*~$^+=<>]).{8,16}$")){
                    errors=true;
                    password.setError("La password deve essere lunga almeno 8 caratteri e deve contenere almeno un numero, una lettera maiuscola, " +
                            "una minuscola e un carattere speciale, non può contenere spazi");
                }
                if(!conferma.getText().toString().equals(password.getText().toString())){
                    errors=true;
                    conferma.setError("Le password non coincidono");
                }
                if(password.getText().toString().length()==0){
                    errors=true;
                    password.setError("Inserire la password");
                }
                if(conferma.getText().toString().length()==0){
                    errors=true;
                    password.setError("Inserire la password");
                }
                if(!errors){
                    Utente.listaUtenti.remove(Utente.utenteCorrente);
                    Utente.utenteCorrente.setPassword(password.getText().toString());
                    Utente.listaUtenti.add(Utente.utenteCorrente);
                    //PopupWindow popupWindow = new PopupWindow();
                    //popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                    //popupWindow.setOnDismissListener();
                    Intent intent = new Intent(ModificaPasswordActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

}