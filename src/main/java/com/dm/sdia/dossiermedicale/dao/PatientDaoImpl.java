package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao{
    Connection connection= DbSingleton.getConnection();
    @Override
    public void save(Patient o) {

        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO PATIENT(NOM,PRENOM,CIN,ADRESSE,TELEPHONE,STF,SEXE,MUTUELLE,DATENAISSANCE) VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,o.getNom());
            preparedStatement.setString(2,o.getPrenom());
            preparedStatement.setString(3,o.getCin());
            preparedStatement.setString(4,o.getAdresse());
            preparedStatement.setString(5,o.getTelephone());
            preparedStatement.setString(6,o.getSituationFamiliale());
            preparedStatement.setString(7,o.getSexe());
            preparedStatement.setString(8,o.getMutuelle());
            preparedStatement.setDate(9, new Date(o.getDateNaissance().getYear(),o.getDateNaissance().getMonth(),o.getDateNaissance().getDay()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(Integer o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM PATIENT WHERE ID=?");

            preparedStatement.setInt(1,o);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient getById(Integer o) {
        Patient patient=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM PATIENT WHERE ID=?");

            preparedStatement.setInt(1,o);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                patient=new Patient();
                patient.setId(resultSet.getInt("ID"));
                patient.setMutuelle(resultSet.getString("MUTUELLE"));
                patient.setAdresse(resultSet.getString("ADRESSE"));
                patient.setCin(resultSet.getString("CIN"));
                patient.setNom(resultSet.getString("NOM"));
                patient.setPrenom(resultSet.getString("Prenom"));
                patient.setSexe(resultSet.getString("SEXE"));
                patient.setDateNaissance(resultSet.getDate("DATENAISSANCE"));
                patient.setTelephone(resultSet.getString("TELEPHONE"));
                patient.setSituationFamiliale(resultSet.getString("STF"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM PATIENT ");

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                Patient patient=new Patient();
                patient.setId(resultSet.getInt("ID"));
                patient.setMutuelle(resultSet.getString("MUTUELLE"));
                patient.setAdresse(resultSet.getString("ADRESSE"));
                patient.setCin(resultSet.getString("CIN"));
                patient.setNom(resultSet.getString("NOM"));
                patient.setPrenom(resultSet.getString("Prenom"));
                patient.setSexe(resultSet.getString("SEXE"));
                patient.setDateNaissance(resultSet.getDate("DATENAISSANCE"));
                patient.setTelephone(resultSet.getString("TELEPHONE"));
                patient.setSituationFamiliale(resultSet.getString("STF"));
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void update(Patient o) {

        try {
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE PATIENT SET NOM=?,PRENOM=?,CIN=?,TELEPHONE=?,ADRESSE=?,SEXE=?,DATENAISSANCE=?,MUTUELLE=?,STF=? WHERE ID=?");


            preparedStatement.setString(1,o.getNom());
            preparedStatement.setString(2,o.getPrenom());
            preparedStatement.setString(3,o.getCin());
            preparedStatement.setString(4,o.getTelephone());
            preparedStatement.setString(5,o.getAdresse());
            preparedStatement.setString(6,o.getSexe());
            preparedStatement.setDate(7, new Date(o.getDateNaissance().getYear(),o.getDateNaissance().getMonth(),o.getDateNaissance().getDay()));
            preparedStatement.setString(8,o.getMutuelle());
            preparedStatement.setString(9,o.getSituationFamiliale());
            preparedStatement.setInt(10,o.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Patient> searchByQuery(String query) {
        List<Patient> patients=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM PATIENT WHERE CIN LIKE ? or PRENOM LIKE ? or NOM LIKE ?");
            preparedStatement.setString(1,"%"+query+"%");
            preparedStatement.setString(2,"%"+query+"%");
            preparedStatement.setString(3,"%"+query+"%");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Patient patient=new Patient();
                patient.setId(resultSet.getInt("ID"));
                patient.setMutuelle(resultSet.getString("MUTUELLE"));
                patient.setAdresse(resultSet.getString("ADRESSE"));
                patient.setCin(resultSet.getString("CIN"));
                patient.setNom(resultSet.getString("NOM"));
                patient.setPrenom(resultSet.getString("Prenom"));
                patient.setSexe(resultSet.getString("SEXE"));
                patient.setDateNaissance(resultSet.getDate("DATENAISSANCE"));
                patient.setTelephone(resultSet.getString("TELEPHONE"));
                patient.setSituationFamiliale(resultSet.getString("STF"));
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
}
