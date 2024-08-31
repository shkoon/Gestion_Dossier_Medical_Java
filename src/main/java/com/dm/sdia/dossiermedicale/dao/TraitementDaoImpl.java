package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Traitement;
import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TraitementDaoImpl implements TraitementDao {
    Connection connection= DbSingleton.getConnection();
    @Override
    public void save(Traitement o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO TRAITEMENT(LIBELLE,DOSAGE,PERIODE,DATETRAITEMENT,COMMENTAIRE,IDCONS) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,o.getLibelle());
            preparedStatement.setInt(2,o.getDosage());
            preparedStatement.setString(3,o.getPeriode());
            preparedStatement.setDate(4, new Date(o.getDateTraitement().getYear(),o.getDateTraitement().getMonth(),o.getDateTraitement().getDay()));
            preparedStatement.setString(5,o.getCommentaire());
            preparedStatement.setInt(6,o.getIdConsultation());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(Integer o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM TRAITEMENT WHERE ID=?");
            preparedStatement.setInt(1,o);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Traitement getById(Integer o) {
        Traitement traitement=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM TRAITEMENT WHERE ID=?");

            preparedStatement.setInt(1,o);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                traitement=new Traitement();
                traitement.setId(resultSet.getInt("ID"));
                traitement.setLibelle(resultSet.getString("LIBELLE"));
                traitement.setDateTraitement(resultSet.getDate("DATETRAITEMENT"));
                traitement.setCommentaire(resultSet.getString("COMMENTAIRE"));
                traitement.setDosage(resultSet.getInt("DOSAGE"));
                traitement.setCommentaire(resultSet.getString("PERIODE"));
                traitement.setIdConsultation(resultSet.getInt("IDCONS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traitement;
    }

    @Override
    public List<Traitement> getAll() {
        List<Traitement> traitements=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM TRAITEMENT");
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Traitement traitement=new Traitement();
                traitement.setId(resultSet.getInt("ID"));
                traitement.setLibelle(resultSet.getString("LIBELLE"));
                traitement.setDateTraitement(resultSet.getDate("DATETRAITEMENT"));
                traitement.setCommentaire(resultSet.getString("COMMENTAIRE"));
                traitement.setDosage(resultSet.getInt("DOSAGE"));
                traitement.setPeriode(resultSet.getString("PERIODE"));
                traitement.setIdConsultation(resultSet.getInt("IDCONS"));

                traitements.add(traitement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traitements;
    }

    @Override
    public void update(Traitement o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE TRAITEMENT SET LIBELLE=?,COMMENTAIRE=?,DATETRAITEMENT=?,DOSAGE=?,PERIODE=? WHERE ID=?");
            preparedStatement.setString(1,o.getLibelle());
            preparedStatement.setString(2,o.getCommentaire());
            preparedStatement.setDate(3, new Date(o.getDateTraitement().getYear(),o.getDateTraitement().getMonth(),o.getDateTraitement().getDay()));
            preparedStatement.setInt(4,o.getDosage());
            preparedStatement.setString(5,o.getPeriode());
            preparedStatement.setInt(6,o.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Traitement> searchByQuery(String query, int idConsultation) {
        List<Traitement> traitements=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM TRAITEMENT WHERE IDCONS=? and (LIBELLE LIKE ? OR DATETRAITEMENT LIKE ? OR COMMENTAIRE LIKE ? OR DOSAGE LIKE ? OR PERIODE LIKE ?)");
            preparedStatement.setInt(1,idConsultation);

            preparedStatement.setString(2,"%"+query+"%");
            preparedStatement.setString(3,"%"+query+"%");
            preparedStatement.setString(4,"%"+query+"%");
            preparedStatement.setString(5,"%"+query+"%");
            preparedStatement.setString(6,"%"+query+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Traitement traitement=new Traitement();
                traitement.setId(resultSet.getInt("ID"));
                traitement.setLibelle(resultSet.getString("LIBELLE"));
                traitement.setDateTraitement(resultSet.getDate("DATETRAITEMENT"));
                traitement.setCommentaire(resultSet.getString("COMMENTAIRE"));
                traitement.setDosage(resultSet.getInt("DOSAGE"));
                traitement.setPeriode(resultSet.getString("PERIODE"));
                traitement.setIdConsultation(resultSet.getInt("IDCONS"));

                traitements.add(traitement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traitements;
    }

    @Override
    public List<Traitement> searchByConsultation(int idConsultation) {
        List<Traitement> traitements=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM TRAITEMENT WHERE IDCONS=?");
            preparedStatement.setInt(1,idConsultation);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Traitement traitement=new Traitement();
                traitement.setId(resultSet.getInt("ID"));
                traitement.setLibelle(resultSet.getString("LIBELLE"));
                traitement.setDateTraitement(resultSet.getDate("DATETRAITEMENT"));
                traitement.setCommentaire(resultSet.getString("COMMENTAIRE"));
                traitement.setDosage(resultSet.getInt("DOSAGE"));
                traitement.setPeriode(resultSet.getString("PERIODE"));
                traitement.setIdConsultation(resultSet.getInt("IDCONS"));

                traitements.add(traitement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traitements;
    }
}
