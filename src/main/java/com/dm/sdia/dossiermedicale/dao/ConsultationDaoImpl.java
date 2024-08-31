package com.dm.sdia.dossiermedicale.dao;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.dao.entities.Consultation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl implements ConsultationDao{

    Connection connection= DbSingleton.getConnection();
    @Override
    public List<Consultation> searchByQuery(String query, int idPatient,int idMedecin) {
        List<Consultation> consultations=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM CONSULTATION WHERE IDPATIENT=? AND (TYPECONS LIKE ? or DATECONS LIKE ? or DIAGNOSTICCONS LIKE ? or CONDUITCONS LIKE ? or CONCLUSIONCONS LIKE ?) AND IDMEDECIN=?");
            preparedStatement.setInt(1,idPatient);
            preparedStatement.setString(2,"%"+query+"%");
            preparedStatement.setString(3,"%"+query+"%");
            preparedStatement.setString(4,"%"+query+"%");
            preparedStatement.setString(5,"%"+query+"%");
            preparedStatement.setString(6,"%"+query+"%");
            preparedStatement.setInt(7,idMedecin);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Consultation consultation=new Consultation();
                consultation.setId(resultSet.getInt("ID"));
                consultation.setTypeConsultation(resultSet.getString("TYPECONS"));
                consultation.setDateConsultation(resultSet.getDate("DATECONS"));
                consultation.setDiagnosticConsultaion(resultSet.getString("DIAGNOSTICCONS"));
                consultation.setConduitConsultation(resultSet.getString("CONDUITCONS"));
                consultation.setConclusionConsultation(resultSet.getString("CONCLUSIONCONS"));
                consultation.setIdPatient(resultSet.getInt("IDPATIENT"));
                consultation.setIdPatient(resultSet.getInt("IDMEDECIN"));
                consultations.add(consultation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public List<Consultation> searchByPatient(int idPatient,int idMedecin) {
        List<Consultation> consultations=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM CONSULTATION WHERE IDPATIENT=? AND IDMEDECIN=?");
            preparedStatement.setInt(1,idPatient);
            preparedStatement.setInt(2,idMedecin);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Consultation consultation=new Consultation();
                consultation.setId(resultSet.getInt("ID"));
                consultation.setTypeConsultation(resultSet.getString("TYPECONS"));
                consultation.setDateConsultation(resultSet.getDate("DATECONS"));
                consultation.setDiagnosticConsultaion(resultSet.getString("DIAGNOSTICCONS"));
                consultation.setConduitConsultation(resultSet.getString("CONDUITCONS"));
                consultation.setConclusionConsultation(resultSet.getString("CONCLUSIONCONS"));
                consultation.setIdPatient(resultSet.getInt("IDPATIENT"));
                consultation.setIdMedecin(resultSet.getInt("IDMEDECIN"));
                consultations.add(consultation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void save(Consultation o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO CONSULTATION(TYPECONS,DIAGNOSTICCONS,CONDUITCONS,CONCLUSIONCONS,DATECONS,IDPATIENT) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,o.getTypeConsultation());

            preparedStatement.setString(2,o.getDiagnosticConsultaion());
            preparedStatement.setString(3,o.getConduitConsultation());
            preparedStatement.setString(4,o.getConclusionConsultation());
            preparedStatement.setDate(5, new Date(o.getDateConsultation().getYear(),o.getDateConsultation().getMonth(),o.getDateConsultation().getDay()));
            preparedStatement.setInt(6,o.getIdPatient());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(Integer o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM CONSULTATION WHERE ID=?");
            preparedStatement.setInt(1,o);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Consultation getById(Integer o) {
        Consultation consultation=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM CONSULTATION WHERE ID=?");
            preparedStatement.setInt(1,o);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                consultation=new Consultation();
                consultation.setId(resultSet.getInt("ID"));
                consultation.setTypeConsultation(resultSet.getString("TYPECONS"));
                consultation.setDateConsultation(resultSet.getDate("DATECONS"));
                consultation.setDiagnosticConsultaion(resultSet.getString("DIAGNOSTICCONS"));
                consultation.setConduitConsultation(resultSet.getString("CONDUITCONS"));
                consultation.setConclusionConsultation(resultSet.getString("CONCLUSIONCONS"));
                consultation.setIdPatient(resultSet.getInt("IDPATIENT"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultation;
    }

    @Override
    public List<Consultation> getAll() {
        List<Consultation> consultations=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM CONSULTATION");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Consultation consultation=new Consultation();
                consultation.setId(resultSet.getInt("ID"));
                consultation.setTypeConsultation(resultSet.getString("TYPECONS"));
                consultation.setDateConsultation(resultSet.getDate("DATECONS"));
                consultation.setDiagnosticConsultaion(resultSet.getString("DIAGNOSTICCONS"));
                consultation.setConduitConsultation(resultSet.getString("CONDUITCONS"));
                consultation.setConclusionConsultation(resultSet.getString("CONCLUSIONCONS"));
                consultation.setIdPatient(resultSet.getInt("IDPATIENT"));
                consultations.add(consultation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void update(Consultation o) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE CONSULTATION SET TYPECONS=?,DIAGNOSTICCONS=?,CONDUITCONS=?,CONCLUSIONCONS=?,DATECONS=? WHERE ID=?");
            preparedStatement.setString(1,o.getTypeConsultation());
            preparedStatement.setString(2,o.getDiagnosticConsultaion());
            preparedStatement.setString(3,o.getConduitConsultation());
            preparedStatement.setString(4,o.getConclusionConsultation());
            preparedStatement.setDate(5, new Date(o.getDateConsultation().getYear(),o.getDateConsultation().getMonth(),o.getDateConsultation().getDay()));
            preparedStatement.setInt(6,o.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
