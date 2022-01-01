package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ObjectClass {
    private PositionClass position;

    private String ObjectName;
    private ImageView objectImage;

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

}
