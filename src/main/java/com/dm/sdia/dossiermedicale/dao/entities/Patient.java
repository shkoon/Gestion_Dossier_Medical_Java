package com.dm.sdia.dossiermedicale.dao.entities;

import java.util.Date;

public class Patient extends Personne{

    private String mutuelle;

    public Patient(){}
    public Patient(int id, String cin, String nom, String prenom, String adresse, String telephone, String situationFamiliale, String sexe, Date dateNaissance, String mutuelle) {
        super(id, cin, nom, prenom, adresse, telephone, situationFamiliale, sexe, dateNaissance);
        this.mutuelle = mutuelle;
    }

    public Patient(String cin, String nom, String prenom, String adresse, String telephone, String situationFamiliale, String sexe, Date dateNaissance, String mutuelle) {
        super(cin, nom, prenom, adresse, telephone, situationFamiliale, sexe, dateNaissance);
        this.mutuelle = mutuelle;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }
}
