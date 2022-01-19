package com.example.final_project;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class LoadPlayer implements Serializable {
    private ObjectClass deserializedPlayer;
    ObjectClass getPlayer(String givenFileName) throws IOException, ClassNotFoundException {
        System.out.println("Player file path : "+givenFileName);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(givenFileName));
            try {
                deserializedPlayer = (PlayerClass) inputStream.readObject();
                //System.out.println(deserializedPlayer.getObjectImage().getX());
                System.out.println(((PlayerClass)deserializedPlayer).getPlayerName());
                System.out.println("object name "+((PlayerClass)deserializedPlayer).getObjectName());
                System.out.println("Player Coordinates "+deserializedPlayer.getPosition().getXCoordinate());
                System.out.println("Player y coordinate"+deserializedPlayer.getPosition().getYCoordinate());
            }catch (ClassCastException e) {
                System.out.println("Invalid Cast Exception");
            }
        }finally {
            inputStream.close();
        }
        return deserializedPlayer;
    }
}
