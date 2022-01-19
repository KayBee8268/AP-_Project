package com.example.final_project;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.scene.media.*;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorCompletionService;

import static java.lang.Math.max;


public class Game implements Serializable,Initializable {
    @FXML
    private ImageView exitInWonMenu;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private Label gameSavedLabel;

    @FXML
    private ImageView shurikenImage;
    @FXML
    private ImageView ThrowingKnifeImage;
    @FXML
    private Label weaponLevelfirst;
    @FXML
    private Label weaponLevelSecond;

    private ArrayList<ObjectClass> loadedObjects = new ArrayList<>();
    @FXML
    private Label coinErrorLabel;
    @FXML
    private Label reviveErrorLabel;

    @FXML
    private AnchorPane pauseMenuPane;
    @FXML
    private AnchorPane gamePane;
    private EventHandler<MouseEvent> saveGame;
    private EventHandler<MouseEvent> pauseGame;
    private EventHandler<MouseEvent> resumeGame;
    private EventHandler<MouseEvent> mainMenu;

    private static Stage gameStage;
    private Scene gameScene;

    @FXML
    private Group deathMenu;
    private int moveCounter;
    @FXML
    private ImageView coinImage;
    @FXML
    private Label totalScoreLabel;
    @FXML
    private Label totalCoinsLabel;
    @FXML
    private Label deathLabel;
    private LoadObjects loadObjects = new LoadObjects();

    @FXML
    private ImageView deathMenuImage;

    @FXML
    private ImageView exitButton;
    @FXML
    private ImageView ressurectButton;

    private Game game;
    private DataClass dataObject = new DataClass();
    private Parent gameRoot;
    private PauseMenuController menuController;

    @FXML
    private ImageView hero;
    @FXML
    private Group wonGroup;
    @FXML
    private ImageView playButton;
    private Scene s;
    private ArrayList<ImageView> perishable = new ArrayList<>();
    private AnimationClass animateObject = new AnimationClass();
    private ArrayList<ObjectClass> allObjectRecord = new ArrayList<>();
    private ArrayList<ItemsClass> orcRecord = new ArrayList<>();
    private ArrayList<ItemsClass> platformRecord = new ArrayList<>();
    private ArrayList<ImageView> chest = new ArrayList<>();
    private ArrayList<ObjectClass> Chest = new ArrayList<>();
    private ArrayList<ObjectClass> coin = new ArrayList<>();
    private ArrayList<ItemsClass> fallingPlatformRecord = new ArrayList<>();
    //    private ArrayList<ItemsClass> knifes = new ArrayList<>();
    private static ArrayList<Weapon> weapons = new ArrayList<>();
    private ImageView player;
    private ObjectClass Player;
    private double lastX;
    private double lastY;

    private int obstacleFrequency;
    private boolean obstacle = false;
    private boolean pauseStatus;
    private boolean bossGenerated = false;
    private ItemsClass bossOrc = null;
    private boolean won = false;

    public ObjectClass getPlayer() {
        return Player;
    }

    public String getPlayerFileName() {
        String tempPlayerFile = "C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\Saved_Players";
        String currentPlayerName = null;
        try {
            currentPlayerName = ((PlayerClass) Player).getPlayerName();
        } catch (NullPointerException e) {
            System.out.println("Player not initialized");
        }
        tempPlayerFile = (tempPlayerFile + "\\" + currentPlayerName + ".txt");
        return tempPlayerFile;

    }


    public String getGameFileName() {
        String tempGameFile = "C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\Saved_Games";
        int totalGamesPlayed = 0;
        String currentPlayerName = null;
        try {
            if (Player instanceof PlayerClass) {
                currentPlayerName = ((PlayerClass) Player).getPlayerName();
            }


        } catch (NullPointerException e) {
            System.out.println("Error : Player Not initialized");
            return null;
        }
        if (Player instanceof PlayerClass) {
            totalGamesPlayed = ((PlayerClass) Player).getTotalGamesPlayed();
        }

        tempGameFile = (tempGameFile + "\\" + currentPlayerName + "-" + totalGamesPlayed + ".txt");
        return tempGameFile;
    }

    public void SerializeGame(String givenGameFile) throws IOException {
        // System.out.println("test game file "+givenGameFile);
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(givenGameFile));

            outputStream.writeObject(Player);
            for (ObjectClass temp : allObjectRecord) {
                outputStream.writeObject(temp);
            }

        } finally {
            outputStream.close();
        }

    }

    public void SerializePlayer(String givenPlayerFile) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(givenPlayerFile));
//            System.out.println("In save game" + (Player).getObjectName());
            try {
                outputStream.writeObject(Player);
            } catch (NullPointerException e) {
                System.out.println("Error : Player Not initialized");
            }

        } finally {
            outputStream.close();
        }
    }

    public void setObjectCoordinates() {

        allObjectRecord.get(0).getPosition().setXCoordinate(allObjectRecord.get(0).getObjectImage().getX());
        allObjectRecord.get(0).getPosition().setXDimension(allObjectRecord.get(0).getObjectImage().getFitWidth());
        allObjectRecord.get(0).getPosition().setYCoordinate(allObjectRecord.get(0).getObjectImage().getY());
        allObjectRecord.get(0).getPosition().setYDimension(allObjectRecord.get(0).getObjectImage().getFitHeight());

        allObjectRecord.get(1).getPosition().setXCoordinate(allObjectRecord.get(1).getObjectImage().getX());
        allObjectRecord.get(1).getPosition().setXDimension(allObjectRecord.get(1).getObjectImage().getFitWidth());
        allObjectRecord.get(1).getPosition().setYCoordinate(allObjectRecord.get(1).getObjectImage().getY());
        allObjectRecord.get(1).getPosition().setYDimension(allObjectRecord.get(1).getObjectImage().getFitHeight());

        allObjectRecord.get(2).getPosition().setXCoordinate(allObjectRecord.get(2).getObjectImage().getX());
        allObjectRecord.get(2).getPosition().setXDimension(allObjectRecord.get(2).getObjectImage().getFitWidth());
        allObjectRecord.get(2).getPosition().setYCoordinate(allObjectRecord.get(2).getObjectImage().getY());
        allObjectRecord.get(2).getPosition().setYDimension(allObjectRecord.get(2).getObjectImage().getFitHeight());

        allObjectRecord.get(3).getPosition().setXCoordinate(allObjectRecord.get(3).getObjectImage().getX());
        allObjectRecord.get(3).getPosition().setXDimension(allObjectRecord.get(3).getObjectImage().getFitWidth());
        allObjectRecord.get(3).getPosition().setYCoordinate(allObjectRecord.get(3).getObjectImage().getY());
        allObjectRecord.get(3).getPosition().setYDimension(allObjectRecord.get(3).getObjectImage().getFitHeight());
        for (ObjectClass temp : platformRecord) {
            temp.getPosition().setXCoordinate(temp.getObjectImage().getX());
            temp.getPosition().setYCoordinate(temp.getObjectImage().getY());
            temp.getPosition().setXDimension(temp.getObjectImage().getFitWidth());
            temp.getPosition().setYDimension(temp.getObjectImage().getFitHeight());
            allObjectRecord.add(temp);
        }
        for (ObjectClass temp : Chest) {
            temp.getPosition().setXCoordinate(temp.getObjectImage().getX());
            temp.getPosition().setYCoordinate(temp.getObjectImage().getY());
            temp.getPosition().setXDimension(temp.getObjectImage().getFitWidth());
            temp.getPosition().setYDimension(temp.getObjectImage().getFitHeight());
            allObjectRecord.add(temp);
        }
        for (ObjectClass temp : coin) {
            temp.getPosition().setXCoordinate(temp.getObjectImage().getX());
            temp.getPosition().setYCoordinate(temp.getObjectImage().getY());
            temp.getPosition().setXDimension(temp.getObjectImage().getFitWidth());
            temp.getPosition().setYDimension(temp.getObjectImage().getFitHeight());
            allObjectRecord.add(temp);
        }
        for (ObjectClass temp : fallingPlatformRecord) {
            temp.getPosition().setXCoordinate(temp.getObjectImage().getX());
            temp.getPosition().setYCoordinate(temp.getObjectImage().getY());
            temp.getPosition().setXDimension(temp.getObjectImage().getFitWidth());
            temp.getPosition().setYDimension(temp.getObjectImage().getFitHeight());
            allObjectRecord.add(temp);
        }
        for (ObjectClass temp : orcRecord) {
            temp.getPosition().setXCoordinate(temp.getObjectImage().getX());
            temp.getPosition().setYCoordinate(temp.getObjectImage().getY());
            temp.getPosition().setXDimension(temp.getObjectImage().getFitWidth());
            temp.getPosition().setYDimension(temp.getObjectImage().getFitHeight());
            allObjectRecord.add(temp);
        }

    }

    public boolean checkCoinException() {
        boolean flag = false;

        return flag;
    }

    public void setDeathMenu(){
        Node node = deathMenu.getChildren().get(2);
        ((VBox)node).getChildren().get(1).setOnMouseClicked(mainMenu);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reviveErrorLabel.setText("");
        coinErrorLabel.setText("");
        saveGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    SerializePlayer(getPlayerFileName());
                    SerializeGame(getGameFileName());
//                    System.out.println("Hello world");
//                    Parent pauseRoot = null;
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
//                    try {
//                        pauseRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    PauseMenuController pauseController = loader.getController();
//                    pauseController.callFeedBackDownwards();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        resumeGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gamePane.getChildren().remove(pauseMenuPane);
                pauseStatus = false;
            }
        };



        mainMenu = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), actionEvent -> {
                    Parent parentRoot = null;
                    controller controllertomainMenu = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                        parentRoot = loader.load();
                        controllertomainMenu = loader.getController();
                        Scene menuScene = new Scene(parentRoot, 1280, 720);

                        Willhero getStage =  new Willhero();
                        gameStage=getStage.getWillHeroStage();
                        gameStage.setScene(menuScene);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }));
                timeline.play();

            }
        };
    }

    public void setPlayerCoordinates(ObjectClass player) {

    }

    public void setPlatformCoordinates(ItemsClass platform) {
        platform.getPosition().setXCoordinate(platform.getObjectImage().getX());
        platform.getPosition().setYCoordinate(platform.getObjectImage().getY());
        platform.getPosition().setXDimension(platform.getObjectImage().getFitWidth());
        platform.getPosition().setYDimension(platform.getObjectImage().getFitHeight());
    }

    public void gameSetUp(Scene givenScene) {
        this.s = givenScene;
        this.gameScene = givenScene;

        Player = new PlayerClass(400, 475, 50, 70);
        setPlayerCoordinates(Player);
        Player.setObjectName("Player");
        allObjectRecord.add(Player);
        player = Player.getObjectImage();

//        //1
//        PlatformClass platform = new PlatformClass(50, 545, 150, 50,1);
//        platformRecord.add(platform.getPlatform());
        //2


        ItemsClass platform = new PlatformClass(270, 545, 300, 50, 2);
        platform.setObjectName("Platform");
        setPlatformCoordinates(platform);
        allObjectRecord.add(platform);
        platformRecord.add(platform);
        //3
        ItemsClass platform2 = new PlatformClass(700, 520, 100, 70, 3);
        platform2.setObjectName("Platform");
        setPlatformCoordinates(platform2);
        allObjectRecord.add(platform2);
        platformRecord.add(platform2);

        //4
        ItemsClass platform3 = new PlatformClass(900, 490, 200, 80, 5);
        platform3.setObjectName("Platform");
        setPlatformCoordinates(platform3);
        allObjectRecord.add(platform3);
        platformRecord.add(platform3);


        lastX = 900;
        lastY = 490;
        pauseStatus = false;
        for (ItemsClass temp : platformRecord) {
            ((AnchorPane) s.getRoot()).getChildren().add(temp.getObjectImage());
        }


        ((AnchorPane) s.getRoot()).getChildren().add(player);

    }

    public void playCoinSound() {
        String ssound = "file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/mario_coin_sound.mp3";
        Media sound = new Media(ssound);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();


    }

    public void runGame() {
        if (((PlayerClass) Player).getTotalScore() < 200) {
            generate();
        } else if (((PlayerClass) Player).getTotalScore() >= 200 && !bossGenerated && lastX < 1280) {
            generateBoss();
            bossGenerated = true;
        }

        orcJump();
        playerJump();
        checkDead();
        checkCoins();
        checkChests();
        checkFallingPlatform();
        moveWeapon();
        destroy();
        checkWin();
        setPlayerData();
        movePerishable();
    }

    public void sceneSetup(PlayerClass givenPlayer, Scene givenScene, Stage givenStage) {
        this.gameStage = givenStage;
        this.gameScene = givenScene;
        this.Player = givenPlayer;
//        System.out.println("setting up the scene");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
        try {
//            System.out.println("Inside try catch");
            pauseMenuPane = (AnchorPane) loader.load();
            pauseMenuPane.getChildren().get(1).setOnMouseClicked(resumeGame);
            pauseMenuPane.getChildren().get(2).setOnMouseClicked((saveGame));
            pauseMenuPane.getChildren().get(3).setOnMouseClicked((mainMenu));
            gamePane.getChildren().add(pauseMenuPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int ran(int x) {
        return (int) (Math.random() * 100 % x);
    }

    public void generate() {
        int minGap = 100;
        int minLength = 200;
        int minHeight = 40;

        int type = ran(10) + 1;
        if (type > 8) {
            minGap = 300;
            minLength = 300;
            minHeight = 50;
        } else if (type > 5) {
            minGap = 200;
            minLength = 50;
        } else {
            minGap *= type;
            minLength *= type;
        }

        if (lastX <= 1280 - minGap) {
            if (lastY > 600) lastY = 600;
            if (lastY < 400) lastY = 450;
            double x, y;
            if (obstacle == false) {
                x = 1280 + ran(minGap);
                y = lastY + ran(minHeight * 2) - minHeight;
            } else {
                x = lastX;
                y = lastY;
            }
            obstacle = false;
            ItemsClass platform = new PlatformClass(x, y, minLength + ran(minLength), minHeight + ran(20), ran(7) + 1);
            platform.setObjectName("Platform");
            platformRecord.add(platform);
            ((AnchorPane) s.getRoot()).getChildren().add(platform.getObjectImage());
            lastX = platform.getObjectImage().getX() + platform.getObjectImage().getFitWidth();
            lastY = platform.getObjectImage().getY();
            if (platform.getObjectImage().getFitWidth() > 100) {
                int spawn = ran(10) + 1;
                if (spawn == 1) {
                    ObjectClass chest1;
                    if (ran(2) == 0) {
                        chest1 = new CoinChestClass(platform.getObjectImage().getX() + (platform.getObjectImage().getFitWidth() / 2) - 37, platform.getObjectImage().getY() - 50, 75, 50, 4 + ran(3));
                        chest1.setObjectName("CoinChest");
                    } else {
                        chest1 = new WeaponChestClass(platform.getObjectImage().getX() + (platform.getObjectImage().getFitWidth() / 2) - 37, platform.getObjectImage().getY() - 50, 75, 50, ran(2) + 1);
                        chest1.setObjectName("WeaponChest");
                    }
                    Chest.add(chest1);
                    chest.add(chest1.getObjectImage());
                    ((AnchorPane) s.getRoot()).getChildren().add(chest1.getObjectImage());
                }

                if (spawn == 2) {
                    ObjectClass Coin = new CoinClass(platform.getObjectImage().getX() + (platform.getObjectImage().getFitWidth() / 2) - 12, platform.getObjectImage().getY() - 100, 25, 25);
                    Coin.setObjectName("Coin");
                    coin.add(Coin);
                    ((AnchorPane) s.getRoot()).getChildren().add(Coin.getObjectImage());

                    ObjectClass Coin2 = new CoinClass(Coin.getObjectImage().getX() - 75, Coin.getObjectImage().getY(), 25, 25);
                    Coin2.setObjectName("Coin");
                    coin.add(Coin2);
                    ((AnchorPane) s.getRoot()).getChildren().add(Coin2.getObjectImage());

                    Coin2 = new CoinClass(Coin.getObjectImage().getX() + 75, Coin.getObjectImage().getY(), 25, 25);
                    Coin2.setObjectName("Coin");
                    coin.add(Coin2);
                    ((AnchorPane) s.getRoot()).getChildren().add(Coin2.getObjectImage());
                } else if (spawn > 5) {
                    if (ran(2) == 0) {
                        int H = 50 + ran(20);
                        ItemsClass orc = new GreenOrc(platform.getObjectImage().getX() + platform.getObjectImage().getFitWidth() / 2 + ran((int) (platform.getObjectImage().getFitWidth() / 2 - H)), platform.getObjectImage().getY() - H, H, H);
                        orc.setObjectName("GreenOrc");
                        orcRecord.add(orc);
                        ((AnchorPane) s.getRoot()).getChildren().add(orc.getObjectImage());
                    } else {
                        int H = 50 + ran(50);
                        ItemsClass orc = new RedOrc(platform.getObjectImage().getX() + platform.getObjectImage().getFitWidth() / 2 + ran((int) (platform.getObjectImage().getFitWidth() / 2 - H)), platform.getObjectImage().getY() - H, H, H);
                        orc.setObjectName("RedOrc");
                        orcRecord.add(orc);
                        ((AnchorPane) s.getRoot()).getChildren().add(orc.getObjectImage());

                    }
                } else if (spawn == 3 && obstacleFrequency <= 0) {
                    for (ItemsClass temp : fallingPlatformRecord) {
                        ((AnchorPane) s.getRoot()).getChildren().remove(temp.getObjectImage());
                    }
                    fallingPlatformRecord.clear();
                    ItemsClass fallingPlatform;
                    double temp = platform.getObjectImage().getX() + platform.getObjectImage().getFitWidth();
                    int n = 0;
                    for (double i = temp; i < temp + 500; i += 50) {
                        fallingPlatform = new ObstacleClass(i, platform.getObjectImage().getY(), 50, 10, n);
                        n = 1;
                        fallingPlatform.setObjectName("FallingPlatform");
                        fallingPlatformRecord.add(fallingPlatform);
                        lastX = fallingPlatform.getObjectImage().getX() + fallingPlatform.getObjectImage().getFitWidth();

                        lastY = fallingPlatform.getObjectImage().getY();
                    }
                    obstacleFrequency = 2000;

                    for (ItemsClass temp6 : fallingPlatformRecord) {
                        ((AnchorPane) s.getRoot()).getChildren().add(temp6.getObjectImage());
                    }
                    obstacle = true;
                }


            }
        }
    }

    public void generateBoss() {

        int minGap = 200;
        int minLength = 1500;
        int minHeight = 40;

        if (lastY > 600) lastY = 600;
        if (lastY < 400) lastY = 450;
        double x, y;

        if (obstacle == false) {
            x = 1280 + ran(minGap);
            y = lastY + ran(minHeight * 2) - minHeight;
        } else {
            x = lastX;
            y = lastY;
        }
        obstacle = false;

        ItemsClass platform = new PlatformClass(x, y, minLength + ran(minLength), minHeight + ran(20), ran(7) + 1);
        platformRecord.add(platform);
        ((AnchorPane) s.getRoot()).getChildren().add(platform.getObjectImage());


        int H = 200 + ran(20);
        ItemsClass orc = new BossOrc(platform.getObjectImage().getX() + platform.getObjectImage().getFitWidth() / 2 + ran((int) (platform.getObjectImage().getFitWidth() / 2 - H)), 0, H, H);
        orcRecord.add(orc);
        ((AnchorPane) s.getRoot()).getChildren().add(orc.getObjectImage());
        bossOrc = orc;

    }

    public void destroy() {
        ItemsClass temp1 = null;
        for (ItemsClass orc : orcRecord) {
            if (orc.getObjectImage().getBoundsInParent().getMaxX() < 0 || orc.getObjectImage().getY() > 720) {
                temp1 = orc;
                break;
            }
        }
        if (temp1 != null) {
            orcRecord.remove(temp1);
            if (((OrcClass) temp1).getisDead()) ((PlayerClass) Player).setCoins(((PlayerClass) Player).getCoins() + 1);
            ((AnchorPane) s.getRoot()).getChildren().remove(temp1.getObjectImage());
        }

        ItemsClass temp2 = null;
        for (ItemsClass p : platformRecord) {
            if (p.getObjectImage().getBoundsInParent().getMaxX() < 0 || p.getObjectImage().getY() > 720) {
                temp2 = p;
                break;
            }
        }
        if (temp2 != null) {
            platformRecord.remove(temp2);
            ((AnchorPane) s.getRoot()).getChildren().remove(temp2.getObjectImage());
        }

        ObjectClass temp3 = null;
        for (ObjectClass temp : coin) {
            if (temp.getObjectImage().getBoundsInParent().getMaxX() < 0 || temp.getObjectImage().getY() > 720) {
                temp3 = temp;
                break;
            }
        }
        if (temp3 != null) {
            coin.remove(temp3);
            ((AnchorPane) s.getRoot()).getChildren().remove(temp3.getObjectImage());
        }

        ObjectClass temp4 = null;
        for (ObjectClass temp : Chest) {
            if (temp.getObjectImage().getBoundsInParent().getMaxX() < 0 || temp.getObjectImage().getY() > 720) {
                temp4 = temp;
                break;
            }
        }
        if (temp4 != null) {
            Chest.remove(temp4);
            ((AnchorPane) s.getRoot()).getChildren().remove(temp4.getObjectImage());
        }

        ItemsClass temp5 = null;
        for (ItemsClass temp : fallingPlatformRecord) {
            if (temp.getObjectImage().getBoundsInParent().getMaxX() < 0 || temp.getObjectImage().getY() > 720) {
                temp5 = temp;
                break;
            }
        }
        if (temp5 != null) {
            fallingPlatformRecord.remove(temp5);
            ((AnchorPane) s.getRoot()).getChildren().remove(temp5.getObjectImage());
        }
        ImageView temp6 = null;
        for (ImageView temp : perishable) {
            if (temp.getBoundsInParent().getMaxY() < 0 || temp.getBoundsInParent().getMaxX() < 0) {
                temp6 = temp;
                break;
            }
        }
        if (temp6 != null) {
            perishable.remove(temp6);
            ((AnchorPane) s.getRoot()).getChildren().remove(temp6);
        }

    }

    @FXML
    public void revive() {
        int coinRequired = 20;
        if(((PlayerClass) Player).getRessurected()){
            try {

                throw new SingleReviveAllowedException("Can Only Revive Once");

            } catch (SingleReviveAllowedException e) {

                coinErrorLabel.setText(e.getMessage());
            }
        }
        else if (((PlayerClass) Player).getCoins() < coinRequired) {
            try {

                throw new NotEnoughCoinsException("Not Enough Coins present");
            } catch (NotEnoughCoinsException e) {

                coinErrorLabel.setText(e.getMessage());
            }
        }

        if (((PlayerClass) Player).getCoins() >= coinRequired && !((PlayerClass) Player).getRessurected()) {
            callDeathMenuDownwards();
            ((PlayerClass) Player).setCoins(((PlayerClass) Player).getCoins() - coinRequired);
            ((PlayerClass) Player).setDead(false);
            ((PlayerClass) Player).setRessurected(true);

            for (ItemsClass p : platformRecord) {
                if (p.getObjectImage().getX() > 400) {
                    double diff = p.getObjectImage().getX() - 420;
                    for (ItemsClass temp : fallingPlatformRecord) {
                        temp.getObjectImage().setX(temp.getObjectImage().getX() - diff);
                    }

                    for (ItemsClass temp : platformRecord) {
                        temp.getObjectImage().setX(temp.getObjectImage().getX() - diff);
                    }
                    lastX -= diff;
                    obstacleFrequency -= diff;
                    for (ObjectClass temp : Chest) {
                        temp.getObjectImage().setX(temp.getObjectImage().getX() - diff);
                    }
                    for (ObjectClass temp : coin) {
                        temp.getObjectImage().setX(temp.getObjectImage().getX() - diff);
                    }

                    player.setY(0);

                    break;
                }
            }

        }
    }

    public boolean platformCheck() {
        return (PlatformClass.checkPlatform(player, 1) || ObstacleClass.checkFallingPlatform(player, 1));

    }
//    public void UseWeapon(){
//        Player.useWeapon(s);
//    }

    public boolean checkPlayerDead() {
//        if (Player instanceof PlayerClass){
        return ((PlayerClass) Player).getDead();
    }

    public Dash move(Dash moveCheck) {
        int speed = 5;
        if (!PlatformClass.checkPlatform(player, 1) && !((PlayerClass) Player).getDead()) {
            for (ItemsClass temp : platformRecord) {
                if (temp.getObjectImage().getBoundsInParent().intersects(player.getBoundsInParent())) {
                    moveCheck.setDash(0);
                    ((PlayerClass) Player).setVal(1);
                }
            }
        }
        if (moveCheck.getDash() > 0) {
            ((PlayerClass) Player).setVal(0);
            for (ItemsClass temp : orcRecord) {
                temp.getObjectImage().setX(temp.getObjectImage().getX() - speed);
                if (player.getBoundsInParent().intersects(temp.getObjectImage().getBoundsInParent()) && player.getY() < temp.getObjectImage().getBoundsInParent().getMaxY() && temp.getObjectImage().getY() < player.getBoundsInParent().getMaxY()) {
                    moveCheck.setOrc(temp.getObjectImage());
                    moveCheck.setPushorc(max(moveCheck.getDash(), 50));
                    moveCheck.setOrc(temp.getObjectImage());
                    moveCheck.setPushorc(max(moveCheck.getDash(), 50));
                    moveCheck.setDash(0);
                }
                //                if((temp.getObjectImage() .getY()<player.getY()+player.getFitHeight()+3) &&(temp.getObjectImage() .getY()>player.getY()+player.getFitHeight()-3) && (player.getX()+player.getFitWidth()>temp.getObjectImage() .getX()) && (temp.getObjectImage() .getX()+temp.getObjectImage() .getFitWidth()>player.getX())){
                //                    temp.setisDead(true);
                //                }
            }
            for (ImageView temp : perishable) {
                temp.setX(temp.getX() - speed);
            }


            for (ItemsClass temp : fallingPlatformRecord) {
                temp.getObjectImage().setX(temp.getObjectImage().getX() - speed);
            }

            for (ItemsClass temp : platformRecord) {
                temp.getObjectImage().setX(temp.getObjectImage().getX() - speed);
            }
            lastX -= speed;
            obstacleFrequency -= speed;
            for (ObjectClass temp : Chest) {
                temp.getObjectImage().setX(temp.getObjectImage().getX() - speed);
            }
            for (ObjectClass temp : coin) {
                temp.getObjectImage().setX(temp.getObjectImage().getX() - speed);
            }
            moveCheck.setDash(moveCheck.getDash() - speed);
            if (moveCheck.getDash() <= 0) {
                ((PlayerClass) Player).setVal(-1);
            }
        }

        if (moveCheck.getPushorc() > 0) {
            moveCheck.getOrc().setX(moveCheck.getOrc().getX() + speed);
            moveCheck.setPushorc(moveCheck.getPushorc() - speed);
            if (moveCheck.getPushorc() <= 0) {
                moveCheck.setOrc(null);
            }
        }
        return moveCheck;
    }

    public void movePerishable() {
        for (ImageView temp : perishable) {
            temp.setY(temp.getY() - 5);
        }
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
    public void addscore() {
        ((PlayerClass) Player).setTotalScore(((PlayerClass) Player).getTotalScore() + 1);
    }

    public void checkDead() {
        for (ItemsClass temp : orcRecord) {
            if (!((PlayerClass) Player).getDead() && !((OrcClass) temp).getisDead()) {
                if ((temp.getObjectImage().getY() < player.getY() + player.getFitHeight() + 5) && (temp.getObjectImage().getY() > player.getY() + player.getFitHeight() - 5) && (player.getX() + player.getFitWidth() > temp.getObjectImage().getX()) && (temp.getObjectImage().getX() + temp.getObjectImage().getFitWidth() > player.getX())) {
                    ((OrcClass) temp).setisDead(true);
                } else if ((temp.getObjectImage().getY() + temp.getObjectImage().getFitHeight() + 5 > player.getY()) && (temp.getObjectImage().getY() + temp.getObjectImage().getFitHeight() - 5 < player.getY()) && (player.getX() + player.getFitWidth() > temp.getObjectImage().getX()) && (temp.getObjectImage().getX() + temp.getObjectImage().getFitWidth() > player.getX())) {
                    ((PlayerClass) Player).setDead(true);
                    setDeathMenu();
                    callDeathMenuUpwards();

                } else if (player.getBoundsInParent().intersects(temp.getObjectImage().getBoundsInParent()) && player.getBoundsInParent().getMaxY() < temp.getObjectImage().getY() + 4) {
                    ((OrcClass) temp).setisDead(true);

                }
            }
        }
        if (player.getY() > 720 && !((PlayerClass) Player).getDead()) {
            ((PlayerClass) Player).setDead(true);
            setDeathMenu();
            callDeathMenuUpwards();
        }
    }

    public void setPauseStatus(boolean val) {
        pauseStatus = val;
    }

    public boolean getPauseStatus() {
        return pauseStatus;
    }


    public void playerJump() {
        ((PlayerClass) Player).jump();
        ((PlayerClass) Player).drop();
    }

    public void setupEnvironment() {
        Willhero gameObject = new Willhero();
        this.gameStage = gameObject.getWillHeroStage();
    }

    public void orcJump() {
        for (ItemsClass temp : orcRecord) {
            ((OrcClass) temp).jump();
            ((OrcClass) temp).drop();
        }
    }

    public void checkCoins() {
        ObjectClass k = null;
        for (ObjectClass temp : coin) {
            if (player.getBoundsInParent().intersects(temp.getObjectImage().getBoundsInParent())) {
                ((PlayerClass) Player).setCoins(((PlayerClass) Player).getCoins() + 1);
                ((AnchorPane) s.getRoot()).getChildren().remove(temp.getObjectImage());
                playCoinSound();
                k = temp;
                break;
            }
        }
        if (k != null)
            coin.remove(k);
    }

    public void checkFallingPlatform() {
        ObstacleClass.fall();
    }

    public void checkChests() {
        for (ObjectClass temp : Chest) {
            if (player.getBoundsInParent().intersects(temp.getObjectImage().getBoundsInParent())) {
                if (!((ChestClass) temp).getOpen()) {
                    ((ChestClass) temp).awardItem(((PlayerClass) Player));
                    ((ChestClass) temp).setOpen(true);

                    if (temp instanceof ChestClass) {
                        ImageView ani = new ImageView();

                        if (temp instanceof CoinChestClass) {
                            Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Coin.png");
                            ani.setImage(image);
                            ani.setFitHeight(25);
                            ani.setFitWidth(25);
                        } else if (temp instanceof WeaponChestClass) {
                            int type = ((WeaponChestClass) temp).getWeaponType();
                            if (type == 1) {
                                Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/WeaponKnife.png");
                                ani.setImage(image);
                                ani.setFitHeight(15);
                                ani.setFitWidth(50);
                            } else if (type == 2) {
                                Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/WeaponShuriken.png");
                                ani.setImage(image);
                                ani.setFitHeight(40);
                                ani.setFitWidth(40);
                            }
                        }
                        ani.setY(temp.getObjectImage().getBoundsInParent().getCenterY());
                        ani.setX(temp.getObjectImage().getBoundsInParent().getMinX() + 20);
                        ((AnchorPane) s.getRoot()).getChildren().add(ani);
                        perishable.add(ani);
                    }
                }
            }
        }
    }

    public void checkWin() {
        if (!won && bossOrc != null && (((BossOrc) bossOrc).getisDead() || (!((BossOrc) bossOrc).getisDead() && bossOrc.getObjectImage().getBoundsInParent().getMaxX() < player.getX()))) {
            won = true;
            callWonMenuDownwards();

        }
    }

    public boolean winStatus() {
        return won;
    }

    public void setPlayerData() {
        if (Player instanceof PlayerClass) {
            weaponLevelfirst.setText(String.valueOf(((PlayerClass) Player).getWeapon1lvl()));
            weaponLevelSecond.setText(String.valueOf(((PlayerClass) Player).getWeapon2lvl()));
            totalScoreLabel.setText(String.valueOf(((PlayerClass) Player).getTotalScore()));
            totalCoinsLabel.setText(String.valueOf(((PlayerClass) Player).getCoins()));
        }

    }

    public void useWeapon(int x) {

        if (((PlayerClass) Player).getType() == 1) {
            Weapon knife = new ThrowingKnifeClass(player);
            weapons.add(knife);
            ((AnchorPane) s.getRoot()).getChildren().add(knife.getImageView());
        } else if (((PlayerClass) Player).getType() == 2) {
            Weapon shuriken = new ShurikenClass(player);
            weapons.add(shuriken);
            ((AnchorPane) s.getRoot()).getChildren().add(shuriken.getImageView());
        }
    }

    public void selectWeapon(int type) {
        ((PlayerClass) Player).selectWeapon(type);
    }

    public void moveWeapon() {
        Weapon k = null;
        for (Weapon temp : weapons) {
            temp.move();

            for (ItemsClass orc : orcRecord) {
                if (orc.getObjectImage().getBoundsInParent().intersects(temp.getImageView().getBoundsInParent())) {
                    ((OrcClass) orc).setHP(((OrcClass) orc).getHP() - temp.getDamage());
                    if (((OrcClass) orc).getHP() <= 0) {
//                        ((PlayerClass)Player).setCoins(((PlayerClass)Player).getCoins()+1);
                    }
                    ((AnchorPane) s.getRoot()).getChildren().remove(temp.getImageView());
                    k = temp;
                    break;
                }
            }

            if (temp.getDistance() <= 0) {
                ((AnchorPane) s.getRoot()).getChildren().remove(temp.getImageView());
                k = temp;
                break;

            }
        }
        if (k != null)
            weapons.remove(k);
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
//    @FXML
//    public void pressPauseButton(MouseEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
//        Parent root1 = fxmlLoader.load();
////        controller c = fxmlLoader.getController();
//        Scene scene2 = new Scene(root1,1280,720);
//        Stage stage1 = (Stage) ((Node )event.getSource()).getScene().getWindow();
//        stage1.setScene(scene2);
//        stage1.show();
//
//    }
    public void callDeathMenuDownwards() {
        deathMenu.toFront();
        setPlayerData();
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            moveDeathMenuDownwards();
        }));
        intro.play();

    }

    public void moveDeathMenuDownwards() {

        animateObject.straightTransition(ressurectButton, 0, 750, 500).play();
        animateObject.straightTransition(exitButton, 0, 750, 500).play();
        animateObject.straightTransition(deathMenuImage, 0, 750, 500).play();
        animateObject.straightTransition(deathLabel, 0, 750, 500).play();
        animateObject.straightTransition(coinErrorLabel, 0, 750, 500).play();
        animateObject.straightTransition(reviveErrorLabel, 0, 750, 500).play();
       // animateObject.straightTransition(deathLabel, 0, 750, 500).play();
        // animateObject.straightTransition(totalCoinsLabel,0,750,500).play();
        //animateObject.straightTransition(totalScoreLabel,0,750,500).play();
        //animateObject.straightTransition(coinImage,0,750,500).play();

    }

    public void callWonMenuDownwards(){
        wonGroup.toFront();
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{moveWonMenuDownwrds(); }));
        intro.play();

    }
    public void moveWonMenuDownwrds(){

        animateObject.straightTransition(exitInWonMenu,0,500,500).play();
        animateObject.straightTransition(gameSavedLabel,0,500,500).play();
        animateObject.straightTransition(backgroundImage,0,500,500).play();
//        animateObject.straightTransition(deathLabel,0,750,500).play();
//        animateObject.straightTransition(coinErrorLabel, 0, 750, 500).play();
//        animateObject.straightTransition(reviveErrorLabel, 0, 750, 500).play();
        //        animateObject.straightTransition(totalCoinsLabel,0,-750,500).play();
//        animateObject.straightTransition(totalScoreLabel,0,-750,500).play();
//        animateObject.straightTransition(coinImage,0,-750,500).play();

    }
    @FXML
    public void pressExitInWonMenu(){
        Stage stage = (Stage) exitInWonMenu.getScene().getWindow();
        stage.close();
    }



    @FXML
    public void callDeathMenuUpwards(){
        deathMenu.toFront();
        setPlayerData();
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1),e ->{moveDeathMenuUpwards();}));
        intro.play();

    }    public void moveDeathMenuUpwards(){

        animateObject.straightTransition(ressurectButton,0,-750,500).play();
        animateObject.straightTransition(exitButton,0,-750,500).play();
        animateObject.straightTransition(deathMenuImage,0,-750,500).play();
        animateObject.straightTransition(deathLabel,0,-750,500).play();
        animateObject.straightTransition(coinErrorLabel, 0, -750, 500).play();
        animateObject.straightTransition(reviveErrorLabel, 0, -750, 500).play();
        //        animateObject.straightTransition(totalCoinsLabel,0,-750,500).play();
//        animateObject.straightTransition(totalScoreLabel,0,-750,500).play();
//        animateObject.straightTransition(coinImage,0,-750,500).play();

    }

//    @FXML
//    public void setWonMenu(){
//        ((Group)(gamePane.getChildren().get(9))).getChildren().get(2).setOnMouseClicked(mainMenu);
//    }

    public void clearAll(){
        ((AnchorPane) s.getRoot()).getChildren().remove(player);
        for(ObjectClass temp : coin){
            ((AnchorPane) s.getRoot()).getChildren().remove(temp.getObjectImage());

        }
        coin.clear();
        for(ObjectClass temp : Chest){
            ((AnchorPane) s.getRoot()).getChildren().remove(temp.getObjectImage());

        }
        Chest.clear();
        for(ItemsClass temp : platformRecord){
            ((AnchorPane) s.getRoot()).getChildren().remove(temp.getObjectImage());

        }
        platformRecord.clear();
        PlatformClass.listClear();
        for(ObjectClass temp : orcRecord){
            ((AnchorPane) s.getRoot()).getChildren().remove(temp.getObjectImage());

        }
        orcRecord.clear();
        for(ItemsClass temp : fallingPlatformRecord){
            ((AnchorPane) s.getRoot()).getChildren().remove(temp.getObjectImage());

        }
        fallingPlatformRecord.clear();
        ObstacleClass.listClear();

    }

    public void loadObjects(String gameFileName) throws IOException, ClassNotFoundException {
        double tempx =0;
        double tempy =0;

        loadedObjects= loadObjects.deserializeObjects(gameFileName);
       // System.out.println("total score"+((PlayerClass)loadedObjects.get(0)).getTotalScore());

        for(ObjectClass temp : loadedObjects){
            if (temp instanceof PlayerClass){
                Player =  temp;

                Player.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Helmet3.png");
                player=Player.getObjectImage();
                ((AnchorPane) s.getRoot()).getChildren().add(Player.getObjectImage());
            }
            else if (temp instanceof CoinClass){
                ObjectClass c  = temp;
                c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Coin.png");
                coin.add(c);
                ((AnchorPane) s.getRoot()).getChildren().add(c.getObjectImage());
            }
            else if (temp instanceof OrcClass){
                if (temp.getObjectName().equals("GreenOrc")){
                    ItemsClass c = (ItemsClass) temp;
                    c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Orc1.png");
                    ((AnchorPane) s.getRoot()).getChildren().add(c.getObjectImage());
                    orcRecord.add(c);
                }
                else if (temp.getObjectName().equals("RedOrc")){
                     ItemsClass c  = (ItemsClass) temp;
                    c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/RedOrc1.png");
                    orcRecord.add(c);
                    ((AnchorPane) s.getRoot()).getChildren().add(c.getObjectImage());
                }
                else if(temp.getObjectName().equals("BossOrc")){
                    ItemsClass c = (ItemsClass) temp;
                    c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/OrcBoss.png");
                    orcRecord.add(c);
                    ((AnchorPane) s.getRoot()).getChildren().add(c.getObjectImage());
                }
            }
            else if (temp instanceof ObstacleClass){
                ItemsClass c = (ItemsClass) temp;
                c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/fallingPlatform.png");
                fallingPlatformRecord.add(c);
                ObstacleClass.addObstacles(c.getObjectImage());
                tempx=c.getObjectImage().getBoundsInParent().getMaxX();
                tempy=c.getObjectImage().getY();
                ((AnchorPane) s.getRoot()).getChildren().add(c.getObjectImage());
            }
            else if (temp instanceof ChestClass){
                ObjectClass c = temp;
                if(((ChestClass)c).isOpen){
                    c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/ChestOpen.png");
                }
                else{
                    c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/ChestClosed.png");
                }
                Chest.add(c);
                ((AnchorPane) s.getRoot()).getChildren().add(c.getObjectImage());
            }
            else if (temp instanceof PlatformClass){
                ItemsClass c = (ItemsClass) temp;
                int type=((PlatformClass)c).getPlatformType();
                c.makeImage("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands"+type+".png");
                platformRecord.add(c);
                PlatformClass.addPlatform(c.getObjectImage());
                ((AnchorPane) s.getRoot()).getChildren().add(c.getObjectImage());
                lastX=c.getObjectImage().getBoundsInParent().getMaxX();
                lastY=c.getObjectImage().getY();
            }

            lastX=max(tempx,lastX);
            lastY=max(tempy,lastY);

        }
//
//        for(int i=0;i<loadedObjects.size();i++){
//            if(loadedObjects.get(i) instanceof PlayerClass){
//                Player=loadedObjects.get(i);
//                System.out.println("Coordinates"+Player.getObjectImage().getY());
//                System.out.println("gg "+ Player.getObjectName());
//            }
//
//            loadedObjects.get(i).display(gamePane);
//        }

    }
    public void exitInDeathMenu(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void pressPauseButton(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//        System.out.println("gg");
        this.gameRoot = fxmlLoader.load();
        controller menuController = fxmlLoader.getController();
        FXMLLoader fxmlLoaderPause = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
        Parent pauseRoot = fxmlLoaderPause.load();
        this.game = menuController.getGame();
        //dataObject.saveGame(game);
//        System.out.println("GAME SAVED");
        //menuController.pressSaveButton();
        // controller c = fxmlLoader.getController();
        Scene scene2 = new Scene(pauseRoot);
        Stage stage1 = (Stage) ((Node )event.getSource()).getScene().getWindow();
        stage1.setScene(scene2);
        stage1.show();

    }

}
