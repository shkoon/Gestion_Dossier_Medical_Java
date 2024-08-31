package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Medecin;

public interface MedecinDao {

    public Medecin login(String username,String mdp);

    public Medecin getByid(int id);
}
