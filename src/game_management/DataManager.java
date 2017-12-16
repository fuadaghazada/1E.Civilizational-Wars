package game_management;


import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import user_interface.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  Data manager - manages and handles the data when the save&load commands executed
 *
 */
public class DataManager
{
    //Singleton
    private static DataManager dataManager = new DataManager();

    // Properties
    private File file;
    private String filename;

    private boolean isSuccessfulWrite = false;
    private boolean isLoadCalled = false;

    private ArrayList<String> gameData;
    private int level;

    private ArrayList<Point> characterPositions;
    private ArrayList<Point> enemyPositions;
    private ArrayList<Point> boxPositions;
    private ArrayList<Point> bossPosiitons;


    /**
     *  Constructs the data manager.
     */
    private DataManager()
    {
        gameData = new ArrayList<>();
        level = 1;

        this.filename = "GameData";

        file = new File(filename);
    }

    /**
     *  Singleton access
     */
    public static DataManager getInstance()
    {
        return dataManager;
    }

    /**
     *  Saves the data of the game elements and properties to the data list
     */
    private void saveGameData()
    {
        ArrayList<GameObject> object_list = GameObjectHandler.getInstance().getGame_objects();

        // Saving the level of the game
        level = LevelManager.getInstance().getCurrentLevelNo();

        // Saving the properties of the game objects
        for(int i = 0; i < object_list.size(); i++)
        {
            if(object_list.get(i).getId() != ObjectID.Tile)
            {
                String data = object_list.get(i).toString();

                gameData.add(data);
            }
        }
    }

    /**
     *  Writes the game data to the file with given filename as a property
     */
    public void saveGame()
    {
        // when is need to be written to the file - game data should be saved
        this.saveGameData();

        try
        {
            FileWriter fileWriter = new FileWriter(file, false);

            // Writing the currentLevel to the file
            fileWriter.write(level + "\n");

            // Writing the objects to the file
            for(String eachData : gameData)
            {
                fileWriter.write(eachData + "\n");
            }

            fileWriter.close();
            isSuccessfulWrite = true;                   // did write

        }
        catch(IOException e)
        {
            isSuccessfulWrite = false;
            e.printStackTrace();
        }
    }

    /**
     *  Reads the game data from the file with the game data.
     */
    public GameData loadGame()
    {
        GameData gData = new GameData();
        // To handle the repetition in the file
        gameData.clear();

        try {
            Scanner fileReader = new Scanner(file);

            String level_str = fileReader.nextLine();

            gData.setLevel(Integer.parseInt(level_str));

            //TODO: Handle character size according to the game mode
            characterPositions = new ArrayList<>();

            enemyPositions = new ArrayList<>();
            boxPositions = new ArrayList<>();
            bossPosiitons = new ArrayList<>();

            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();

                String[] data = line.split("-");

                parseReadData(data);
/*
                if (id.equals(ObjectID.ClassicFighter) || id.equals(ObjectID.ModernFighter) || id.equals(ObjectID.Robot))
                    GameObjectHandler.getInstance().addGameObject(id, characterPositions);
                else if(id.equals(ObjectID.ClassicSoldier) || id.equals(ObjectID.ModernSoldier) || id.equals(ObjectID.Alien))
                    GameObjectHandler.getInstance().addGameObject(id, enemyPositions);
                else if(id.equals(ObjectID.SurpriseBox))
                    GameObjectHandler.getInstance().addGameObject(id, boxPositions);
                    */
            }
            gData.setCharacterPositions(characterPositions.toArray(new Point[characterPositions.size()]));
            gData.setEnemyPositions( enemyPositions.toArray(new Point[enemyPositions.size()]));
            gData.setBoxPositions( boxPositions.toArray(new Point[boxPositions.size()]));
            gData.setBossPositions( bossPosiitons.toArray(new Point[bossPosiitons.size()]));

            gData.setMultiPlayer(characterPositions.size() == 2);


            fileReader.close();
            return gData;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  Parses the data read from the file to create the objects with those properties
     *  @param data - data array in String
     *  @return objectID - ObjectID of the game object - from read data
     */
    private void parseReadData(String [] data)
    {
        double x = Double.parseDouble(data[1]);
        double y = Double.parseDouble(data[2]);

        if(data[0].equals("ClassicFighter") || data[0].equals("ModernFighter") || data[0].equals("Robot"))
        {
            characterPositions.add(new Point((int) x, (int) y));
        }

        if(data[0].equals("ClassicSoldier") || data[0].equals("ModernSoldier") || data[0].equals("Alien"))
        {
            enemyPositions.add(new Point((int) x, (int) y));
        }

        if(data[0].equals("SurpriseBox"))
        {
            boxPositions.add(new Point((int) x, (int) y));
        }

        System.out.println("Data: " + Arrays.toString(data));

      //  switch (data[0])
      //  {
      //      case "ClassicFighter":
      //      {
      //          return ObjectID.ClassicFighter;
      //      }
      //      case "ModernFighter":
      //      {
      //          return ObjectID.ModernFighter;
      //      }
      //      case "Robot":
      //      {
      //          return ObjectID.Robot;
      //      }
      //      case "ClassicSoldier":
      //      {
      //          return ObjectID.ClassicSoldier;
      //      }
      //      case "ModernSoldier":
      //      {
      //          return ObjectID.ModernSoldier;
      //      }
      //      case "Alien":
      //      {
      //          return ObjectID.Alien;
      //      }
      //      case "SurpriseBox":
      //      {
      //          return ObjectID.SurpriseBox;
      //      }
      //      default:
      //          return ObjectID.ClassicSoldier;
      //  }
    }

    // ACCESS

    public boolean isSuccessfulRead()
    {
        return file.length() >= 2;
    }

    public boolean isSuccessfulWrite()
    {
        return isSuccessfulWrite;
    }

    public void setLoadCalled(boolean isLoadCalled)
    {
        this.isLoadCalled = isLoadCalled;
    }

    public boolean isLoadCalled()
    {
        return isLoadCalled;
    }
}
