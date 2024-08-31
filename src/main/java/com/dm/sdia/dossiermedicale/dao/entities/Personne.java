package com.dm.sdia.dossiermedicale.dao.entities;

import java.util.Date;

public abstract class Personne {
    private int id;
    private String cin;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;
    private String telephone;
    private String situationFamiliale;
    private String sexe;

    public Personne(){}
    public Personne(int id, String cin, String nom, String prenom, String adresse, String telephone, String situationFamiliale, String sexe,Date dateNaissance) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.situationFamiliale = situationFamiliale;
        this.sexe = sexe;
        this.dateNaissance=dateNaissance;
    }

    public Personne(String cin, String nom, String prenom, String adresse, String telephone, String situationFamiliale, String sexe,Date dateNaissance) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.situationFamiliale = situationFamiliale;
        this.sexe = sexe;
        this.dateNaissance=dateNaissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
