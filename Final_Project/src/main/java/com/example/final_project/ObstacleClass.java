package com.example.final_project;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class ObstacleClass{
    private ImageView fallingPlatform;
    private static ArrayList<ImageView> falling1 = new ArrayList<>();
    private static ArrayList<ImageView> falling2 = new ArrayList<>();
    private int type;
    private static boolean isFallen1=false;
    private static boolean isFallen2=false;
    private static int buffer1=0;
    private static int buffer2=0;

    public ObstacleClass(double x,double y,double xDimension, double yDimension,int type){
        //     position=new PositionClass(x,y,xdimension,ydimension);
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/fallingPlatform.png");
        fallingPlatform= new ImageView();
        fallingPlatform.setImage(image);
        fallingPlatform.setX(x);
        fallingPlatform.setY(y);
        fallingPlatform.setFitHeight(yDimension);
        fallingPlatform.setFitWidth(xDimension);

        if(type==1){
            falling1.add(fallingPlatform);
            this.type=type;
        }
        else if(type==2){
            falling2.add(fallingPlatform);
            this.type=type;
        }
    }

    public static boolean checkFallingPlatform(ImageView obj){
        for(ImageView temp: falling1){
            if((obj.getY()==temp.getY()-obj.getFitHeight()-1) && ((obj.getX()>(temp.getX()- obj.getFitWidth())) && (obj.getX()<(temp.getX()+temp.getFitWidth())))){
                isFallen1=true;
                return true;
            }
        }
        for(ImageView temp: falling2){
            if((obj.getY()==temp.getY()-obj.getFitHeight()-1) && ((obj.getX()>(temp.getX()- obj.getFitWidth())) && (obj.getX()<(temp.getX()+temp.getFitWidth())))){
                isFallen2=true;
                return true;
            }
        }
        return false;
    }

    public static void fall(){
        if(isFallen1){
            buffer1++;
            for(ImageView temp: falling1){
                if(temp.getY()<720 && buffer1>=1){
                    temp.setY(temp.getY() +8);
                    buffer1--;
                }
            }
        }
        if(isFallen2){
            buffer2++;
            for(ImageView temp: falling2){
                if(temp.getY()<720 && buffer2>=1){
                    temp.setY(temp.getY() +8);
                    buffer2--;
                }
            }
        }
    }

    public ImageView getFallingPlatform() {
        return fallingPlatform;
    }
}