package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Allergie;
import com.dm.sdia.dossiermedicale.dao.entities.Antecedent;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.IAllergieService;
import com.dm.sdia.dossiermedicale.service.IAllergieServiceImpl;
import com.dm.sdia.dossiermedicale.service.IAntecedentService;
import com.dm.sdia.dossiermedicale.service.IAntecedentServiceImpl;
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

public class AntecedentController implements Initializable {
    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonEdit;

    @FXML
    private TableColumn<Antecedent, String> TableColumnCommentaire;

    @FXML
    private TableColumn<Antecedent, String> TableColumnType;

    @FXML
    private TableView<Antecedent> TableViewAntecedent;

    @FXML
    private TextField TextFieldCommentaire;

    @FXML
    private TextField TextFieldTypeAntecedent;

    @FXML
    private TextField TextFieldSearch;

    private ObservableList<Antecedent> antecedents= FXCollections.observableArrayList();
    private IAntecedentService antecedentService=new IAntecedentServiceImpl();
    private int idPatient;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumnType.setCellValueFactory(new PropertyValueFactory<>("typeAntecedent"));
        TableColumnCommentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        idPatient=PatientController.getId();
        load();
        TableViewAntecedent.setItems(antecedents);

        TextFieldSearch.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            antecedents.clear();
            antecedents.addAll(antecedentService.searchAntecedentsByQuery(newValue,idPatient));
        });
    }

    public void load(){
        antecedents.clear();
        antecedents.addAll(antecedentService.searchAntecedentsByPatient(idPatient));
    }

    public void addAntecedent(){
        if(isFormeEmpty()){
            Notification.errorNotification("Error","Remplisser toutes les zones de texte");
        }else {
            Antecedent antecedent = new Antecedent();

            antecedent.setTypeAntecedent(TextFieldTypeAntecedent.getText());
            antecedent.setCommentaire(TextFieldCommentaire.getText());

            antecedent.setIdPatient(idPatient);

            antecedentService.addAntecedent(antecedent);
            load();
            viderForme();
            Notification.errorNotification("Succes","L'antecedent est ajouté");
        }
    }

    public void viderForme(){
        TextFieldCommentaire.setText("");
        TextFieldTypeAntecedent.setText("");
    }

    public void loadForme(){


        if(TableViewAntecedent.getSelectionModel().getSelectedItem()!= null){
            Antecedent antecedent=TableViewAntecedent.getSelectionModel().getSelectedItem();

            TextFieldTypeAntecedent.setText(antecedent.getTypeAntecedent());
            TextFieldCommentaire.setText(antecedent.getCommentaire());
        }
    }

    public void deleteAntecedent(){

        if(TableViewAntecedent.getSelectionModel().getSelectedItem()==null){
            Notification.errorNotification("Error","Selectionner un antecedent");
        }else {
        int id =TableViewAntecedent.getSelectionModel().getSelectedItem().getId();

        antecedentService.deleteAntecedent(id);
        load();
        viderForme();
            Notification.errorNotification("Succes","L'antecedent est supprimé");
        }

    }

    public void editAntecedent(){
        if(isFormeEmpty()){
            Notification.errorNotification("Error","Selectionner un antecedent/Remplisser toutes les zones de texte");
        }
        else {
        Antecedent antecedent=new Antecedent();
        antecedent.setId(TableViewAntecedent.getSelectionModel().getSelectedItem().getId());
        antecedent.setTypeAntecedent(TextFieldTypeAntecedent.getText());
        antecedent.setCommentaire(TextFieldCommentaire.getText());
        antecedent.setIdPatient(idPatient);

        antecedentService.updateAntecedent(antecedent);
        load();
        viderForme();
            Notification.errorNotification("Succes","L'allergie' est modifé");
        }
    }
    public boolean isFormeEmpty(){


        boolean check=false;

        if( TextFieldTypeAntecedent.getLength()==0)
            check=true;
        if( TextFieldCommentaire.getLength()==0)
            check=true;

        return check;
    }
}
