package com.example.final_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PauseMenuController implements Serializable {
    private Stage gameStage;
    private Scene gameScene;

    private Game game;
    private DataClass dataObject = new DataClass();
    private controller menuController;
    @FXML
    private Group feedBackGroup;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView okayButton;
    @FXML
    private Label gameSavedLabel;


    private  AnimationClass animateObject = new AnimationClass();

    //   private static  Game game;
//    public PauseMenuController(Game givenGameObject){
//        this.game=givenGameObject;
//    }
//    public void callFeedBackUpwards(){
//        feedBackGroup.toFront();
//        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1), e ->{moveFeedBackUpwards();}));
//        intro.play();
//    }
//    public void moveFeedBackUpwards(){
//        animateObject.straightTransition(okayButton,0,-750,500).play();
//        animateObject.straightTransition(backgroundImage,0,-750,500).play();
//        animateObject.straightTransition(gameSavedLabel,0,-750,500).play();
//
//    }
//    public void callFeedBackDownwards(){
//
//        feedBackGroup.toFront();
//        feedBackGroup.setOpacity(1);
////        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1), e ->{moveFeedBaackDownwards();}));
////        intro.play();
//    }
//    public void moveFeedBaackDownwards(){
//
//        System.out.println("priyanshu");
//        animateObject.straightTransition(okayButton,0,-750,500).play();
//
//        animateObject.straightTransition(backgroundImage,0,750,500).play();
//        animateObject.straightTransition(gameSavedLabel,0,750,500).play();
//
//    }
//    @FXML
//    public void pressOkay(){
//     callFeedBackUpwards();
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

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        feedBackGroup.setOpacity(0);
//    }
}
