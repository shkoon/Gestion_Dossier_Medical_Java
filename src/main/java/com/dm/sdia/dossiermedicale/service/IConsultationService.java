package com.dm.sdia.dossiermedicale.service;


import com.dm.sdia.dossiermedicale.dao.entities.Consultation;

import java.util.List;

public interface IConsultationService {

    void addConsultation(Consultation o);
    void deleteConsultation(Integer id);
    void updateConsultation(Consultation o);
    Consultation getConsultationbyId(Integer id);
    List<Consultation> getConsultations();
    List<Consultation> searchConsultationsByQuery(String query,int idPatient,int idMedecin);
    List<Consultation> searchConsultationsByPatient(int idPatient,int idMedecin);
}
