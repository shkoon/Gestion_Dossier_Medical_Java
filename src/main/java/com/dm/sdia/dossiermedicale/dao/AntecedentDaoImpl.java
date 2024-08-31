package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.dao.entities.Antecedent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AntecedentDaoImpl implements AntecedentDao{
    Connection connection= DbSingleton.getConnection();
    @Override
    public List<Antecedent> searchByQuery(String query,int idPatient) {

        List<Antecedent> antecedents=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ANTECEDENT WHERE IDPATIENT=? AND (TYPEANTECEDENT LIKE ? or COMMENTAIRE LIKE ?)");
            preparedStatement.setInt(1,idPatient);
            preparedStatement.setString(2,"%"+query+"%");
            preparedStatement.setString(3,"%"+query+"%");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                Antecedent antecedent=new Antecedent();
                antecedent.setId(resultSet.getInt("ID"));
                antecedent.setTypeAntecedent(resultSet.getString("TYPEANTECEDENT"));
                antecedent.setCommentaire(resultSet.getString("COMMENTAIRE"));
                antecedent.setIdPatient(resultSet.getInt("IDPATIENT"));
                antecedents.add(antecedent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return antecedents;
    }

    @Override
    public List<Antecedent> searchByPatient(int idPatient) {
        List<Antecedent> antecedents=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ANTECEDENT WHERE IDPATIENT=?");
            preparedStatement.setInt(1,idPatient);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Antecedent antecedent=new Antecedent();
                antecedent.setId(resultSet.getInt("ID"));
                antecedent.setTypeAntecedent(resultSet.getString("TYPEANTECEDENT"));
                antecedent.setCommentaire(resultSet.getString("COMMENTAIRE"));
                antecedent.setIdPatient(resultSet.getInt("IDPATIENT"));
                antecedents.add(antecedent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return antecedents;
    }

    @Override
    public void save(Antecedent o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO ANTECEDENT(TYPEANTECEDENT,COMMENTAIRE,IDPATIENT) VALUES(?,?,?)");
            preparedStatement.setString(1,o.getTypeAntecedent());
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
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM ANTECEDENT WHERE ID=?");

            preparedStatement.setInt(1,o);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Antecedent getById(Integer o) {
        Antecedent antecedent=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ANTECEDENT WHERE ID=?");

            preparedStatement.setInt(1,o);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                antecedent=new Antecedent();
                antecedent.setId(resultSet.getInt("ID"));
                antecedent.setTypeAntecedent(resultSet.getString("TYPEANTECEDENT"));
                antecedent.setCommentaire(resultSet.getString("COMMENTAIRE"));
                antecedent.setIdPatient(resultSet.getInt("IDPATIENT"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return antecedent;
    }

    @Override
    public List<Antecedent> getAll() {
        List<Antecedent> antecedents=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ANTECEDENT");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Antecedent antecedent=new Antecedent();
                antecedent.setId(resultSet.getInt("ID"));
                antecedent.setTypeAntecedent(resultSet.getString("TYPEANTECEDENT"));
                antecedent.setCommentaire(resultSet.getString("COMMENTAIRE"));
                antecedent.setIdPatient(resultSet.getInt("IDPATIENT"));
                antecedents.add(antecedent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return antecedents;
    }

    @Override
    public void update(Antecedent o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE ANTECEDENT SET TYPEANTECEDENT=?,COMMENTAIRE=? WHERE ID=?");
            preparedStatement.setString(1,o.getTypeAntecedent());
            preparedStatement.setString(2,o.getCommentaire());
            preparedStatement.setInt(3,o.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
