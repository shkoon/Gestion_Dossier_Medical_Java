package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.dao.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllergieDaoImpl implements  AllergieDao{

    Connection connection= DbSingleton.getConnection();
    @Override
    public List<Allergie> searchByQuery(String query,int idPatient) {

        List<Allergie> allergies=new ArrayList<>();

        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ALLERGIE WHERE IDPATIENT=? AND (NOMALLERGIE LIKE ? or COMMENTAIRE LIKE ?)");
            preparedStatement.setInt(1,idPatient);
            preparedStatement.setString(2,"%"+query+"%");
            preparedStatement.setString(3,"%"+query+"%");

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                Allergie allergie=new Allergie();
                allergie.setId(resultSet.getInt("ID"));
                allergie.setNomAllergie(resultSet.getString("NOMALLERGIE"));
                allergie.setCommentaire(resultSet.getString("COMMENTAIRE"));
                allergie.setIdPatient(resultSet.getInt("IDPATIENT"));
                allergies.add(allergie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allergies;
    }

    @Override
    public List<Allergie> searchByPatient(int idPatient) {
        List<Allergie> allergies=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ALLERGIE WHERE IDPATIENT=? ");
            preparedStatement.setInt(1,idPatient);

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                Allergie allergie=new Allergie();
                allergie.setId(resultSet.getInt("ID"));
                allergie.setNomAllergie(resultSet.getString("NOMALLERGIE"));
                allergie.setCommentaire(resultSet.getString("COMMENTAIRE"));
                allergie.setIdPatient(resultSet.getInt("IDPATIENT"));
                allergies.add(allergie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allergies;
    }

    @Override
    public void save(Allergie o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO ALLERGIE(NOMALLERGIE,COMMENTAIRE,IDPATIENT) VALUES(?,?,?)");
            preparedStatement.setString(1,o.getNomAllergie());
            preparedStatement.setString(2,o.getCommentaire());
            preparedStatement.setInt(3,o.getIdPatient());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(Integer o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM ALLERGIE WHERE ID=?");

            preparedStatement.setInt(1,o);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Allergie getById(Integer o) {

        Allergie allergie=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ALLERGIE WHERE ID=?");

            preparedStatement.setInt(1,o);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                allergie=new Allergie();
                allergie.setId(resultSet.getInt("ID"));
                allergie.setNomAllergie(resultSet.getString("NOMALLERGIE"));
                allergie.setCommentaire(resultSet.getString("COMMENTAIRE"));
                allergie.setIdPatient(resultSet.getInt("IDPATIENT"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  allergie;
    }

    @Override
    public List<Allergie> getAll() {
        List<Allergie> allergies=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ALLERGIE ");

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                Allergie allergie=new Allergie();
                allergie.setId(resultSet.getInt("ID"));
                allergie.setNomAllergie(resultSet.getString("NOMALLERGIE"));
                allergie.setCommentaire(resultSet.getString("COMMENTAIRE"));
                allergie.setIdPatient(resultSet.getInt("IDPATIENT"));
                allergies.add(allergie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allergies;
    }

    @Override
    public void update(Allergie o) {

        try {
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE ALLERGIE SET NOMALLERGIE=?,COMMENTAIRE=? WHERE ID=?");
            preparedStatement.setString(1,o.getNomAllergie());
            preparedStatement.setString(2,o.getCommentaire());
            preparedStatement.setInt(3,o.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
