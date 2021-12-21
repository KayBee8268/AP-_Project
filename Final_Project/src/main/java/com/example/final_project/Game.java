package com.example.final_project;


import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class Game implements Initializable {
    @FXML
    private ImageView hero;

    @FXML
    private ImageView playButton;

    private ArrayList<ImageView> orcRecord = new ArrayList<>();
    private ArrayList<ImageView> platForm = new ArrayList<>();
    private ArrayList<ImageView> chest = new ArrayList<>();
    private ArrayList<ImageView> coin = new ArrayList<>();
    private ImageView player;

    public void gameSetUp() {
        Scene s= playButton.getScene();

        PlayerClass Player = new PlayerClass(100, 475, 50, 70);
        player= Player.getImageview();;

        PlatformClass platform = new PlatformClass(50, 545, 150, 50);
        platForm.add(platform.getPlatform());

        GreenOrc greenorc = new GreenOrc(370, 500, 50, 50);
        orcRecord.add(greenorc.getImageView());

        RedOrc redorc = new RedOrc(500, 500, 50, 50);
        orcRecord.add(redorc.getImageView());

        platform = new PlatformClass(270, 545, 300, 50);
        platForm.add(platform.getPlatform());

        platform = new PlatformClass(700, 520, 50, 70);
        platForm.add(platform.getPlatform());

        platform = new PlatformClass(900, 490, 200, 150);
        platForm.add(platform.getPlatform());

        CoinChestClass coinChest = new CoinChestClass(1000, 445, 75, 50);
        chest.add(coinChest.getImageView());

        platform = new PlatformClass(1300, 500, 250, 100);
        platForm.add(platform.getPlatform());

        greenorc = new GreenOrc(1500, 455, 50, 50);
        orcRecord.add(greenorc.getImageView());

        platform = new PlatformClass(1700, 485, 200, 150);
        platForm.add(platform.getPlatform());

        CoinClass Coin = new CoinClass(1570, 400, 25, 25);
        coin.add(Coin.getImageView());

        Coin = new CoinClass(1650, 400, 25, 25);
        coin.add(Coin.getImageView());

        Coin = new CoinClass(1750, 400, 25, 25);
        coin.add(Coin.getImageView());

        redorc = new RedOrc(1800, 390, 100, 100);
        orcRecord.add(redorc.getImageView());


//        greenorc= new GreenOrc(150,50,50,50);
//        orcRecord.add(greenorc.getImageView());

        ((AnchorPane) s.getRoot()).getChildren().addAll(orcRecord);
        ((AnchorPane) s.getRoot()).getChildren().addAll(platForm);
        ((AnchorPane) s.getRoot()).getChildren().addAll(chest);
        ((AnchorPane) s.getRoot()).getChildren().addAll(coin);
        ((AnchorPane) s.getRoot()).getChildren().add(player);
    }

    public void move(char key){
            int x,t;
            x=-200;
            t=200;
            for(ImageView temp: orcRecord){
                move(temp,x,0,t).play();
//                moveVertical(temp,0,-300,1000).play();
            }
            for(ImageView temp: platForm){
                move(temp,x,0,t).play();
            }
            for(ImageView temp: chest){
                move(temp,x,0,t).play();
            }
            for(ImageView temp: coin){
                move(temp,x,0,t).play();
            }
    }

    public boolean checkPlatform(TranslateTransition t){
        for(ImageView temp: platForm){
            if((player.getY()==temp.getY()-70) && ((player.getX()>(temp.getX()-50)) && (player.getX()<(temp.getX()+temp.getFitWidth()+50)))){
                System.out.println("check true\n"+" " +temp.getY());
                return true;
            }
//            System.out.println("check false\n");
        }
        return false;
    }

    public void orcJump(){
        for(ImageView temp: orcRecord){
            int val= (int) ((Math.random()*100)/2);
            moveVerticalOrc(temp,0,-300+val,1000+val).play();
        }
    }

    public TranslateTransition jump(){

        TranslateTransition t = move(player,0,-200,400);
        t.play();
        return t;
    }

    public void drop(TranslateTransition t ){
        while(true){
            if(!checkPlatform(t)){
//                System.out.println("inside if \n");
                move(player,0,200,200).play();
            }
            else{
//                System.out.println("inside else\n");
                break;}
        }

    }

    public static TranslateTransition moveVerticalOrc(Node node , double x, double y, double duration){
        TranslateTransition t = new TranslateTransition();
        t.setByX(x);
        t.setByY(y);
        t.setNode(node);
        t.setDuration(Duration.millis(duration));
        t.setCycleCount(TranslateTransition.INDEFINITE);
        t.setAutoReverse(true);
        return t;
    }


    public static TranslateTransition move(Node node , double x, double y, double duration){
        TranslateTransition t = new TranslateTransition();

        t.setByX(x);
        t.setByY(y);
        t.setNode(node);
        t.setDuration(Duration.millis(duration));
        return t;
    }


    public void runTrans(double out){
        moveVerticalOrc(hero,0,-250,1000).play();

    }


    public static Timeline setDelay(double time){
        return new Timeline(new KeyFrame(Duration.millis(time), e->{}));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{runTrans(1);}));
        intro.play();
//        new SequentialTransition(setDelay(10000),intro);


    }
    @FXML
    public void pressPauseButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
        Parent root1 = fxmlLoader.load();
//        controller c = fxmlLoader.getController();
        Scene scene2 = new Scene(root1,1280,720);
        Stage stage1 = (Stage) ((Node )event.getSource()).getScene().getWindow();
        stage1.setScene(scene2);
        stage1.show();

    }
}
