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


public class Game{
    @FXML
    private ImageView hero;

    @FXML
    private ImageView playButton;
    private Scene s;

    private ArrayList<OrcClass> orcRecord = new ArrayList<>();
    private ArrayList<ImageView> platformRecord = new ArrayList<>();
    private ArrayList<ImageView> chest = new ArrayList<>();
    private ArrayList<ChestClass> Chest = new ArrayList<>();
    private ArrayList<ImageView> coin = new ArrayList<>();
    private ArrayList<ImageView> fallingPlatformRecord1 = new ArrayList<>();
    private ArrayList<ImageView> fallingPlatformRecord2 = new ArrayList<>();
    private ImageView player;
    private PlayerClass Player;

    public void gameSetUp() {
        s= playButton.getScene();

        Player = new PlayerClass(100, 475, 50, 70);
        player= Player.getImageview();

        //1
        PlatformClass platform = new PlatformClass(50, 545, 150, 50,1);
        platformRecord.add(platform.getPlatform());
        //2
        platform = new PlatformClass(270, 545, 300, 50,2);
        platformRecord.add(platform.getPlatform());
        //3
        platform = new PlatformClass(700, 520, 100, 70,3);
        platformRecord.add(platform.getPlatform());
        //4
        platform = new PlatformClass(900, 490, 200, 80,5);
        platformRecord.add(platform.getPlatform());
        //5
        platform = new PlatformClass(1300, 500, 250, 75,2);
        platformRecord.add(platform.getPlatform());
        //6
        platform = new PlatformClass(1700, 390, 200, 70,3);
        platformRecord.add(platform.getPlatform());
        //7
        platform = new PlatformClass(2000, 430, 300, 60,1);
        platformRecord.add(platform.getPlatform());
        //8
        platform = new PlatformClass(2500, 450, 150, 80,4);
        platformRecord.add(platform.getPlatform());
//9
        platform = new PlatformClass(2800, 435, 130, 75,1);
        platformRecord.add(platform.getPlatform());
//10
        platform = new PlatformClass(3150, 400, 300, 75,2);
        platformRecord.add(platform.getPlatform());
//11
        platform = new PlatformClass(3550, 420, 150, 70,6);
        platformRecord.add(platform.getPlatform());

//12
        platform = new PlatformClass(3800, 380, 250, 80,2);
        platformRecord.add(platform.getPlatform());

//13
        ObstacleClass fallingPlatform = new ObstacleClass(4075,380,50,20,1);
        fallingPlatformRecord1.add(fallingPlatform.getFallingPlatform());
        for(int i =4125;i<4525;i+=50){
            fallingPlatform = new ObstacleClass(i,380,50,20,1);
            fallingPlatformRecord1.add(fallingPlatform.getFallingPlatform());
        }


// 14
        platform = new PlatformClass(4550, 380, 250, 75,3);
        platformRecord.add(platform.getPlatform());
//15
        platform = new PlatformClass(5000, 400, 350, 80 ,2);
        platformRecord.add(platform.getPlatform());

//16
        platform = new PlatformClass(5500, 385, 150, 75,7);
        platformRecord.add(platform.getPlatform());

//17
        platform = new PlatformClass(5850, 375, 250, 80,5);
        platformRecord.add(platform.getPlatform());
//18
        platform = new PlatformClass(6300, 390, 275, 70,4);
        platformRecord.add(platform.getPlatform());

//19
        platform = new PlatformClass(6700, 400, 150, 75,3);
        platformRecord.add(platform.getPlatform());
//20
        platform = new PlatformClass(6900, 420, 250, 70,6);
        platformRecord.add(platform.getPlatform());
//21
        platform = new PlatformClass(7300, 400, 200, 70,3);
        platformRecord.add(platform.getPlatform());
//22
        ObstacleClass fallingPlatform2 = new ObstacleClass(7545,400,50,20,2);
        fallingPlatformRecord2.add(fallingPlatform2.getFallingPlatform());
        for(int i =7595;i<7955;i+=50){
            fallingPlatform2 = new ObstacleClass(i,400,50,20,2);
            fallingPlatformRecord2.add(fallingPlatform2.getFallingPlatform());
        }


        platform = new PlatformClass(8000, 400, 250, 65,4);
        platformRecord.add(platform.getPlatform());
//24
        platform = new PlatformClass(8500  , 350, 275, 80,6);
        platformRecord.add(platform.getPlatform());

//25
        platform = new PlatformClass(8900, 410, 250, 85,5);
        platformRecord.add(platform.getPlatform());

//26
        platform = new PlatformClass(9300, 350, 275, 70,1);
        platformRecord.add(platform.getPlatform());

//27
        platform = new PlatformClass(9700, 400, 150, 80,7);
        platformRecord.add(platform.getPlatform());

//28
        platform = new PlatformClass(10000, 430, 1000, 75,2);
        platformRecord.add(platform.getPlatform());
        //  platform = new PlatformClass(7725, 430, 350, 80,2);
        //platformRecord.add(platform.getPlatform());
//23 Difficulty rises from here
        //platform = new PlatformClass(4200, 400, 300, 80,5);
        // platForm.add(platform.getPlatform());

//        PlatformClass platform = new PlatformClass(50, 545, 150, 50);
//        platForm.add(platform.getPlatform());

        GreenOrc greenorc = new GreenOrc(370, 500, 50, 50);
        orcRecord.add(greenorc);

        RedOrc redorc = new RedOrc(500, 500, 50, 50);
        orcRecord.add(redorc);

//        platform = new PlatformClass(270, 545, 300, 50);
//        platForm.add(platform.getPlatform());

//        platform = new PlatformClass(700, 520, 50, 70);
//        platForm.add(platform.getPlatform());

//        platform = new PlatformClass(900, 490, 200, 150);
//        platForm.add(platform.getPlatform());

        CoinChestClass coinChest = new CoinChestClass(1000, 445, 75, 50,5);
        chest.add(coinChest.getImageView());
        Chest.add(coinChest);

//        platform = new PlatformClass(1300, 500, 250, 100);
//        platForm.add(platform.getPlatform());

        greenorc = new GreenOrc(1500, 455, 50, 50);
        orcRecord.add(greenorc);

//        platform = new PlatformClass(1700, 485, 200, 150);
//        platForm.add(platform.getPlatform());

        CoinClass Coin = new CoinClass(1570, 400, 25, 25);
        coin.add(Coin.getImageView());

        Coin = new CoinClass(1650, 400, 25, 25);
        coin.add(Coin.getImageView());

        Coin = new CoinClass(1750, 400, 25, 25);
        coin.add(Coin.getImageView());

        redorc = new RedOrc(1800, 390, 100, 100);
        orcRecord.add(redorc);


//        greenorc= new GreenOrc(150,50,50,50);
//        orcRecord.add(greenorc.getImageView());

        for(OrcClass temp:orcRecord){
        ((AnchorPane) s.getRoot()).getChildren().add(temp.getImageView());
        }
        ((AnchorPane) s.getRoot()).getChildren().addAll(platformRecord);
        ((AnchorPane) s.getRoot()).getChildren().addAll(chest);
        ((AnchorPane) s.getRoot()).getChildren().addAll(coin);
        ((AnchorPane) s.getRoot()).getChildren().add(player);
        ((AnchorPane) s.getRoot()).getChildren().addAll(fallingPlatformRecord1);
        ((AnchorPane) s.getRoot()).getChildren().addAll(fallingPlatformRecord2);

    }

    public Dash move(Dash moveCheck){
            if(moveCheck.getDash()>0){
                Player.setVal(0);
            for(OrcClass temp: orcRecord){
                temp.getImageView().setX(temp.getImageView().getX() -5);
                if((temp.getImageView().getX()==player.getX()+50) && (temp.getImageView().getY()<player.getY()+50) && (temp.getImageView().getY()>player.getY()-70)){
                    moveCheck.setOrc(temp.getImageView());
                    moveCheck.setPushorc(moveCheck.getDash());
                    moveCheck.setDash(0);
                }
//                if((temp.getImageView().getY()<player.getY()+player.getFitHeight()+3) &&(temp.getImageView().getY()>player.getY()+player.getFitHeight()-3) && (player.getX()+player.getFitWidth()>temp.getImageView().getX()) && (temp.getImageView().getX()+temp.getImageView().getFitWidth()>player.getX())){
//                    temp.setisDead(true);
//                }
            }


            for(ImageView temp: fallingPlatformRecord1){
                temp.setX(temp.getX() -5);
            }
            for(ImageView temp: fallingPlatformRecord2) {
                temp.setX(temp.getX() - 5);
            }
            for(ImageView temp: platformRecord){
                temp.setX(temp.getX() -5);
            }
            for(ImageView temp: chest){
                temp.setX(temp.getX() -5);
            }
            for(ImageView temp: coin){
                temp.setX(temp.getX() -5);
            }
            moveCheck.setDash(moveCheck.getDash()-5);
            if(moveCheck.getDash()<=0){Player.setVal(-1);}
            }

            if(moveCheck.getPushorc()>0){
                moveCheck.getOrc().setX(moveCheck.getOrc().getX()+5);
                moveCheck.setPushorc(moveCheck.getPushorc()-5);
                if(moveCheck.getPushorc()<=0){
                    moveCheck.setOrc(null);
                }
            }
            return moveCheck;
    }

//    public boolean checkPlatform(){
//        for(ImageView temp: platForm){
//            if((player.getY()==temp.getY()-71) && ((player.getX()>(temp.getX()-50)) && (player.getX()<(temp.getX()+temp.getFitWidth()+50)))){
//                System.out.println("check true\n"+" " +temp.getLayoutBounds());
//                return true;
//            }
//            //System.out.println("check false\n"+" " +player.getY() );
//        }
//        return false;
//    }

    public void checkDead(){
        for(OrcClass temp: orcRecord){
            if((temp.getImageView().getY()<player.getY()+player.getFitHeight()+3) &&(temp.getImageView().getY()>player.getY()+player.getFitHeight()-3) && (player.getX()+player.getFitWidth()>temp.getImageView().getX()) && (temp.getImageView().getX()+temp.getImageView().getFitWidth()>player.getX())){
                temp.setisDead(true);
            }
            if((temp.getImageView().getY()+temp.getImageView().getFitHeight()+3>player.getY()) &&(temp.getImageView().getY()+temp.getImageView().getFitHeight()-3<player.getY()) && (player.getX()+player.getFitWidth()>temp.getImageView().getX()) && (temp.getImageView().getX()+temp.getImageView().getFitWidth()>player.getX())){
                Player.setDead(true);

            }
        }
    }

    public void playerJump(){
        Player.jump();
        Player.drop();
    }

    public void orcJump(){
        for(OrcClass temp:orcRecord){
            temp.jump();
            temp.drop();
        }
    }

    public void checkCoins(){
        for(ImageView temp:coin){
            if(player.getBoundsInParent().intersects(temp.getBoundsInParent())){
                Player.addCoins(1);
                ((AnchorPane) s.getRoot()).getChildren().remove(temp);
            }
        }
    }

    public void checkFallingPlatform(){
        ObstacleClass.fall();
    }

    public void checkChests(){
        for(ChestClass temp:Chest){
            if(player.getBoundsInParent().intersects(temp.getImageView().getBoundsInParent())){
                if(!temp.getOpen()) temp.awardItem(Player);
                temp.setOpen(true);
            }
        }
    }

//    public static Scene getScene(){
//        return playButton.getScene();
//    }


//    public int orcJump(int val){
//        if(val>0){
//            for(ImageView temp: orcRecord){
////              int val= (int) ((Math.random()*100)/2);
////              moveVerticalOrc(temp,0,-300+val,1000+val).play();
//                temp.setY(temp.getY()-1);
//                val++;
//                if(val==500) val=-500;
//            }
//        }
//        else{
//            for (ImageView temp : orcRecord) {
////              int val= (int) ((Math.random()*100)/2);
////              moveVerticalOrc(temp,0,-300+val,1000+val).play();
//                temp.setY(temp.getY() + 1);
//                val++;
//            }
//        }
//        return val;
//    }

//    public int jump(int val){
//        if(val>0)
//        {
//            player.setY(player.getY()-1);
//            val--;
//        }
//        if(val==0) val=-200;
//        return val;
//    }

//    public int drop(int val){
//        if(val<0) {
//            if (!checkPlatform()){
//                player.setY(player.getY() + 1);
//                val++;
//            }
//            if(checkPlatform()) return 200;
//
//        }
//        if(player.getY()==720){
//            System.out.println("Player Dead");
//        }
//        return val;
//
//
//    }

//    public static TranslateTransition moveVerticalOrc(Node node , double x, double y, double duration){
//        TranslateTransition t = new TranslateTransition();
//        t.setByX(x);
//        t.setByY(y);
//        t.setNode(node);
//        t.setDuration(Duration.millis(duration));
//        t.setCycleCount(TranslateTransition.INDEFINITE);
//        t.setAutoReverse(true);
//        return t;
//    }
//
//
//    public static TranslateTransition move(Node node , double x, double y, double duration){
//        TranslateTransition t = new TranslateTransition();
//
//        t.setByX(x);
//        t.setByY(y);
//        t.setNode(node);
//        t.setDuration(Duration.millis(duration));
//        return t;
//    }
//
//
//    public void runTrans(double out){
//        moveVerticalOrc(hero,0,-250,1000).play();
//
//    }
//
//
//    public static Timeline setDelay(double time){
//        return new Timeline(new KeyFrame(Duration.millis(time), e->{}));
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{runTrans(1);}));
//        intro.play();
//        new SequentialTransition(setDelay(10000),intro);


//    }
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
