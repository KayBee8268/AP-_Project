package com.example.final_project;

import java.io.*;
import java.util.ArrayList;

public class DataClass {
    private File [] savedFiles;
    private ArrayList<String> playerList;
    private ArrayList<String> gameList;
    public DataClass(){
        playerList = new ArrayList<>();
        gameList = new ArrayList<>();
    }
    public ArrayList<String> getSavedPlayerList() {
        this.savedFiles = new File("C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\Saved_Players").listFiles();
        assert savedFiles != null;
        for(File file : savedFiles) {
            playerList.add(file.getName());
        }
        return playerList;
    }
    public ArrayList<String> getSavedGameList() {
        this.savedFiles = new File("C:\\Users\\asus\\IdeaProjects\\Final_Project\\src\\Saved_Games").listFiles();
        assert savedFiles != null;
        // System.out.println(savedFiles.length);
        for(File file : savedFiles) {
            gameList.add(file.getName());
        }
        return gameList;
    }



}
