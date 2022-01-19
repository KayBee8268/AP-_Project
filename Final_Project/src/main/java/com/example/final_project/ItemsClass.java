package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class ItemsClass extends ObjectClass implements Serializable {
    private ImageView itemImage;

    public ImageView getItemImage() {
        return itemImage;
    }
}
