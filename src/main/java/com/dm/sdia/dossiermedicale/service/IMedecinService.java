package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.entities.Medecin;

public interface IMedecinService {

    public Medecin seConnecter(String username,String password);
    public Medecin getMedecinByid(int id);
}
