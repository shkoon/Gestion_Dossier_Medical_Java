package com.dm.sdia.dossiermedicale.dao.entities;

import java.util.Date;

public class Vaccin {

    private int id;
    private String libelle;
    private Date dateVaccin;
    private String commentaire;
    private int idPatient;

    public Vaccin() {
    }

    public Vaccin(int id, String libelle, Date dateVaccin, String commentaire, int idPatient) {
        this.id = id;
        this.libelle = libelle;
        this.dateVaccin = dateVaccin;
        this.commentaire = commentaire;
        this.idPatient = idPatient;
    }

    public Vaccin(String libelle, Date dateVaccin, String commentaire, int idPatient) {
        this.libelle = libelle;
        this.dateVaccin = dateVaccin;
        this.commentaire = commentaire;
        this.idPatient = idPatient;
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

    public Date getDateVaccin() {
        return dateVaccin;
    }

    public void setDateVaccin(Date dateVaccin) {
        this.dateVaccin = dateVaccin;
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
