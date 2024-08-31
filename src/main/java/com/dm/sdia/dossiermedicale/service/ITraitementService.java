package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.entities.Traitement;


import java.util.List;

public interface ITraitementService {

    void addTraitement(Traitement o);
    void deleteTraitement(Integer id);
    void updateTraitement(Traitement o);
    Traitement getVaccinbyId(Integer id);
    List<Traitement> getAllTraitements();
    List<Traitement> searchTraitementsByQuery(String query,int idConsultation);
    List<Traitement> searchTraitementsByConsultation(int idConsultation);
}
