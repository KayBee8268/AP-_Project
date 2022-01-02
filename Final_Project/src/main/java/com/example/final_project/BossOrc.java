package com.example.final_project;
import java.io.Serializable;

public class BossOrc extends OrcClass implements Serializable {
    public BossOrc(double xCoordinate ,double yCoordinate,double xDimension,double yDimension){
        super(xCoordinate ,yCoordinate,xDimension,yDimension,"file:/C:/Users/asus/IdeaProjects/Final_Project/src/assets/assets/OrcBoss.png");
        this.setHP(500);
    }


}

