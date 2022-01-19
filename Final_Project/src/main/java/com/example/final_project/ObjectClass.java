package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;

public class ObjectClass implements Serializable {
    private PositionClass position = new PositionClass();


    public PositionClass getPosition() {
        return position;
    }

    private String ObjectName;
    private transient ImageView objectImage;
    public void display(AnchorPane gamePane) {
        gamePane.getChildren().add(objectImage);
    }
    public ImageView getObjectImage() {
        return objectImage;
    }
    public void setObjectImage(Image givenImage) {
        objectImage = new ImageView();
        objectImage.setImage(givenImage);
    }
    public String getObjectName() {
        return ObjectName;
    }
    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }
    public void makeImage(String FilePath){
        Image image = new Image(FilePath);
        setObjectImage(image);
        getObjectImage().setX(getPosition().getXCoordinate());
        getObjectImage().setY(getPosition().getYCoordinate());
        getObjectImage().setFitHeight(getPosition().getYDimension());
        getObjectImage().setFitWidth(getPosition().getXDimension());
    }

}
