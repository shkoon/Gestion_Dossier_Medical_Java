package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.PatientDao;
import com.dm.sdia.dossiermedicale.dao.PatientDaoImpl;
import com.dm.sdia.dossiermedicale.dao.entities.Patient;

import java.util.List;

public class IPatientServiceImpl implements IPatientService{

    private PatientDao aldi=new PatientDaoImpl();
    @Override
    public void addPatient(Patient o) {
        aldi.save(o);
    }

    @Override
    public void deletePatient(Integer id) {
        aldi.removeById(id);
    }

    @Override
    public void updatePatient(Patient o) {
        aldi.update(o);
    }

    @Override
    public Patient getPatientbyId(Integer id) {
        return aldi.getById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return aldi.getAll();
    }

    @Override
    public List<Patient> searchPatientsByQuery(String query) {
        return aldi.searchByQuery(query);
    }
}
