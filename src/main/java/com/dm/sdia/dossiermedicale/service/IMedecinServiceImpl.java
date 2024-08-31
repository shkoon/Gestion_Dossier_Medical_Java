package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.MedecinDao;
import com.dm.sdia.dossiermedicale.dao.MedecinDaoImpl;
import com.dm.sdia.dossiermedicale.dao.entities.Medecin;

public class IMedecinServiceImpl implements IMedecinService{

    private MedecinDao medecinService=new MedecinDaoImpl();
    @Override
    public Medecin seConnecter(String username, String password) {
        return medecinService.login(username, password);
    }

    @Override
    public Medecin getMedecinByid(int id) {
        return medecinService.getByid(id);
    }
}
