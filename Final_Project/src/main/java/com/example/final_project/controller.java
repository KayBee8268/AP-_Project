package com.example.final_project;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class controller implements Initializable, Serializable {
    private ArrayList<String> savedGameList;
    private ArrayList<String> savedPlayerList;

    private DataClass dataObject = new DataClass();
    private LoadPlayer loadPlayer = new LoadPlayer();

    @FXML
    private ImageView exitButtoninMainMenu;
    @FXML
    private ChoiceBox<String> savedGamesChoices;
    @FXML
    private ChoiceBox<String> savedPlayersChoices;
    @FXML
    private Label loadGameLabel;
    @FXML
    private Group loadGameGroup;
    @FXML
    private ImageView loadGameImage;
    @FXML
    private ImageView playButtonInLoadMenu;
    @FXML
    private ImageView loadMenuImage;
    @FXML
    private ImageView playButton;
    @FXML
    private ImageView loadGameButton;

    private Stage gameStage;
    @FXML
    private ImageView playerMenuImage;
    @FXML
    private Label enterPlayerName;
    @FXML
    private Label invalidPlayerNameLabel;
    @FXML
    private Group playerMenuGroup;
    private AnimationClass animateObject = new AnimationClass();
    private Game game;
    private Parent gameRoot;
    private Scene gameScene;
    private ObjectClass player;
    @FXML
    private ImageView island;
    @FXML
    private ImageView redOrc;
    @FXML
    private ImageView submitButton;
    @FXML
    private TextField playerText;

    public Parent getGameRoot() {
        return gameRoot;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public Game getGame() {
        return game;
    }
    public static TranslateTransition moveObjInScreen(Node node , double x, double y, double duration){
        TranslateTransition t = new TranslateTransition();
        t.setByX(x);
        t.setByY(y);
        t.setNode(node);
        t.setDuration(Duration.millis(duration));
//        t.setCycleCount(TranslateTransition.INDEFINITE);
//        t.setAutoReverse(true);
        return t;
    }
    public void exitInMainMenu(){
        Stage stage = (Stage) exitButtoninMainMenu.getScene().getWindow();
        stage.close();

    }

    public void runTrans(){
        moveObjInScreen(redOrc,0,-250,1000).play();
//        moveObjInScreen(island,350,0,1000).play();
    }
    public static Timeline setDelay(double time){
        return new Timeline(new KeyFrame(Duration.millis(time),e->{}));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        savedGameList= dataObject.getSavedGameList();
        savedPlayerList=dataObject.getSavedPlayerList();
        savedGamesChoices.setValue("Select Game");
        savedGamesChoices.getItems().addAll(savedGameList);

//        for(int i=0;i<savedGameList.size();i++){
//            System.out.println(savedGameList.get(i));
//            System.out.println(savedPlayerList.get(i));
//        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        try {
            this.gameRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Willhero gameObject = new Willhero();
        this.gameStage=gameObject.getWillHeroStage();
        this.game = fxmlLoader.getController();
        this.gameScene = new Scene(this.gameRoot);
        game.gameSetUp(gameScene);
        callPlayerMenuUpwards();



        invalidPlayerNameLabel.setText("");

        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{runTrans();}));
        intro.play();
//        new SequentialTransition(setDelay(1000),intro);
    }
    @FXML
    public void setPlayerName(){
        if(playerText.getText().equals("")){
            try{
                throw new InvalidNameException("The given name is Invalid");
            }
            catch (InvalidNameException e){
                invalidPlayerNameLabel.setText(e.getMessage());
            }

        }
        else{
            boolean flag = false;
            savedPlayerList = dataObject.getSavedPlayerList();
            loadPlayer = new LoadPlayer();
//            System.out.println("gg");
            String filePath = "C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\Saved_Players\\"+ playerText.getText()+".txt";
            for(int i =0;i<savedPlayerList.size();i++){
                if(savedPlayerList.get(i).equals(playerText.getText())){
                    try {
                        player = loadPlayer.getPlayer(filePath);
                        ((PlayerClass)player).setTotalGamesPlayed(((PlayerClass)player).getTotalGamesPlayed()+1);
                        flag=true;

                    } catch (IOException e) {
                        System.out.println("Error");
                    } catch (ClassNotFoundException e) {
                        System.out.println("class not found");
                    }
                }
            }
            if(!flag){
                player=game.getPlayer();
                ((PlayerClass)player).setPlayerName(playerText.getText());
            }
            callPlayerMenuDownwards();
            //System.out.println(player.getPlayerName());
        }

    }

    public String[] getGameDetails(String givenString){
        String[] arr = new String[3];
        int fileBreaker=0;
        int nameBreaker=0;
        for(int i=0;i<givenString.length();i++) {
            if(givenString.charAt(i)==('.'))
                break;
            fileBreaker++;
        }
        String wholeFileName = givenString.substring(0,fileBreaker);
        for(int i=0;i<givenString.length();i++){
            if(givenString.charAt(i)=='-'){
                break;
            }
            nameBreaker++;
        }
        String playerName = wholeFileName.substring(0,nameBreaker)+".txt";
        String gamesPlayed = wholeFileName.split("-")[1];
        arr[0]=wholeFileName;
        arr[1]=playerName;
        arr[2]=gamesPlayed;
        return arr;
    }
    @FXML
    public void loadGame() throws ClassNotFoundException,IOException,NoSavedGameFoundException{
        callLoadingMenuUpwards();
        String selectedGame = savedGamesChoices.getValue();
        if(selectedGame.equals("Select Games")){
            throw new NoSavedGameFoundException("No Saved Games are present");
        }
        String[] gameDetails=getGameDetails(selectedGame);
//        System.out.println(gameDetails[0]);
//        System.out.println(gameDetails[1]);
//        System.out.println(gameDetails[2]);
        String gameFilePath = "C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\Saved_Games\\"+gameDetails[0]+".txt";
        String playerFilePath = "C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\Saved_Players\\"+gameDetails[1];
        game.clearAll();
        player=(PlayerClass) loadPlayer.getPlayer(playerFilePath);
        //game.sceneSetup((PlayerClass) player,gameScene,gameStage);

        game.loadObjects(gameFilePath);
        gameStage.setScene(gameScene);
        gameStage.show();

        class myTimer extends AnimationTimer{
            //             private static int orcjump=1;
            private static int jump=200;
            private Dash moveCheck=new Dash(0,0);
            //private int buffer=0;
            @Override
            public void handle(long l) {
                game.runGame();
                //if(buffer<0) buffer++;
                // if(game.platformCheck()) buffer=0;

                gameScene.setOnKeyPressed(event1 ->{
                    if (event1.getCode() == KeyCode.SPACE) {
                        if(!game.checkPlayerDead() && !game.getPauseStatus() && !game.winStatus() ) {
                            moveCheck.setDash(300);
                            game.addscore();
                            game.useWeapon(1);
                            //buffer++;
                            //if(buffer>=5) buffer=-75;
                        }
                    }

                    else if(event1.getCode()==KeyCode.DIGIT1){
                        game.selectWeapon(1);
                    }

                    else if(event1.getCode()==KeyCode.DIGIT2){
                        game.selectWeapon(2);
                    }

                    else if(event1.getCode()==KeyCode.DIGIT3){
                        game.selectWeapon(3);
                    }
                    else if (event1.getCode()==KeyCode.P){
                        game.setPauseStatus(true);
                        game.setObjectCoordinates();
                        game.sceneSetup((PlayerClass) player,gameScene,gameStage);

                    }

                });
                moveCheck=game.move(moveCheck);
//                game.checkDead();
//                game.checkCoins();
//                game.checkChests();
//                game.checkFallingPlatform();
//                game.moveWeapon();
//                game.generate();
//                game.destroy();


            }
        }
        AnimationTimer timer=new myTimer();
        timer.start();

    }
    @FXML
    public void pressLoadButton(){
//        System.out.println("pressed load button");
        loadGameGroup.toFront();
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{moveLoadingMenuDownwards();}));
        intro.play();
    }

    public void callLoadingMenuUpwards(){

        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{moveLoadingMenuUpwards();}));
        intro.play();

    }
    public void callPlayerMenuDownwards(){

        playerMenuGroup.toFront();
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{movePlayerMenuDownwards();}));
        intro.play();

    }

    @FXML
    public void callPlayerMenuUpwards(){
        playerMenuGroup.toFront();
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{movePlayerMenuUpwards();}));
        intro.play();
    }    public void movePlayerMenuUpwards(){

        animateObject.straightTransition(submitButton,0,-650,500).play();
        animateObject.straightTransition(playerMenuImage,0,-650,500).play();
        animateObject.straightTransition(enterPlayerName,0,-650,500).play();
        animateObject.straightTransition(invalidPlayerNameLabel,0,-650,500).play();
        animateObject.straightTransition(playerText,0,-650,500).play();

    }
    public void movePlayerMenuDownwards(){

        animateObject.straightTransition(submitButton,0,650,500).play();
        animateObject.straightTransition(playerMenuImage,0,650,500).play();
        animateObject.straightTransition(enterPlayerName,0,650,500).play();
        animateObject.straightTransition(invalidPlayerNameLabel,0,650,500).play();
        animateObject.straightTransition(playerText,0,650,500).play();

    }
    public void moveLoadingMenuUpwards(){

        animateObject.straightTransition(savedGamesChoices,0,-650,500).play();
        animateObject.straightTransition(loadGameLabel,0,-650,500).play();
        animateObject.straightTransition(playButtonInLoadMenu,0,-650,500).play();
        animateObject.straightTransition(loadMenuImage,0,-650,500).play();
    }
    public void moveLoadingMenuDownwards(){
        animateObject.straightTransition(savedGamesChoices,0,550,500).play();
        animateObject.straightTransition(loadGameLabel,0,550,500).play();
        animateObject.straightTransition(playButtonInLoadMenu,0,550,500).play();
        animateObject.straightTransition(loadMenuImage,0,550,500).play();

    }

    @FXML
    public void pressPlayButton(MouseEvent event) throws IOException, ClassNotFoundException {
        //Scene scene1 = new Scene(root1,1280,720);
        // gameStage= (Stage) ((Node )event.getSource()).getScene().getWindow();
        //  gameStage.setScene(this.gameScene);

        gameStage.setScene(gameScene);
        gameStage.show();

        class myTimer extends AnimationTimer{
            //             private static int orcjump=1;
            private static int jump=200;
            private Dash moveCheck=new Dash(0,0);
            //private int buffer=0;
            @Override
            public void handle(long l) {

                game.runGame();
                //if(buffer<0) buffer++;
                // if(game.platformCheck()) buffer=0;

                gameScene.setOnKeyPressed(event1 ->{
                    if (event1.getCode() == KeyCode.SPACE) {
                        if(!game.checkPlayerDead() && !game.getPauseStatus() && !game.winStatus() ) {
                            moveCheck.setDash(300);
                            game.addscore();
                            game.useWeapon(1);
                            //buffer++;
                            //if(buffer>=5) buffer=-75;
                        }
                    }

                    else if(event1.getCode()==KeyCode.DIGIT1){
                        game.selectWeapon(1);
                    }

                    else if(event1.getCode()==KeyCode.DIGIT2){
                        game.selectWeapon(2);
                    }

                    else if(event1.getCode()==KeyCode.DIGIT3){
                        game.selectWeapon(3);
                    }
                    else if (event1.getCode()==KeyCode.P){
                        game.setPauseStatus(true);
                        game.setObjectCoordinates();
                        game.sceneSetup((PlayerClass) player,gameScene,gameStage);

                    }

                });
                moveCheck=game.move(moveCheck);
//                game.checkDead();
//                game.checkCoins();
//                game.checkChests();
//                game.checkFallingPlatform();
//                game.moveWeapon();
//                game.generate();
//                game.destroy();


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

