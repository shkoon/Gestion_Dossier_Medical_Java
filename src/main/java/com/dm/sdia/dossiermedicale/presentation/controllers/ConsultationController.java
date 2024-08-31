package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Consultation;

import com.dm.sdia.dossiermedicale.presentation.compnents.InterfaceOpener;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.IConsultationService;
import com.dm.sdia.dossiermedicale.service.IConsultationServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonEdit;

    @FXML
    private Button ButtonTraitement;

    @FXML
    private DatePicker DatePickerCons;

    @FXML
    private TextField TextFieldConclusion;

    @FXML
    private TextField TextFieldConduit;

    @FXML
    private TextField TextFieldDiagnostic;

    @FXML
    private TextField TextFieldSearch;

    @FXML
    private TextField TextFieldType;

    @FXML
    private TableColumn<Consultation, String> TableColumnConclusion;

    @FXML
    private TableColumn<Consultation, String> TableColumnConduit;

    @FXML
    private TableColumn<Consultation, Date> TableColumnDate;

    @FXML
    private TableColumn<Consultation, String> TableColumnDiagnostic;

    @FXML
    private TableColumn<Consultation, String> TableColumnType;

    @FXML
    private TableView<Consultation> TableViewConsultation;

    private ObservableList<Consultation> consultations= FXCollections.observableArrayList();

    private IConsultationService consultationService=new IConsultationServiceImpl();

    private int idPatient;
    private int idMedecin;

    private static int idConsultation=-1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumnType.setCellValueFactory(new PropertyValueFactory<>("typeConsultation"));
        TableColumnDiagnostic.setCellValueFactory(new PropertyValueFactory<>("diagnosticConsultaion"));
        TableColumnConduit.setCellValueFactory(new PropertyValueFactory<>("conduitConsultation"));
        TableColumnConclusion.setCellValueFactory(new PropertyValueFactory<>("conclusionConsultation"));
        TableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        idPatient=MainController.getIdPatient();
        load();
        TableViewConsultation.setItems(consultations);


        TextFieldSearch.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            consultations.clear();
            consultations.addAll(consultationService.searchConsultationsByQuery(newValue,idPatient,LoginController.getId()));
        });

        ButtonTraitement.setOnAction(e -> {
            try {
                if(idConsultation>-1){
                    InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/dossierpatient/traitement.fxml", "/com/dm/sdia/dossiermedicale/views/dossierpatient/medical.png", "Traitement",ButtonTraitement);
                }
                else {
                    Notification.errorNotification("Error","Selectionner une consultation");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }
    public void viderForme(){
        TextFieldType.setText("");
        TextFieldDiagnostic.setText("");
        TextFieldConduit.setText("");
        TextFieldConclusion.setText("");
        DatePickerCons.setValue(null);

    }
    public void addConsultation(){
        if(isFormeEmpty()){
            Notification.errorNotification("Error","Les zones de textes/date sont vides");
        }
        else {
            Consultation consultation=new Consultation();
            consultation.setTypeConsultation(TextFieldType.getText());
            consultation.setDiagnosticConsultaion(TextFieldDiagnostic.getText());
            consultation.setConduitConsultation(TextFieldConduit.getText());
            consultation.setConclusionConsultation(TextFieldConclusion.getText());
            consultation.setDateConsultation(new Date(DatePickerCons.getEditor().getText()));
            consultation.setIdPatient(idPatient);
            consultationService.addConsultation(consultation);
            load();
            viderForme();
            Notification.errorNotification("Succes","La consultation est ajoutée");
        }
    }

    public void deleteConsultation(){


        if(TableViewConsultation.getSelectionModel().getSelectedItem()==null){
            Notification.errorNotification("Error","Selectionner une consultation");
        }
        else {
            int id=TableViewConsultation.getSelectionModel().getSelectedItem().getId();
            consultationService.deleteConsultation(id);
            load();
            viderForme();
            Notification.errorNotification("Succes","La consultation est supprimée");
        }
    }
    public void editConsultation(){
        if( isFormeEmpty()){
            Notification.errorNotification("Error","Selectionner une consultation/Remplisser toutes les zones de texte");

        }else {
            Consultation consultation=new Consultation();
            consultation.setId(TableViewConsultation.getSelectionModel().getSelectedItem().getId());
            consultation.setTypeConsultation(TextFieldType.getText());
            consultation.setDiagnosticConsultaion(TextFieldDiagnostic.getText());
            consultation.setConduitConsultation(TextFieldConduit.getText());
            consultation.setConclusionConsultation(TextFieldConclusion.getText());
            consultation.setDateConsultation(new Date(DatePickerCons.getEditor().getText()));
            consultation.setIdPatient(idPatient);
            consultationService.updateConsultation(consultation);
            load();
            viderForme();

            Notification.errorNotification("Succes","La consultation est modifée");
        }}
    public void loadForm(){

        if(TableViewConsultation.getSelectionModel().getSelectedItem()!=null){
            Consultation consultation=TableViewConsultation.getSelectionModel().getSelectedItem();

            TextFieldType.setText(consultation.getTypeConsultation());
            TextFieldDiagnostic.setText(consultation.getDiagnosticConsultaion());
            TextFieldConduit.setText(consultation.getConduitConsultation());
            TextFieldConclusion.setText(consultation.getConclusionConsultation());

            DatePickerCons.setValue(LocalDate.parse(consultation.getDateConsultation().toString()));
            idConsultation=consultation.getId();
        }


    }

    public void load(){
        consultations.clear();
        consultations.addAll(consultationService.searchConsultationsByPatient(idPatient,LoginController.getId()));

    }

    public boolean isFormeEmpty(){


        boolean check=false;

        if( TextFieldType.getLength()==0)
            check=true;
        if( TextFieldDiagnostic.getLength()==0)
            check=true;
        if( DatePickerCons.getEditor().getLength()==0)
            check=true;
        if( TextFieldConduit.getLength()==0)
            check=true;
        if( TextFieldConclusion.getLength()==0)
            check=true;

        return check;
    }

    public static int getIdConsultation() {
        return idConsultation;
    }


}
