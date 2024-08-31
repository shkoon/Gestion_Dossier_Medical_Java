package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.ConsultationDao;
import com.dm.sdia.dossiermedicale.dao.ConsultationDaoImpl;
import com.dm.sdia.dossiermedicale.dao.entities.Consultation;

import java.util.List;

public class IConsultationServiceImpl implements IConsultationService{


    private ConsultationDao consultationService= new ConsultationDaoImpl();
    @Override
    public void addConsultation(Consultation o) {
        consultationService.save(o);
    }

    @Override
    public void deleteConsultation(Integer id) {
        consultationService.removeById(id);
    }

    @Override
    public void updateConsultation(Consultation o) {
        consultationService.update(o);
    }

    @Override
    public Consultation getConsultationbyId(Integer id) {
        return consultationService.getById(id);
    }

    @Override
    public List<Consultation> getConsultations() {
        return consultationService.getAll();
    }

    @Override
    public List<Consultation> searchConsultationsByQuery(String query, int idPatient,int idMedecin) {
        return consultationService.searchByQuery(query,idPatient,idMedecin);
    }

    @Override
    public List<Consultation> searchConsultationsByPatient(int idPatient,int idMedecin) {
        return consultationService.searchByPatient(idPatient,idMedecin);
    }
}
