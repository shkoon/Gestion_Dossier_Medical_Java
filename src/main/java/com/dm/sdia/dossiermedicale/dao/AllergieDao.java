package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;

import java.util.List;

public interface AllergieDao extends Dao<Allergie,Integer>{

    List<Allergie> searchByQuery(String query,int idPatient);
    List<Allergie> searchByPatient(int idPatient);
}
