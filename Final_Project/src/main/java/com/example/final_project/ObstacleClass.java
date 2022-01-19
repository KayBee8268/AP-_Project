package com.example.final_project;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class ObstacleClass extends ItemsClass implements Serializable {
//    private ImageView fallingPlatform;
    private static ArrayList<ImageView> falling = new ArrayList<>();
    private static boolean isFallen=false;
    private static int buffer=0;

    public ObstacleClass (double x,double y,double xDimension, double yDimension,int n){
        //     position=new PositionClass(x,y,xdimension,ydimension);
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/fallingPlatform.png");
        super.setObjectImage(image);
        super.getObjectImage().setX(x);
        super.getObjectImage().setY(y);
        super.getObjectImage().setFitHeight(yDimension);
        super.getObjectImage().setFitWidth(xDimension);

        if(n==0){
            falling.clear();
        }

        falling.add(super.getObjectImage());

    }

    public static boolean checkFallingPlatform(ImageView obj,int g){
        for(ImageView temp: falling){
            if((obj.getY()>=temp.getY()-obj.getFitHeight() && obj.getY()<=temp.getY()-obj.getFitHeight()+g) && ((obj.getX()>(temp.getX()- obj.getFitWidth())) && (obj.getX()<(temp.getX()+temp.getFitWidth())))){
                isFallen=true;
                return true;
            }
        }
        return false;
    }
    public static void listClear(){
        falling.clear();
    }
    public static void addObstacles(ImageView image){
        falling.add(image);
    }

    public static void fall(){
        if(isFallen){
            buffer++;
            for(ImageView temp: falling){
                if(temp.getY()<720 && buffer>=1){
                    temp.setY(temp.getY() +8);
                    buffer--;
                }
            }
        }
    }

//    public ImageView getFallingPlatform() {
//        return fallingPlatform;
//    }
}