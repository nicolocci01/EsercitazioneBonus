package com.example.esercitazionebonus3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ScrollCaptureCallback;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    ScrollView scrollView;
    LinearLayout layout;
    LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

    LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        draw();
    }

    private void draw(){
        p2.setMargins(0, 10, 0, 0);

        layout = findViewById(R.id.layout);

        for (int i = 0; i < Utente.listaUtenti.size(); i++) {
            if (!Utente.listaUtenti.get(i).getAdminPrivilege()) {

                layout.setOrientation(LinearLayout.VERTICAL);

                TextView username = new TextView(this);
                username.setText(Utente.listaUtenti.get(i).getUsername());
                username.setLayoutParams(params);

                Button makeAdmin = new Button(this);
                makeAdmin.setText("Rendi Admin");
                makeAdmin.setId(i);
                makeAdmin.setLayoutParams(params);

                Button removeUser = new Button(this);
                removeUser.setText("Rimuovi");
                removeUser.setId(i * (-1));
                removeUser.setLayoutParams(params);

                layout.addView(username);

                layout.addView(makeAdmin);

                layout.addView(removeUser);

                makeAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utente.listaUtenti.get(makeAdmin.getId()).setAdminPrivilege(true);
                        layout.removeAllViews();
                        Intent intent = new Intent(AdminActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }
                });

                removeUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utente.listaUtenti.remove(removeUser.getId() * (-1));
                        layout.removeAllViews();
                        Intent intent = new Intent(AdminActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }

        Button home = new Button(this);
        home.setText("Home");
        home.setLayoutParams(p2);
        layout.addView(home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeAllViews();
                Intent intent = new Intent(AdminActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}