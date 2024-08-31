package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Patient;

import java.util.List;

public interface PatientDao extends Dao<Patient,Integer>{

    List<Patient> searchByQuery(String query);
}
