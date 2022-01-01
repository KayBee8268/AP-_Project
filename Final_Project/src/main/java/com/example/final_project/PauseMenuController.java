package com.example.final_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

public class PauseMenuController {
    private Stage gameStage;
    private Scene gameScene;
    @FXML
    private ImageView closeButton;
    private Game game;
    private DataClass dataObject = new DataClass();
    private controller menuController;

    //   private static  Game game;
//    public PauseMenuController(Game givenGameObject){
//        this.game=givenGameObject;
//    }

    @FXML
    public void pressResumButton() throws IOException,ClassNotFoundException {
        //dataObject.loadGame();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        this.menuController=fxmlLoader.getController();
        this.gameScene=menuController.getGameScene();
        this.gameStage=menuController.getGameStage();
        gameStage.setScene(this.gameScene);
        gameStage.show();

        System.out.println("Game Resumed Successfully");
    }

    public void pressSaveButton() throws IOException{

        System.out.println("Game Saved Successfully");

    }
    public void pressLoadButton() throws IOException{

    }
    public void pressExitButton(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
}
