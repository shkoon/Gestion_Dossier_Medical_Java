package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Patient;
import com.dm.sdia.dossiermedicale.presentation.compnents.InterfaceOpener;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.IPatientService;
import com.dm.sdia.dossiermedicale.service.IPatientServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class PatientController implements Initializable {

    @FXML
    private Button ButtonAllergie;

    @FXML
    private Button ButtonAntecedent;
    @FXML
    private Button ButtonVaccin;

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonConsult;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonEdit;

    @FXML
    private ComboBox<String> ComboBoxSTF;

    @FXML
    private ComboBox<String> ComboBoxSexe;

    @FXML
    private DatePicker DatePickerNaissance;

    @FXML
    private TableColumn<Patient, String> TableColumnAdresse;

    @FXML
    private TableColumn<Patient, String> TableColumnCin;

    @FXML
    private TableColumn<Patient, Date> TableColumnDateNaissance;

    @FXML
    private TableColumn<Patient, String> TableColumnNom;

    @FXML
    private TableColumn<Patient, String> TableColumnPrenom;

    @FXML
    private TableColumn<Patient, String> TableColumnSexe;

    @FXML
    private TableColumn<Patient, String> TableColumnSituation;

    @FXML
    private TableColumn<Patient, String> TableColumnTelephone;

    @FXML
    private TableColumn<Patient,String> TableColumnMutuelle;
    @FXML
    private TableView<Patient> TableViewPatient;

    @FXML
    private TextField TextFieldAdresse;

    @FXML
    private TextField TextFieldCin;

    @FXML
    private TextField TextFieldNom;

    @FXML
    private TextField TextFieldPrenom;

    @FXML
    private TextField TextFieldMutuelle;

    @FXML
    private TextField TextFieldSearch;

    @FXML
    private TextField TextFieldTelephone;

    private static int id=-1;
    private ObservableList<Patient> patients=FXCollections.observableArrayList();
    private ObservableList<String> sexe= FXCollections.observableArrayList();
    private ObservableList<String> stf= FXCollections.observableArrayList();

    private IPatientService patientService=new IPatientServiceImpl();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sexe.addAll("Masculin","Feminin");
        stf.addAll("Célibataire","Veuf(e) ","Divorcé(e)","Marié(e)");
        ComboBoxSTF.setItems(stf);
        ComboBoxSexe.setItems(sexe);
        ComboBoxSexe.setValue("Masculin");
        ComboBoxSTF.setValue("Célibataire");

        TableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TableColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        TableColumnTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        TableColumnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        TableColumnSituation.setCellValueFactory(new PropertyValueFactory<>("situationFamiliale"));
        TableColumnSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        TableColumnDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        TableColumnMutuelle.setCellValueFactory(new PropertyValueFactory<>("mutuelle"));
        load();
        TableViewPatient.setItems(patients);

        TextFieldSearch.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            patients.clear();
            patients.addAll(patientService.searchPatientsByQuery(newValue));
        });

        ButtonAntecedent.setOnAction(e -> {
            try {
                if(PatientController.getId()>-1){
                    InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/patient/antecedent.fxml", "/com/dm/sdia/dossiermedicale/views/patient/antecedents-medicaux.png","Antecedent",ButtonAntecedent);
                }
                else {
                    Notification.errorNotification("Error","Selectionner un Patient");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ButtonAllergie.setOnAction(e -> {
            try {
                if(PatientController.getId()>-1){
                    InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/patient/allergie.fxml", "/com/dm/sdia/dossiermedicale/views/patient/eternuement.png","Allergie",ButtonAllergie);
                }
                else {
                    Notification.errorNotification("Error","Selectionner un Patient");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ButtonVaccin.setOnAction(e -> {
            try {
                if(PatientController.getId()>-1){
                    InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/patient/vaccin.fxml", "/com/dm/sdia/dossiermedicale/views/patient/vaccin.png","Vaccin",ButtonVaccin);
                }
                else {
                    Notification.errorNotification("Error","Selectionner un Patient");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static int getId() {
        return id;
    }

    public void load(){
        patients.clear();
        patients.addAll(patientService.getAllPatients());
    }

    public void ajouterPatient(){

        if(isFormeEmpty()){
            Notification.errorNotification("Error","Les zones de textes/date sont vides");
        }
        else {


        Patient patient=new Patient();
        patient.setNom(TextFieldNom.getText());
        patient.setPrenom(TextFieldPrenom.getText());
        patient.setCin(TextFieldCin.getText());
        patient.setTelephone(TextFieldTelephone.getText());
        patient.setAdresse(TextFieldAdresse.getText());
        Date date=new Date(DatePickerNaissance.getEditor().getText());
        patient.setDateNaissance(date);
        patient.setSituationFamiliale(ComboBoxSTF.getValue());
        patient.setSexe(ComboBoxSexe.getValue());
        patient.setMutuelle(TextFieldMutuelle.getText());
        patientService.addPatient(patient);
        load();
        viderForme();
        Notification.errorNotification("Succes","Patient(e) "+patient.getNom()+" est ajouté(e)");
    }}

    public void supprimerPatient(){




        if( TableViewPatient.getSelectionModel().getSelectedItem()==null){
            Notification.errorNotification("Error","Selectionner un patient");
        }
        else {
            int id= TableViewPatient.getSelectionModel().getSelectedItem().getId();
            patientService.deletePatient(id);
            load();
            viderForme();
            Notification.errorNotification("Succes","Patient(e) est supprimé(e)");}
    }
    public void loadForm(){
        if(TableViewPatient.getSelectionModel().getSelectedItem()!=null){
            Patient patient=TableViewPatient.getSelectionModel().getSelectedItem();
            TextFieldNom.setText(patient.getNom());
            TextFieldPrenom.setText(patient.getPrenom());
            TextFieldCin.setText(patient.getCin());
            TextFieldAdresse.setText(patient.getAdresse());
            TextFieldTelephone.setText(patient.getTelephone());
            ComboBoxSexe.setValue(patient.getSexe());
            ComboBoxSTF.setValue(patient.getSituationFamiliale());
            TextFieldMutuelle.setText(patient.getMutuelle());
            DatePickerNaissance.setValue(LocalDate.parse(patient.getDateNaissance().toString()));
            id=patient.getId();
        }



    }
    public void viderForme(){
        TextFieldMutuelle.setText("");
        TextFieldNom.setText("");
        TextFieldPrenom.setText("");
        TextFieldCin.setText("");
        TextFieldAdresse.setText("");
        TextFieldTelephone.setText("");
        id=-1;

    }
    public void editPatient(){
        if(isFormeEmpty()){
            Notification.errorNotification("Error","Selectionner un patient/Remplisser toutes les zones de texte");
        }
        else {

        Patient patient=new Patient();

        patient.setId(TableViewPatient.getSelectionModel().getSelectedItem().getId());
        patient.setSexe(ComboBoxSexe.getValue());
        patient.setMutuelle(TextFieldMutuelle.getText());
        patient.setSituationFamiliale(ComboBoxSTF.getValue());
        patient.setDateNaissance(new Date(DatePickerNaissance.getEditor().getText()));
        patient.setAdresse(TextFieldAdresse.getText());
        patient.setTelephone(TextFieldTelephone.getText());
        patient.setCin(TextFieldCin.getText());
        patient.setNom(TextFieldNom.getText());
        patient.setPrenom(TextFieldPrenom.getText());

        patientService.updatePatient(patient);
        load();
        viderForme();
            Notification.errorNotification("Succes","Patient(e) est modifé(e)");
        }
    }


    public boolean isFormeEmpty(){
        boolean check=false;
        if( TextFieldNom.getLength()==0)
            check=true;
        if( TextFieldPrenom.getLength()==0)
            check=true;
        if( TextFieldTelephone.getLength()==0)
            check=true;
        if( TextFieldAdresse.getLength()==0)
            check=true;
        if( TextFieldCin.getLength()==0)
            check=true;
        if( TextFieldMutuelle.getLength()==0)
            check=true;
        if( DatePickerNaissance.getEditor().getLength()==0)
            check=true;

        return check;
    }



}
