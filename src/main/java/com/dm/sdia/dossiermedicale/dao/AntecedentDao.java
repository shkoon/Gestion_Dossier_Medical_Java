package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.dao.entities.Antecedent;

import java.util.List;

public interface AntecedentDao extends Dao<Antecedent,Integer> {

    List<Antecedent> searchByQuery(String query,int idPatient);
    List<Antecedent> searchByPatient(int idPatient);
}
