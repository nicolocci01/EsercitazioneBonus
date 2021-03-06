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
    Button registerButton, pulisciButton, back;
    Utente utente;
    TextView errorText;

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
        back = findViewById(R.id.backReg);
        pulisciButton = findViewById(R.id.cleanButton);

        utente = new Utente();

        dataText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {

                    dataText.setRawInputType(InputType.TYPE_NULL);
                    dataText.setFocusable(true);
                    new DatePickerFragment().show(
                            getSupportFragmentManager(), DatePickerFragment.TAG);


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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                        Utente.listaUtenti.add(utente);
                        Utente.utenteCorrente = utente;
                        startActivity(intent);
                    }else{
                        errorText.setText("Nome utente gi?? presente");
                    }
                }
            }
        });

        pulisciButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameText.setText("");
                passwordText.setText("");
                confermaPasswordText.setText("");
                dataText.setText("");
                cittaText.setText("");
            }
        });
    }

    public void doPositiveClick(Calendar date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dataText.setText(format.format(date.getTime()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkInput(){
        int errors=0;

        if(usernameText.getText().toString().length()==0){
            errors++;
            usernameText.setError("Inserire il nome utente");
        }
        if(passwordText.getText().toString().length()==0){
            errors++;
            passwordText.setError("Inserire la password");
        }
        if(confermaPasswordText.getText().toString().length()==0){
            errors++;
            confermaPasswordText.setError("Inserire la password");
        }else if(!confermaPasswordText.getText().toString().equals(passwordText.getText().toString())){
            errors++;
            confermaPasswordText.setError("Le password non coincidono");
        }
        if (!passwordText.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)(?=.*[A-Z])(?=.*[!@#&()???:;',?/*~$^+=<>]).{8,16}$")){
            errors++;
            passwordText.setError("La password deve essere lunga almeno 8 caratteri e deve contenere almeno un numero, una lettera maiuscola, " +
                    "una minuscola e un carattere speciale, non pu?? contenere spazi");
        }
        for (Utente p: Utente.listaUtenti) {
            if(p.getUsername().equals(usernameText.getText().toString())){
                errors++;
                usernameText.setError("Questo nome utente ?? gi?? stato utilizzato");
                break;
            }
        }
        if(dataText.getText().toString().length()==0){
            errors++;
            dataText.setError("Inserire la data");
        }
        if(cittaText.getText().toString().length()==0){
            errors++;
            cittaText.requestFocus();
            cittaText.setError("Inserire la citt?? d'origine");
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