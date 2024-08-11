package com.app.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FinalPane {
    private Models.MainModels.Result selected_game;

    public void setData(Models.MainModels.Result res){
        this.selected_game = res;
    }
    JFrame frame = new JFrame("Test");
    JFileChooser fchooser = new JFileChooser();

    @FXML
    public void onClick(ActionEvent actionEvent) {
        fchooser.setFileFilter(new FileNameExtensionFilter("executable","*.exe"));
        fchooser.showDialog(frame, "Open");
    }
}
