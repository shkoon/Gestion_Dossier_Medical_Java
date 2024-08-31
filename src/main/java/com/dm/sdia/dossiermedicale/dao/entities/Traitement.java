package com.dm.sdia.dossiermedicale.dao.entities;

import java.util.Date;

public class Traitement {

    private int id;
    private String libelle;
    private int dosage;
    private String periode;
    private Date dateTraitement;
    private String commentaire;
    private int idConsultation;
    public Traitement(){}
    public Traitement(int id, String libelle, int dosage, String periode, Date dateTraitement, String commentaire, int idConsultation) {
        this.id = id;
        this.libelle = libelle;
        this.dosage = dosage;
        this.periode = periode;
        this.dateTraitement = dateTraitement;
        this.commentaire = commentaire;
        this.idConsultation = idConsultation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }
}
