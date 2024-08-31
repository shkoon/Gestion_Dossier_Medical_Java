package com.dm.sdia.dossiermedicale.service;


import com.dm.sdia.dossiermedicale.dao.entities.Antecedent;

import java.util.List;

public interface IAntecedentService {
    void addAntecedent(Antecedent o);
    void deleteAntecedent(Integer id);
    void updateAntecedent(Antecedent o);
    Antecedent getAntecedentbyId(Integer id);
    List<Antecedent> getAllAntecedents();
    List<Antecedent> searchAntecedentsByQuery(String query,int idPatient);
    List<Antecedent> searchAntecedentsByPatient(int idPatient);
}
