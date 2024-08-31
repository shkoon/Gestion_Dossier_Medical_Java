package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.IVaccinService;
import com.dm.sdia.dossiermedicale.service.IVaccinServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class VaccinController implements Initializable {



    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonEdit;

    @FXML
    private DatePicker DatePickerVaccin;

    @FXML
    private TableColumn<Vaccin, String> TableColumnCommentaire;

    @FXML
    private TableColumn<Vaccin, Date> TableColumnDate;

    @FXML
    private TableColumn<Vaccin, String> TableColumnLibelle;

    @FXML
    private TableView<Vaccin> TableViewVaccin;

    @FXML
    private TextField TextFieldCommentaire;

    @FXML
    private TextField TextFieldLibelle;

    @FXML
    private TextField TextFieldSearch;


    private ObservableList<Vaccin> vaccins= FXCollections.observableArrayList();

    private IVaccinService vaccinService=new IVaccinServiceImpl();

    private int idPatient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumnLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        TableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateVaccin"));
        TableColumnCommentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        idPatient=PatientController.getId();
        load();
        TableViewVaccin.setItems(vaccins);


        TextFieldSearch.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            vaccins.clear();
            vaccins.addAll(vaccinService.searchVaccinsByQuery(newValue,idPatient));
        });
    }

    public void viderForme(){
        TextFieldCommentaire.setText("");
        TextFieldLibelle.setText("");
        DatePickerVaccin.setValue(null);

    }
    public void addVaccin(){
        if(isFormeEmpty()){
            Notification.errorNotification("Error","Les zones de textes/date sont vides");
        }
        else {
            Vaccin vaccin = new Vaccin();

            vaccin.setLibelle(TextFieldLibelle.getText());
            vaccin.setDateVaccin(new Date(DatePickerVaccin.getEditor().getText()));
            vaccin.setCommentaire(TextFieldCommentaire.getText());
            vaccin.setIdPatient(idPatient);
            vaccinService.addVaccin(vaccin);
            load();
            viderForme();
            Notification.errorNotification("Succes","Le Vaccin est ajouté");
        }
    }

    public void deleteVaccin(){


        if(TableViewVaccin.getSelectionModel().getSelectedItem()==null){
            Notification.errorNotification("Error","Selectionner un vaccin");
        }
        else {
            int id=TableViewVaccin.getSelectionModel().getSelectedItem().getId();
            vaccinService.deleteVaccin(id);
            load();
            viderForme();
            Notification.errorNotification("Succes","Le Vaccin est supprimé");
        }
    }

    public void editVaccin(){
     if( isFormeEmpty()){
         Notification.errorNotification("Error","Selectionner un vaccin/Remplisser toutes les zones de texte");

     }else {
         Vaccin vaccin=new Vaccin();
         vaccin.setId(TableViewVaccin.getSelectionModel().getSelectedItem().getId());
         vaccin.setLibelle(TextFieldLibelle.getText());
         vaccin.setDateVaccin(new Date(DatePickerVaccin.getEditor().getText()));
         vaccin.setCommentaire(TextFieldCommentaire.getText());
         vaccin.setIdPatient(idPatient);
         vaccinService.updateVaccin(vaccin);
         load();
         viderForme();

         Notification.errorNotification("Succes","Le Vaccin est modifé");
     }

    }
    public void loadForm(){

        if(TableViewVaccin.getSelectionModel().getSelectedItem()!=null){
            Vaccin vaccin=TableViewVaccin.getSelectionModel().getSelectedItem();

            TextFieldLibelle.setText(vaccin.getLibelle());
            TextFieldCommentaire.setText(vaccin.getCommentaire());
            DatePickerVaccin.setValue(LocalDate.parse(vaccin.getDateVaccin().toString()));
        }


    }

    public void load(){
        vaccins.clear();
        vaccins.addAll(vaccinService.searchVaccinsByPatient(idPatient));

    }

    public boolean isFormeEmpty(){


        boolean check=false;

        if( TextFieldLibelle.getLength()==0)
            check=true;
        if( TextFieldCommentaire.getLength()==0)
            check=true;
        if( DatePickerVaccin.getEditor().getLength()==0)
            check=true;

        return check;
    }
}
