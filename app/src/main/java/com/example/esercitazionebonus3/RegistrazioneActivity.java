package com.example.esercitazionebonus3;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class RegistrazioneActivity extends AppCompatActivity {

    EditText usernameText, passwordText, dataText, confermaPasswordText, cittaText;
    Button registerButton, pulisciButton;
    Utente utente;
    TextView errorText;
    static String PERSONA_EXTRA = "com.example.esercitazioneBonus.Persona";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        usernameText = findViewById(R.id.attrUsername);
        passwordText = findViewById(R.id.attrPassword);
        confermaPasswordText = findViewById(R.id.attrConfermaPassword);
        cittaText = findViewById(R.id.attrCitta);
        dataText = findViewById(R.id.attrData);
        registerButton = findViewById(R.id.salvaButton);
        errorText = findViewById(R.id.errorText);
        pulisciButton = findViewById(R.id.cleanButton);

        utente = new Utente();

        dataText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    //non fa uscire la tastiera
                    dataText.setRawInputType(InputType.TYPE_NULL);
                    dataText.setFocusable(true);
                    new DatePickerFragment().show(
                            getSupportFragmentManager(), DatePickerFragment.TAG);

                    // per non dover cambiare focus prima di ricliccare
                    dataText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //non fa uscire la tastiera
                            dataText.setRawInputType(InputType.TYPE_NULL);
                            dataText.setFocusable(true);
                            new DatePickerFragment().show(
                                    getSupportFragmentManager(), DatePickerFragment.TAG);
                        }
                    });
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (checkInput()) {
                    if(!Utente.listaUtenti.contains(utente)){
                        Intent intent = new Intent(RegistrazioneActivity.this, HomeActivity.class);
                        aggiornaPersona();
                        //intent.putExtra(PERSONA_EXTRA, persona);
                        Utente.listaUtenti.add(utente);
                        Utente.utenteCorrente = utente;
                        startActivity(intent);
                    }else{
                        errorText.setText("");
                    }
                }
            }
        });

        pulisciButton.setOnClickListener(new View.OnClickListener() {
            @Override   //meglio fare getText().clear(), vale per tutti i tipi
            public void onClick(View view) {
                usernameText.setText("");
                passwordText.setText("");
                dataText.setText("");
            }
        });
    }

    public void doPositiveClick(Calendar date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dataText.setText(format.format(date.getTime()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkInput(){
        //return true se tutto è andato a buon fine, false altrimenti
        int errors=0;

        if(usernameText.getText().toString().length()==0){
            errors++;
            usernameText.setError("Inserire il nome utente!");
        }
        if(passwordText.getText().toString().length()==0){
            errors++;
            passwordText.setError("Inserire la password!");
        }
        if(confermaPasswordText.getText().toString().length()==0){
            errors++;
            confermaPasswordText.setError("Inserire la password!");
        }else if(!confermaPasswordText.getText().toString().equals(passwordText.getText().toString())){
            errors++;
            confermaPasswordText.setError("Le password non coincidono");
        }
        for (Utente p: Utente.listaUtenti) {
            if(p.getUsername().equals(usernameText.getText().toString())){
                errors++;
                usernameText.setError("Questo nome utente è già stato utilizzato");
                break;
            }
        }
        if(dataText.getText().toString().length()==0){
            errors++;
            dataText.setError("Inserire la data!");
        }
        if(cittaText.getText().toString().length()==0){
            errors++;
            cittaText.requestFocus();
            cittaText.setError("Inserire la città d'origine");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate dataNascita = LocalDate.parse(dataText.getText().toString(), formatter);

            Period period = Period.between(dataNascita, LocalDate.now());

            if(period.getYears()<18){
                errors++;
                dataText.setError("Non sei maggiorenne");
            }
        }catch (DateTimeException e){
            errors++;
            dataText.setError("Data non valida");
        }

        switch (errors){
            case 0:
                errorText.setVisibility(View.GONE);
                break;
            case 1:
                errorText.setVisibility(View.VISIBLE);
                errorText.setText("Errore");
                break;
            default:
                errorText.setVisibility(View.VISIBLE);
                errorText.setText("Ci sono " + errors + " errori");
                break;
        }
        return errors==0;
    }

    private void aggiornaPersona(){
        String username = usernameText.getText().toString();
        this.utente.setUsername(username);

        String password = passwordText.getText().toString();
        this.utente.setPassword(password);

        String citta =  cittaText.getText().toString();
        this.utente.setCitta(citta);

        String dataInserita = dataText.getText().toString();
        this.utente.setData(dataInserita);

        this.utente.setAdminPrivilege(false);
    }
}