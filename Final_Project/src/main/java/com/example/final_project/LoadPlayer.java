package com.example.final_project;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadPlayer {
    private PlayerClass deserializedPlayer;
    PlayerClass getPlayer(String givenFileName) throws IOException, ClassNotFoundException {
        System.out.println("Player file path : "+givenFileName);

        ObjectInputStream inputStream = null;
        try {

            inputStream = new ObjectInputStream(new FileInputStream(givenFileName));
            try {
                deserializedPlayer = (PlayerClass) inputStream.readObject();
                //System.out.println(deserializedPlayer.getObjectImage().getX());
                System.out.println(deserializedPlayer.getPlayerName());
            }catch (ClassCastException e) {
                System.out.println("Invalid Cast Exception");
            }
        }finally {
            inputStream.close();
        }
        return deserializedPlayer;
    }
}
