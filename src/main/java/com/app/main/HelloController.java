package com.app.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import javafx.scene.Parent;

import java.io.IOException;


public class HelloController {

@FXML
private BorderPane HelloApplication;


@FXML
private void onAddNew() throws IOException {
//    Parent root = loader.load();

    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("new-pane.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 640, 360);
    Stage stage = new Stage();
    stage.setTitle("Save Manager");
    stage.setScene(scene);
    stage.show();

}



}