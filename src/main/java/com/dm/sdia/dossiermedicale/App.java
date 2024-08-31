package com.dm.sdia.dossiermedicale;

import com.dm.sdia.dossiermedicale.presentation.compnents.Notification;
import com.dm.sdia.dossiermedicale.presentation.controllers.PatientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.kordamp.bootstrapfx.BootstrapFX;
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root= FXMLLoader.load(getClass().getResource("views/login.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Login");
        Image icon = new Image(getClass().getResourceAsStream("views/dossier.png"));
        stage.getIcons().add(icon);
        stage.show();

        stage.setResizable(false);
    }

    private void openAnotherInterface(String path,String title) throws Exception {
        Stage anotherStage = new Stage();
        anotherStage.setResizable(false);
        Parent anotherRoot = FXMLLoader.load(getClass().getResource(path));
        anotherStage.setTitle(title);
        Scene scene=new Scene(anotherRoot);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        anotherStage.setScene(scene);
        anotherStage.show();
    }


}
