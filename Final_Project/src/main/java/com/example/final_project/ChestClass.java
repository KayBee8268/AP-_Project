package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ChestClass {
    protected ImageView chest;
    protected boolean isOpen;

    public ChestClass(double x,double y,double xDimension, double yDimension){
//        position=new PositionClass(x,y,xDimension,yDimension);

        chest = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/ChestClosed.png");
        chest.setImage(image);
        chest.setX(x);
        chest.setY(y);

        chest.setFitHeight(yDimension);
        chest.setFitWidth(xDimension);
        isOpen=false;
    }

    public void setOpen(boolean data){
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/ChestOpen.png");
        chest.setImage(image);
        this.isOpen=data;
    }

    public boolean getOpen(){
        return this.isOpen;

    }
    public abstract void awardItem(PlayerClass player);

    public ImageView getImageView() {
        return chest;
    }
}
