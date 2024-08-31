package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Antecedent;
import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaccinDaoImpl implements VaccinDao{
    Connection connection= DbSingleton.getConnection();
    @Override
    public void save(Vaccin o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO VACCIN(LIBELLE,DATEVACCIN,COMMENTAIRE,IDPATIENT) VALUES(?,?,?,?)");
            preparedStatement.setString(1,o.getLibelle());
            preparedStatement.setDate(2, new Date(o.getDateVaccin().getYear(),o.getDateVaccin().getMonth(),o.getDateVaccin().getDay()));
            preparedStatement.setString(3,o.getCommentaire());
            preparedStatement.setInt(4,o.getIdPatient());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeById(Integer o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM VACCIN WHERE ID=?");
            preparedStatement.setInt(1,o);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Vaccin getById(Integer o) {
        Vaccin vaccin=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM VACCIN WHERE ID=?");

            preparedStatement.setInt(1,o);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                vaccin=new Vaccin();
                vaccin.setId(resultSet.getInt("ID"));
                vaccin.setLibelle(resultSet.getString("LIBELLE"));
                vaccin.setDateVaccin(resultSet.getDate("DATEVACCIN"));
                vaccin.setCommentaire(resultSet.getString("COMMENTAIRE"));
                vaccin.setIdPatient(resultSet.getInt("IDPATIENT"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccin;
    }

    @Override
    public List<Vaccin> getAll() {
        List<Vaccin> vaccins=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM VACCIN");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Vaccin vaccin=new Vaccin();
                vaccin.setId(resultSet.getInt("ID"));
                vaccin.setLibelle(resultSet.getString("LIBELLE"));
                vaccin.setDateVaccin(resultSet.getDate("DATEVACCIN"));
                vaccin.setCommentaire(resultSet.getString("COMMENTAIRE"));
                vaccin.setIdPatient(resultSet.getInt("IDPATIENT"));
                vaccins.add(vaccin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccins;
    }

    @Override
    public void update(Vaccin o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE VACCIN SET LIBELLE=?,COMMENTAIRE=?,DATEVACCIN=? WHERE ID=?");
            preparedStatement.setString(1,o.getLibelle());
            preparedStatement.setString(2,o.getCommentaire());
            preparedStatement.setDate(3, new Date(o.getDateVaccin().getYear(),o.getDateVaccin().getMonth(),o.getDateVaccin().getDay()));
            preparedStatement.setInt(4,o.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Vaccin> searchByQuery(String query,int idPatient) {
        List<Vaccin> vaccins=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM VACCIN WHERE IDPATIENT=? AND (LIBELLE LIKE ? or DATEVACCIN LIKE ? or COMMENTAIRE LIKE ?)");
            preparedStatement.setInt(1,idPatient);
            preparedStatement.setString(2,"%"+query+"%");
            preparedStatement.setString(3,"%"+query+"%");
            preparedStatement.setString(4,"%"+query+"%");


            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Vaccin vaccin=new Vaccin();
                vaccin.setId(resultSet.getInt("ID"));
                vaccin.setLibelle(resultSet.getString("LIBELLE"));
                vaccin.setDateVaccin(resultSet.getDate("DATEVACCIN"));
                vaccin.setCommentaire(resultSet.getString("COMMENTAIRE"));
                vaccin.setIdPatient(resultSet.getInt("IDPATIENT"));
                vaccins.add(vaccin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccins;
    }

    @Override
    public List<Vaccin> searchByPatient(int idPatient) {
        List<Vaccin> vaccins=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM VACCIN WHERE IDPATIENT=?");
            preparedStatement.setInt(1,idPatient);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Vaccin vaccin=new Vaccin();
                vaccin.setId(resultSet.getInt("ID"));
                vaccin.setLibelle(resultSet.getString("LIBELLE"));
                vaccin.setDateVaccin(resultSet.getDate("DATEVACCIN"));
                vaccin.setCommentaire(resultSet.getString("COMMENTAIRE"));
                vaccin.setIdPatient(resultSet.getInt("IDPATIENT"));
                vaccins.add(vaccin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccins;

    }
}
