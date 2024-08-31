package com.dm.sdia.dossiermedicale.service;


import com.dm.sdia.dossiermedicale.dao.entities.Patient;

import java.util.List;

public interface IPatientService {
    void addPatient(Patient o);
    void deletePatient(Integer id);
    void updatePatient(Patient o);
    Patient getPatientbyId(Integer id);
    List<Patient> getAllPatients();
    List<Patient> searchPatientsByQuery(String query);

}
