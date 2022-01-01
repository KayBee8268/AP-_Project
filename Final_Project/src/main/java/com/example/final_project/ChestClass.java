package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ChestClass extends ObjectClass  {
//    protected ImageView chest;
    protected boolean isOpen;

    public ChestClass(double xCoordinate,double yCoordinate,double xDimension, double yDimension){
//        position=new PositionClass(x,y,xDimension,yDimension);

        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/ChestClosed.png");
        super.setObjectImage(image);
        super.getObjectImage().setX(xCoordinate);
        super.getObjectImage().setY(yCoordinate);
        super.getObjectImage().setFitHeight(yDimension);
        super.getObjectImage().setFitWidth(xDimension);
        isOpen=false;
    }

    public void setOpen(boolean data){
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/ChestOpen.png");
        super.getObjectImage().setImage(image);
        this.isOpen=data;
    }

    public boolean getOpen(){
        return this.isOpen;

    }
    public abstract void awardItem(PlayerClass player);

//    public ImageView getImageView() {
//        return chest;
//    }
}
