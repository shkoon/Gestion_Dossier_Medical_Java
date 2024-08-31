package com.dm.sdia.dossiermedicale.presentation.controllers;
import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.dao.entities.Antecedent;
import com.dm.sdia.dossiermedicale.dao.entities.Patient;
import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;
import com.dm.sdia.dossiermedicale.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ConsulterController implements Initializable {

        @FXML
        private Label LabelAdresse;

        @FXML
        private Label LabelCin;

        @FXML
        private Label LabelDateNaissance;

        @FXML
        private Label LabelMutuelle;

        @FXML
        private Label LabelNom;

        @FXML
        private Label LabelPrenom;

        @FXML
        private Label LabelSexe;

        @FXML
        private Label LabelStf;

        @FXML
        private Label LabelTelephone;

        @FXML
        private TableColumn<Allergie, String> TableColumnCommentAl;

        @FXML
        private TableColumn<Antecedent, String> TableColumnCommentAn;

        @FXML
        private TableColumn<Vaccin, String> TableColumnCommentV;

        @FXML
        private TableColumn<Vaccin, Date> TableColumnDate;

        @FXML
        private TableColumn<Vaccin, String> TableColumnLibelle;

        @FXML
        private TableColumn<Allergie, String > TableColumnNom;

        @FXML
        private TableColumn<Antecedent, String> TableColumnType;

        @FXML
        private TableView<Allergie> TableViewAllergie;

        @FXML
        private TableView<Antecedent> TableViewAntecedent;

        @FXML
        private TableView<Vaccin> TableViewVaccin;

        private ObservableList<Vaccin> vaccins= FXCollections.observableArrayList();

        private IVaccinService vaccinService=new IVaccinServiceImpl();
        private ObservableList<Antecedent> antecedents= FXCollections.observableArrayList();
        private IAntecedentService antecedentService=new IAntecedentServiceImpl();
        private ObservableList<Allergie> allergies= FXCollections.observableArrayList();
        private IAllergieService allergieService=new  IAllergieServiceImpl();
        private Patient patient;
        private IPatientService patientService=new IPatientServiceImpl();
        private int idPatient;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nomAllergie"));
        TableColumnCommentAl.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        TableColumnLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        TableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateVaccin"));
        TableColumnCommentV.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        TableColumnType.setCellValueFactory(new PropertyValueFactory<>("typeAntecedent"));
        TableColumnCommentAn.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        idPatient=MainController.getIdPatient();
        patient=patientService.getPatientbyId(idPatient);
        LabelNom.setText(patient.getNom());
        LabelPrenom.setText(patient.getPrenom());
        LabelCin.setText(patient.getCin());
        LabelTelephone.setText(patient.getTelephone());
        LabelAdresse.setText(patient.getAdresse());
        LabelDateNaissance.setText(patient.getDateNaissance().toString());
        LabelStf.setText(patient.getSituationFamiliale());

        LabelSexe.setText(patient.getSexe());
        LabelMutuelle.setText(patient.getMutuelle());
        allergies.addAll(allergieService.searchAllergiesByPatient(idPatient));
        TableViewAllergie.setItems(allergies);
        antecedents.addAll(antecedentService.searchAntecedentsByPatient(idPatient));
        TableViewAntecedent.setItems(antecedents);
        vaccins.addAll(vaccinService.searchVaccinsByPatient(idPatient));
        TableViewVaccin.setItems(vaccins);

    }
}
