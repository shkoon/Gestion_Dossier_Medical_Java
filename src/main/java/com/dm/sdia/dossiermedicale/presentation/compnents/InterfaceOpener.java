package com.dm.sdia.dossiermedicale.presentation.compnents;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class InterfaceOpener {

    public static void openAnotherInterface(String path,String imagePath, String title, Button button) throws Exception {
        Stage anotherStage = new Stage();
        Stage parentStage= (Stage) button.getScene().getWindow();
        if( title=="Main"){
            parentStage.close();
        }
        else {
            anotherStage.initOwner(parentStage);
            anotherStage.initModality(Modality.WINDOW_MODAL);
        }
        anotherStage.setResizable(false);
        Parent anotherRoot = FXMLLoader.load(InterfaceOpener.class.getResource(path));
        anotherStage.setTitle(title);
        Scene scene=new Scene(anotherRoot);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        Image icon = new Image(InterfaceOpener.class.getResourceAsStream(imagePath));
        anotherStage.getIcons().add(icon);
        anotherStage.setScene(scene);
        anotherStage.showAndWait();


    }
}
