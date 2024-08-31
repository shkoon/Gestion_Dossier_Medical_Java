package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Traitement;
import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.ITraitementService;
import com.dm.sdia.dossiermedicale.service.ITraitementServiceImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class TraitementController implements Initializable {


    @FXML
    private Button ButtonAjouter;

    @FXML
    private Button ButtonModifier;

    @FXML
    private Button ButtonSupprimer;

    @FXML
    private DatePicker DatePickerTrait;

    @FXML
    private TableColumn<Traitement, String> TableColumnComment;

    @FXML
    private TableColumn<Traitement, Date> TableColumnDate;

    @FXML
    private TableColumn<Traitement, Integer> TableColumnDosage;

    @FXML
    private TableColumn<Traitement, String> TableColumnLibelle;

    @FXML
    private TableColumn<Traitement, String> TableColumnPeriode;

    @FXML
    private TableView<Traitement> TableViewTraitement;

    @FXML
    private TextField TextFieldComment;

    @FXML
    private TextField TextFieldDosage;

    @FXML
    private TextField TextFieldLibelle;

    @FXML
    private TextField TextFieldPeriode;

    @FXML
    private TextField TextFieldSearch;

    private ObservableList<Traitement> traitements= FXCollections.observableArrayList();
    private ITraitementService traitementService=new ITraitementServiceImpl();
    private int idCons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumnLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        TableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateTraitement"));
        TableColumnComment.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        TableColumnDosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        TableColumnPeriode.setCellValueFactory(new PropertyValueFactory<>("periode"));
        idCons=ConsultationController.getIdConsultation();
        load();
        TableViewTraitement.setItems(traitements);
        TextFieldSearch.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            traitements.clear();
            traitements.addAll(traitementService.searchTraitementsByQuery(newValue,idCons));
        });
    }


    public void addTraitement(){

        if(isFormeEmpty()){
            Notification.errorNotification("Error","Les zones de textes/date sont vides");
        }
        else {
            if(TextFieldDosage.getText().matches("[0-9]+")){
                Traitement traitement=new Traitement();
                traitement.setDateTraitement(new Date(DatePickerTrait.getEditor().getText()));
                traitement.setDosage(Integer.parseInt(TextFieldDosage.getText()));
                traitement.setCommentaire(TextFieldComment.getText());
                traitement.setLibelle(TextFieldLibelle.getText());
                traitement.setPeriode(TextFieldPeriode.getText());
                traitement.setIdConsultation(idCons);
                traitementService.addTraitement(traitement);
                load();
                Notification.errorNotification("Succes","Le Traitement est ajouté");
            }
            else {
                Notification.errorNotification("Error","Insérer un nombre dans la zone Dosage");
            }


        }
        }
    public void editTraitement(){
        if( isFormeEmpty()){
            Notification.errorNotification("Error","Selectionner un vaccin/Remplisser toutes les zones de texte");

        }else {
            if(TextFieldDosage.getText().matches("[0-9]+")) {
                Traitement traitement = new Traitement();
                traitement.setId(TableViewTraitement.getSelectionModel().getSelectedItem().getId());
                traitement.setLibelle(TextFieldLibelle.getText());
                traitement.setDosage(Integer.parseInt(TextFieldDosage.getText()));
                traitement.setDateTraitement(new Date(DatePickerTrait.getEditor().getText()));
                traitement.setPeriode(TextFieldPeriode.getText());
                traitement.setCommentaire(TextFieldComment.getText());
                traitement.setIdConsultation(idCons);
                traitementService.updateTraitement(traitement);
                load();
                viderForme();

                Notification.errorNotification("Succes", "Le Vaccin est modifé");
            }
            else {
                Notification.errorNotification("Error","La zone Dosage doit étre un nombre");
            }
        }

    }
        public void deleteTraitement(){
            if(TableViewTraitement.getSelectionModel().getSelectedItem()==null){
                Notification.errorNotification("Error","Selectionner un traitement");
            }
            else {

                traitementService.deleteTraitement(TableViewTraitement.getSelectionModel().getSelectedItem().getId());
                load();
                viderForme();
                Notification.errorNotification("Succes","Le Traitement est supprimé");
            }
        }
        public void load(){
            traitements.clear();
            traitements.addAll(traitementService.searchTraitementsByConsultation(idCons));
        }
        public void viderForme(){

            TextFieldDosage.setText("");
            TextFieldComment.setText("");
            TextFieldPeriode.setText("");
            TextFieldLibelle.setText("");
            DatePickerTrait.getEditor().setText("");
        }

        public void loadForme(){
            if(TableViewTraitement.getSelectionModel().getSelectedItem()!=null){
                Traitement traitement=TableViewTraitement.getSelectionModel().getSelectedItem();
                TextFieldLibelle.setText(traitement.getLibelle());
                TextFieldDosage.setText(String.valueOf(traitement.getDosage()));
                TextFieldPeriode.setText(traitement.getPeriode());
                TextFieldComment.setText(traitement.getCommentaire());
                DatePickerTrait.setValue(LocalDate.parse(traitement.getDateTraitement().toString()));

            }

        }
        public boolean isFormeEmpty(){


            boolean check=false;

            if( TextFieldLibelle.getLength()==0)
                check=true;
            if( TextFieldComment.getLength()==0)
                check=true;
            if( DatePickerTrait.getEditor().getLength()==0)
                check=true;
            if(TextFieldPeriode.getLength()==0)
                check=true;
            if(TextFieldDosage.getLength()==0)
                check=true;
            return check;
        }
}
