package com.example.esercitazionebonus3;

import java.io.Serializable;
import java.util.ArrayList;

public class Utente implements Serializable {

    public static ArrayList<Utente> listaUtenti= new ArrayList<>();
    public static Utente utenteCorrente;
    private String username, password, data, citta;
    private boolean adminPrivilege;

    public Utente(String username, String password, String data, String citta){
        this.username = username;
        this.password = password;
        this.data=data;
        this.citta=citta;
        this.adminPrivilege = false;
    }

    public Utente(String username,String password, Boolean adminPrivilege){
        this.username = username;
        this.password = password;
        this.data = "";
        this.citta = "";
        this.adminPrivilege = adminPrivilege;
    }

    public Utente(){
        this.username = "";
        this.password = "";
        this.data = "";
        this.citta = "";
        this.adminPrivilege = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public boolean getAdminPrivilege() {
        return adminPrivilege;
    }

    public void setAdminPrivilege(boolean adminPrivilege) {
        this.adminPrivilege = adminPrivilege;
    }

    public boolean equals(Utente u){
        if(this.username.equals(u.username)){
            if(this.password.equals(u.password)){
                if(this.data.equals(u.data)){
                    if(this.citta.equals(u.citta)){
                        if(this.adminPrivilege == u.adminPrivilege){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean equals(String username){
        if (this.username.equals(username))
            return true;

        return false;
    }

}
