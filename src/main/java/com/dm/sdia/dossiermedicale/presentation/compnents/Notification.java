package com.dm.sdia.dossiermedicale.presentation.compnents;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Notification {
    public static void errorNotification(String titre,String message){
        Notifications.create().hideAfter(new Duration(2000)).
                title(titre)
                .text(message)
                .darkStyle().position(Pos.CENTER)
                .show();
    }

}
