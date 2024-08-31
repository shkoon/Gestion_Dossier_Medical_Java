package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Medecin;
import com.dm.sdia.dossiermedicale.dao.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedecinDaoImpl implements MedecinDao{
    Connection connection= DbSingleton.getConnection();
    @Override
    public Medecin login(String username, String mdp) {
        Medecin medecin=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM MEDECIN WHERE USERNAME=? and MOTDEPASSE=?");

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,mdp);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                medecin=new Medecin();
                medecin.setId(resultSet.getInt("ID"));
                medecin.setNom(resultSet.getString("NOM"));
                medecin.setPrenom(resultSet.getString("Prenom"));
                medecin.setSexe(resultSet.getString("SEXE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecin;
    }

    @Override
    public Medecin getByid(int id) {
        Medecin medecin=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM MEDECIN WHERE ID=?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                medecin=new Medecin();
                medecin.setId(resultSet.getInt("ID"));
                medecin.setNom(resultSet.getString("NOM"));
                medecin.setPrenom(resultSet.getString("Prenom"));
                medecin.setSexe(resultSet.getString("SEXE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecin;
    }
}
