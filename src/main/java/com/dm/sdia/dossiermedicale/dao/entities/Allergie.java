package com.dm.sdia.dossiermedicale.dao.entities;

public class Allergie {


    private int id;
    private String nomAllergie;
    private String commentaire;
    private int idPatient;

    public Allergie() {
    }

    public Allergie(int id, String nomAllergie, String commentaire, int idPatient) {
        this.id = id;
        this.nomAllergie = nomAllergie;
        this.commentaire = commentaire;
        this.idPatient = idPatient;
    }

    public Allergie(String nomAllergie, String commentaire, int idPatient) {
        this.nomAllergie = nomAllergie;
        this.commentaire = commentaire;
        this.idPatient = idPatient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAllergie() {
        return nomAllergie;
    }

    public void setNomAllergie(String nomAllergie) {
        this.nomAllergie = nomAllergie;
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
