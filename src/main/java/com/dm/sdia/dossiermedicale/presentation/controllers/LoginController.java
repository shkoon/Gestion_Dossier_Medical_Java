package com.dm.sdia.dossiermedicale.presentation.controllers;

import com.dm.sdia.dossiermedicale.dao.entities.Medecin;
import com.dm.sdia.dossiermedicale.presentation.compnents.InterfaceOpener;
import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.service.IMedecinService;
import com.dm.sdia.dossiermedicale.service.IMedecinServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button ButtonConnecter;

    @FXML
    private TextField TextFieldUsername;

    @FXML
    private TextField TextFieldMdp;

    private IMedecinService medecinService=new IMedecinServiceImpl();
    private static int id;

    public void seConnecter() throws Exception {
        if (TextFieldMdp.getLength()>3 && TextFieldUsername.getLength()>3 ) {

            Medecin medecin = medecinService.seConnecter(TextFieldUsername.getText(), TextFieldMdp.getText());

            if (medecin == null) {
                Notification.errorNotification("Error", "Username ou mot de passe erroné");
            } else {

                id = medecin.getId();
                InterfaceOpener.openAnotherInterface("/com/dm/sdia/dossiermedicale/views/main.fxml", "/com/dm/sdia/dossiermedicale/views/dossier.png", "Main", ButtonConnecter);
            }
        }
        else {
            Notification.errorNotification("Error", "Remplisser les zones de texte");
        }

    }

    public static int getId() {
        return id;
    }
}
