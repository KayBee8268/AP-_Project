package com.example.final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class PlatformClass {
//  private final PositionClass position;
    private ImageView platform;
    private static ArrayList<ImageView> platForm = new ArrayList<>();

    public PlatformClass(double x,double y,double xDimension, double yDimension,int platformType){
//        position=new PositionClass(x,y,xDimension,yDimension);

        platform = new ImageView();
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

        platform.setImage(image);
        platform.setX(x);
        platform.setY(y);

        platform.setFitHeight(yDimension);
        platform.setFitWidth(xDimension);
        platForm.add(platform);
    }

    public static boolean checkPlatform(ImageView obj){
        for(ImageView temp: platForm){
            if((obj.getY()==temp.getY()-obj.getFitHeight()-1) && ((obj.getX()>(temp.getX()- obj.getFitWidth())) && (obj.getX()<(temp.getX()+temp.getFitWidth())))){
                System.out.println("check true\n"+" " +temp.getLayoutBounds());
                return true;
            }
            //System.out.println("check false\n"+" " +player.getY() );
        }
        return false;
    }

    public ImageView getPlatform() {
        return platform;
    }
}
