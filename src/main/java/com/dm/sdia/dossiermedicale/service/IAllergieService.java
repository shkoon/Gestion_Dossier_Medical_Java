package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;


import java.util.List;

public interface IAllergieService {

    void addAllergie(Allergie o);
    void deleteAllergie(Integer id);
    void updateAllergie(Allergie o);
    Allergie getAllergiebyId(Integer id);
    List<Allergie> getAllAllergies();
    List<Allergie> searchAllergiesByQuery(String query,int idPatient);
    List<Allergie> searchAllergiesByPatient(int idPatient);
}

