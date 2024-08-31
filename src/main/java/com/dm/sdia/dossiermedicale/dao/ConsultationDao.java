package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Consultation;

import java.util.List;

public interface ConsultationDao extends Dao<Consultation,Integer>{

    List<Consultation> searchByQuery(String query, int idPatient, int idMedecin);
    List<Consultation> searchByPatient(int idPatient, int idMedecin);

}
