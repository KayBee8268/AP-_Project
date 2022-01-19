package com.example.final_project;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
public class LoadObjects implements Serializable {
    private ArrayList<ObjectClass> gameObjectsRecord;
    private ArrayList<ObjectClass> instantiatedObjects=new ArrayList<>();
    private ObjectClass deserializedObject;
    private PlayerClass deserializedPlayer;
    public ArrayList<ObjectClass> deserializeObjects(String fileName) throws IOException, ClassNotFoundException {
        System.out.println(fileName);
        ObjectInputStream inputStream = null;
        gameObjectsRecord = new ArrayList<>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            if(inputStream!=null){
                System.out.println("not null");
                this.deserializedObject = (ObjectClass)inputStream.readObject();
                System.out.println("Player Name : "+(deserializedObject).getObjectName());
                System.out.println("GetPosition ");
                deserializedPlayer = new PlayerClass(deserializedObject.getPosition().getXCoordinate(),deserializedObject.getPosition().getYCoordinate(),deserializedObject.getPosition().getXDimension(),deserializedObject.getPosition().getYDimension());
                deserializedPlayer.setObjectName("Player");
                deserializedPlayer.setPlayerName(((PlayerClass)deserializedObject).getPlayerName());
                while(true) {
                    try{
                        ObjectClass temp = (ObjectClass) inputStream.readObject();
                        gameObjectsRecord.add(temp);
                    }catch (EOFException e) {
                        break;
                    }catch (ClassCastException e) {
                        System.out.println("Invalid Class Cast Exception");
                    }
                }
            }

        }
        finally {
            inputStream.close();
        }
        return gameObjectsRecord;

    }

    public ArrayList<ObjectClass> getLoadedObjects(String gameFileName) throws IOException, ClassNotFoundException {
        deserializeObjects(gameFileName);
        for(int i=0;i<gameObjectsRecord.size();i++){
            ObjectClass currentGameObject = gameObjectsRecord.get(i);
            if (currentGameObject.getObjectName().equals("Player")){
                System.out.println("code working");
                ObjectClass player = new PlayerClass(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension());

                instantiatedObjects.add(player);
            }
            else if (currentGameObject.getObjectName().equals("Platform")){
                System.out.println("platform working");
                if (currentGameObject instanceof PlatformClass) {
                    ObjectClass platform = new PlatformClass(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension(),((PlatformClass)currentGameObject).getPlatformType());
                    platform.setObjectName("Platform");
                    instantiatedObjects.add(platform);
                }
            }
            else if (currentGameObject.getObjectName().equals("GreenOrc")){
                ObjectClass greenOrc = new GreenOrc(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension());
                instantiatedObjects.add(greenOrc);
            }
            else if (currentGameObject.getObjectName().equals("RedOrc")){
                ObjectClass redOrc = new RedOrc(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension());
                instantiatedObjects.add(redOrc);
            }
            else if (currentGameObject.getObjectName().equals("BossOrc")){
                ObjectClass bossOrc = new BossOrc(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension());
                instantiatedObjects.add(bossOrc);
            }
            else if (currentGameObject.getObjectName().equals("Coin")){
                ObjectClass coin = new CoinClass(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension());
                instantiatedObjects.add(coin);
            }
            else if (currentGameObject.getObjectName().equals("FallingPlatform")){
                ObjectClass fallingPlatform = new ObstacleClass(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension(),1);
                instantiatedObjects.add(fallingPlatform);
            }
            else if (currentGameObject.getObjectName().equals("CoinChest")){
                ObjectClass coinChest= new CoinChestClass(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension(),((CoinChestClass)currentGameObject).getCoins());
                instantiatedObjects.add(coinChest);
            }
            else if (currentGameObject.getObjectName().equals("WeaponChest")){
                ObjectClass coinChest= new CoinChestClass(currentGameObject.getPosition().getXCoordinate(),currentGameObject.getPosition().getYCoordinate(),currentGameObject.getPosition().getXDimension(),currentGameObject.getPosition().getYDimension(),((CoinChestClass)currentGameObject).getCoins());
                instantiatedObjects.add(coinChest);
            }

        }
        return instantiatedObjects;
    }

}
