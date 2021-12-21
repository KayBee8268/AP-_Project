package com.example.final_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenuController {
    @FXML
    public void pressResumButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root1 = fxmlLoader.load();
//        controller c = fxmlLoader.getController();
        Scene scene1 = new Scene(root1,1280,720);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
        stage.show();
        GameClass g = new GameClass(scene1);
    }
}
