package com.dm.sdia.dossiermedicale.dao.entities;

public class Antecedent {

    private int id;
    private String typeAntecedent;
    private String commentaire;
    private int idPatient;

    public Antecedent() {
    }

    public Antecedent(int id, String typeAntecedent, String commentaire, int idPatient) {
        this.id = id;
        this.typeAntecedent = typeAntecedent;
        this.commentaire = commentaire;
        this.idPatient = idPatient;
    }

    public Antecedent(String typeAntecedent, String commentaire, int idPatient) {
        this.typeAntecedent = typeAntecedent;
        this.commentaire = commentaire;
        this.idPatient = idPatient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeAntecedent() {
        return typeAntecedent;
    }

    public void setTypeAntecedent(String typeAntecedent) {
        this.typeAntecedent = typeAntecedent;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }
}
