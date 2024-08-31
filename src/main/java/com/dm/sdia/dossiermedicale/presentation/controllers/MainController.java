package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Medecin;
import com.dm.sdia.dossiermedicale.dao.entities.Patient;
import com.dm.sdia.dossiermedicale.presentation.compnents.InterfaceOpener;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.IMedecinService;
import com.dm.sdia.dossiermedicale.service.IMedecinServiceImpl;
import com.dm.sdia.dossiermedicale.service.IPatientService;
import com.dm.sdia.dossiermedicale.service.IPatientServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.DatagramPacket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button ButtonCons;

    @FXML
    private Button ButtonPatient;

    @FXML
    private TableColumn<Patient, String> TableColumnCin;

    @FXML
    private TableColumn<Patient, Date> TableColumnDateN;

    @FXML
    private Text LabelNom;
    @FXML
    private TableColumn<Patient, String> TableColumnNom;

    @FXML
    private TableColumn<Patient, String> TableColumnPrenom;

    @FXML
    private TableView<Patient> TableVIewPatient;

    @FXML
    private TextField TextFieldSearch;

    @FXML
    private Button ButtonDossier;

    private static int idPatient=-1;

    private ObservableList<Patient> patients=FXCollections.observableArrayList();
    private IPatientService patientService=new  IPatientServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TableColumnCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        TableColumnDateN.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        load();
        TableVIewPatient.setItems(patients);

        TextFieldSearch.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            patients.clear();
            patients.addAll(patientService.searchPatientsByQuery(newValue));
        });

        ButtonPatient.setOnAction(e -> {
            try {
                    InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/patient/patient.fxml","/com/dm/sdia/dossiermedicale/views/patient.png","Patient",ButtonPatient);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ButtonCons.setOnAction(e -> {
            try {
                if(idPatient>-1){
                    InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/dossierpatient/consultation.fxml","/com/dm/sdia/dossiermedicale/views/medecin.png","Consultation",ButtonCons);
                }
                else {
                    Notification.errorNotification("Error","Selectionner un Patient");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

         ButtonDossier.setOnAction(e -> {
            try {
                if(idPatient>-1){
                    InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/consulter.fxml","/com/dm/sdia/dossiermedicale/views/yeux-rouges.png","Consulter",ButtonCons);
                }
                else {
                    Notification.errorNotification("Error","Selectionner un Patient");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        IMedecinService medecinService=new IMedecinServiceImpl();

        Medecin medecin=medecinService.getMedecinByid(LoginController.getId());

        LabelNom.setText("Bienvenue Dr."+medecin.getNom().toUpperCase()+" "+medecin.getPrenom());


    }

    public static int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient() {

        if( TableVIewPatient.getSelectionModel().getSelectedItem()!=null){
            idPatient=TableVIewPatient.getSelectionModel().getSelectedItem().getId();
        }
    }

    public void load(){
        patients.clear();
        patients.addAll(patientService.getAllPatients());

    }
}
