package com.example.final_project;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;
public class LoadObjects {
    private ArrayList<ObjectClass> gameObjectsRecord;
    private ArrayList<ObjectClass> instantiatedObjects=new ArrayList<>();
    private ObjectClass deserializedObject;
    private PlayerClass deserializedPlayer;
    private void deserializeObjects(String fileName) throws IOException, ClassNotFoundException {
        System.out.println(fileName);
        ObjectInputStream inputStream = null;
        gameObjectsRecord = new ArrayList<>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
            if(inputStream!=null){
                System.out.println("not null");
                this.deserializedObject = (ObjectClass)inputStream.readObject();
                System.out.println("Player Name : "+((PlayerClass)deserializedObject).getObjectName());
                deserializedPlayer = new PlayerClass(deserializedObject.getObjectImage().getX(),deserializedObject.getObjectImage().getY(),deserializedObject.getObjectImage().getFitWidth(),deserializedObject.getObjectImage().getFitHeight());
                deserializedPlayer.setPlayerName(((PlayerClass)deserializedObject).getPlayerName());
                while(true) {
                    try{
                        ObjectClass tmp = (ObjectClass) inputStream.readObject();
                        gameObjectsRecord.add(tmp);
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

    }
    public ArrayList<ObjectClass> getLoadedObjects(String gameFileName) throws IOException, ClassNotFoundException {
        deserializeObjects(gameFileName);
        for(int i=0;i<gameObjectsRecord.size();i++){
            ObjectClass currentGameObject = gameObjectsRecord.get(i);
            if (currentGameObject.getObjectName().equals("Player")){
                ObjectClass player = new PlayerClass(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight());
                instantiatedObjects.add(player);
            }
            else if (currentGameObject.getObjectName().equals("Platform")){
                if (currentGameObject instanceof PlatformClass) {
                    ObjectClass platform = new PlatformClass(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight(),((PlatformClass)currentGameObject).getPlatformType());
                    instantiatedObjects.add(platform);
                }
            }
            else if (currentGameObject.getObjectName().equals("GreenOrc")){
                ObjectClass greenOrc = new GreenOrc(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight());
                instantiatedObjects.add(greenOrc);
            }
            else if (currentGameObject.getObjectName().equals("RedOrc")){
                ObjectClass redOrc = new RedOrc(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight());
                instantiatedObjects.add(redOrc);
            }
            else if (currentGameObject.getObjectName().equals("BossOrc")){
                ObjectClass bossOrc = new BossOrc(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight());
                instantiatedObjects.add(bossOrc);
            }
            else if (currentGameObject.getObjectName().equals("Coin")){
                ObjectClass coin = new CoinClass(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight());
                instantiatedObjects.add(coin);
            }
            else if (currentGameObject.getObjectName().equals("FallingPlatform")){
                ObjectClass fallingPlatform = new ObstacleClass(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight(),1);
                instantiatedObjects.add(fallingPlatform);
            }
            else if (currentGameObject.getObjectName().equals("CoinChest")){
                ObjectClass coinChest= new CoinChestClass(currentGameObject.getObjectImage().getX(),currentGameObject.getObjectImage().getY(),currentGameObject.getObjectImage().getFitWidth(),currentGameObject.getObjectImage().getFitHeight(),((CoinChestClass)currentGameObject).getCoins());
                instantiatedObjects.add(coinChest);
            }

        }
        return instantiatedObjects;
    }

}
