package com.example.final_project;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Willhero extends Application implements Serializable {
    public static Stage willHeroStage;
    public Stage getWillHeroStage() {
        return willHeroStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.willHeroStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
//        controller c = fxmlLoader.getController();
        Scene scene = new Scene(root,1280,720);
        stage.setTitle("Will-Hero");
        stage.setScene(scene);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("file:\\C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\assets\\assets\\MainIcon.png")));
        stage.getIcons().add(new Image("file:\\C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\assets\\assets\\MainIcon.png"));
        //   stage.setResizable(false);

        stage.show();

    }

}