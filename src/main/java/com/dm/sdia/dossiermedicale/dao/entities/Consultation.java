package com.dm.sdia.dossiermedicale.dao.entities;

import java.util.Date;

public class Consultation {

    private int id;
    private Date dateConsultation;
    private String typeConsultation;
    private String diagnosticConsultaion;
    private String conclusionConsultation;
    private String conduitConsultation;
    private int idPatient;
    private int idMedecin;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getTypeConsultation() {
        return typeConsultation;
    }

    public void setTypeConsultation(String typeConsultation) {
        this.typeConsultation = typeConsultation;
    }

    public String getDiagnosticConsultaion() {
        return diagnosticConsultaion;
    }

    public void setDiagnosticConsultaion(String diagnosticConsultaion) {
        this.diagnosticConsultaion = diagnosticConsultaion;
    }

    public String getConclusionConsultation() {
        return conclusionConsultation;
    }

    public void setConclusionConsultation(String conclusionConsultation) {
        this.conclusionConsultation = conclusionConsultation;
    }

    public String getConduitConsultation() {
        return conduitConsultation;
    }

    public void setConduitConsultation(String conduitConsultation) {
        this.conduitConsultation = conduitConsultation;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }
}
