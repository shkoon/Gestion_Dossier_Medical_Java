package com.dm.sdia.dossiermedicale.dao.entities;

import java.util.Date;

public class Medecin extends Personne{

    private String username;
    private String motDePasse;
    private String Spécialité;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getSpécialité() {
        return Spécialité;
    }

    public void setSpécialité(String spécialité) {
        Spécialité = spécialité;
    }

    public Medecin(){}
    public Medecin(String username, String motDePasse, String spécialité) {
        this.username = username;
        this.motDePasse = motDePasse;
        Spécialité = spécialité;
    }

    public Medecin(int id, String cin, String nom, String prenom, String adresse, String telephone, String situationFamiliale, String sexe, Date dateNaissance, String username, String motDePasse, String spécialité) {
        super(id, cin, nom, prenom, adresse, telephone, situationFamiliale, sexe, dateNaissance);
        this.username = username;
        this.motDePasse = motDePasse;
        Spécialité = spécialité;
    }

    public Medecin(String cin, String nom, String prenom, String adresse, String telephone, String situationFamiliale, String sexe, Date dateNaissance, String username, String motDePasse, String spécialité) {
        super(cin, nom, prenom, adresse, telephone, situationFamiliale, sexe, dateNaissance);
        this.username = username;
        this.motDePasse = motDePasse;
        Spécialité = spécialité;
    }
}
