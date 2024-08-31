package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Traitement;


import java.util.List;

public interface TraitementDao extends Dao<Traitement,Integer> {

    List<Traitement> searchByQuery(String query, int idConsultation);
    List<Traitement> searchByConsultation(int idConsultation);
}
