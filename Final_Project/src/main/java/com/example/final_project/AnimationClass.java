package com.example.final_project;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationClass {

    public TranslateTransition straightTransition(Node node , double x, double y, double duration){
        TranslateTransition t = new TranslateTransition();
        t.setByX(x);
        t.setByY(y);
        t.setNode(node);
        t.setDuration(Duration.millis(duration));
//        t.setCycleCount(TranslateTransition.INDEFINITE);
//        t.setAutoReverse(true);
        return t;
    }
    public static FadeTransition fade(Node n, double fadeval, double time) {
        //Fade Duration Must be 1500
        //Setting Fades to 1 for Fast Testing
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(time));
        fadeload.setToValue(fadeval);
        fadeload.setNode(n);
        if(fadeval==0)n.setDisable(true);
        else if(fadeval==1)n.setDisable(false);
        return fadeload;
    }
}
