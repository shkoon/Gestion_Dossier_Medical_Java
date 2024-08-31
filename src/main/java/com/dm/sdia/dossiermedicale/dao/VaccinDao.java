package com.dm.sdia.dossiermedicale.dao;
import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;

import java.util.List;

public interface VaccinDao extends Dao<Vaccin,Integer>{

    List<Vaccin> searchByQuery(String query,int idPatient);
    List<Vaccin> searchByPatient(int idPatient);
}
