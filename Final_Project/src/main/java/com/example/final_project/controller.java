package com.example.final_project;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class controller implements Initializable {

    @FXML
    private ImageView island;
    @FXML
    private ImageView redOrc;

    @FXML
    private ImageView playButton;


    public static TranslateTransition moveObjInScreen(Node node , double x, double y, double duration){
        TranslateTransition t = new TranslateTransition();

        t.setByX(x);
        t.setByY(y);
        t.setNode(node);
        t.setDuration(Duration.millis(duration));
        t.setCycleCount(TranslateTransition.INDEFINITE);
        t.setAutoReverse(true);
        return t;
    }
    public void runTrans(double out){
        moveObjInScreen(redOrc,0,-250,1000).play();
//        moveObjInScreen(island,350,0,1000).play();
    }


    public static Timeline setDelay(double time){
        return new Timeline(new KeyFrame(Duration.millis(time),e->{}));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{runTrans(1);}));
        intro.play();
//        new SequentialTransition(setDelay(1000),intro);


    }
    @FXML
    public void pressPlayButton(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root1 = fxmlLoader.load();
        Game game = fxmlLoader.getController();

        Scene scene1 = new Scene(root1);
        //Scene scene1 = new Scene(root1,1280,720);
        Stage stage = (Stage) ((Node )event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
        game.gameSetUp();
        stage.show();

        class myTimer extends AnimationTimer{
//             private static int orcjump=1;
            private static int jump=200;
            private Dash moveCheck=new Dash(0,0);

            @Override
            public void handle(long l) {
                game.orcJump();
//                orcjump=game.orcJump(orcjump);
                game.playerJump();
                scene1.setOnKeyPressed(event1 ->{
                    if (event1.getCode() == KeyCode.SPACE) {
                        moveCheck.setDash(200);
                    }
                });
                moveCheck=game.move(moveCheck);
                game.checkDead();
                game.checkCoins();
                game.checkChests();
                game.checkFallingPlatform();

            }
        }

        AnimationTimer timer=new myTimer();
        timer.start();


    }



    @FXML
    public void showDeathMenu(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeathMenu.fxml"));
        Parent root2 = fxmlLoader.load();
//      controller c = fxmlLoader.getController();
        Scene scene3 = new Scene(root2,1280,720);
        Stage stage3 = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage3.setScene(scene3);
        stage3.show();

    }
}

