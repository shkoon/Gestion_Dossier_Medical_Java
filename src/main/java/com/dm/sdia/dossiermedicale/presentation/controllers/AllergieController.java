package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.IAllergieService;
import com.dm.sdia.dossiermedicale.service.IAllergieServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AllergieController implements Initializable {

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonEdit;

    @FXML
    private TableColumn<Allergie, String> TableColumnCommentaire;

    @FXML
    private TableColumn<Allergie, String> TableColumnNom;

    @FXML
    private TableView<Allergie> TableViewAllergie;

    @FXML
    private TextField TextFieldCommentaire;

    @FXML
    private TextField TextFieldNomAllergie;

    @FXML
    private TextField TextFieldSearch;

    private ObservableList<Allergie> allergies= FXCollections.observableArrayList();
    private IAllergieService allergieService=new  IAllergieServiceImpl();
    private int idPatient;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nomAllergie"));
        TableColumnCommentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        idPatient=PatientController.getId();
        load();
        TableViewAllergie.setItems(allergies);

        TextFieldSearch.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            allergies.clear();
            allergies.addAll(allergieService.searchAllergiesByQuery(newValue,idPatient));
        });
    }

    public void load(){
        allergies.clear();
        allergies.addAll(allergieService.searchAllergiesByPatient(idPatient));
    }

    public void addAllergie(){
       if(isFormeEmpty()){
           Notification.errorNotification("Error","Remplisser toutes les zones de texte");
       }else {
           Allergie allergie=new Allergie();

           allergie.setNomAllergie(TextFieldNomAllergie.getText());
           allergie.setCommentaire(TextFieldCommentaire.getText());

           allergie.setIdPatient(idPatient);

           allergieService.addAllergie(allergie);
           load();
           viderForme();
           Notification.errorNotification("Succes","L'allergie est ajouté");
       }

    }

    public void viderForme(){
        TextFieldCommentaire.setText("");
        TextFieldNomAllergie.setText("");
    }

    public void loadForme(){
        if(TableViewAllergie.getSelectionModel().getSelectedItem()!= null) {
            Allergie allergie = TableViewAllergie.getSelectionModel().getSelectedItem();

            TextFieldNomAllergie.setText(allergie.getNomAllergie());
            TextFieldCommentaire.setText(allergie.getCommentaire());
        }
    }

    public void deleteAllergie(){

        if(TableViewAllergie.getSelectionModel().getSelectedItem()==null){
            Notification.errorNotification("Error","Selectionner une allergie");
        }else {
            int id =TableViewAllergie.getSelectionModel().getSelectedItem().getId();

            allergieService.deleteAllergie(id);
            load();
            viderForme();
            Notification.errorNotification("Succes","L'allergie est supprimé");
        }

    }

    public void editAllergie(){
      if(isFormeEmpty()){
          Notification.errorNotification("Error","Selectionner une allergie/Remplisser toutes les zones de texte");
      }
      else {
          Allergie allergie=new Allergie();
          allergie.setId(TableViewAllergie.getSelectionModel().getSelectedItem().getId());
          allergie.setNomAllergie(TextFieldNomAllergie.getText());
          allergie.setCommentaire(TextFieldCommentaire.getText());
          allergie.setIdPatient(idPatient);

          allergieService.updateAllergie(allergie);
          load();
          viderForme();
          Notification.errorNotification("Succes","L'allergie' est modifé");
      }
    }

    public boolean isFormeEmpty(){


        boolean check=false;

        if( TextFieldNomAllergie.getLength()==0)
            check=true;
        if( TextFieldCommentaire.getLength()==0)
            check=true;

        return check;
    }
}

