package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class PlatformClass extends ItemsClass implements Serializable {
//  private final PositionClass position;
//    private ImageView platform;
private int platformType=1;
    private static ArrayList<ImageView> platForm = new ArrayList<>();

    public int getPlatformType() {
        return platformType;
    }

    public PlatformClass(double x,double y,double xDimension, double yDimension,int platformType){
//        position=new PositionClass(x,y,xDimension,yDimension);

//        platform = new ImageView();
        Image image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands1.png");
        if (platformType==2){
            image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands2.png");
        }
        else if(platformType==3){
            image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands3.png");
        }
        else if(platformType==4){
            image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands4.png");

        }
        else if(platformType==5){
            image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands5.png");
        }
        else if(platformType==6){
            image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands6.png");
        }
        else if(platformType==7){
            image = new Image("file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/Islands7.png");
        }

        super.setObjectImage(image);

        super.getObjectImage().setX(x);
        super.getObjectImage().setY(y);

        super.getObjectImage().setFitHeight(yDimension);
        super.getObjectImage().setFitWidth(xDimension);
        platForm.add(super.getObjectImage());
    }

    public static boolean checkPlatform(ImageView obj,int g){
        for(ImageView temp: platForm){
            if((obj.getY()>=temp.getY()-obj.getFitHeight() && obj.getY()<=temp.getY()-obj.getFitHeight()+g) && ((obj.getX()>(temp.getX()- obj.getFitWidth())) && (obj.getX()<(temp.getX()+temp.getFitWidth())))){
                //System.out.println("check true\n"+" " +temp.getLayoutBounds());
                return true;
            }
            //System.out.println("check false\n"+" " +player.getY() );
        }
        return false;
    }

//    public ImageView getPlatform() {
//        return platform;
//    }
}
