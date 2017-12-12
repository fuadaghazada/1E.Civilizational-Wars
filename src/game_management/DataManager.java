package game_management;


import game_object.general.GameObject;
import game_object.general.GameObjectHandler;
import game_object.general.ObjectID;
import user_interface.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
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

    private Point[] characterPositions;
    private Point[] enemyPositions;


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
     *  Writesthe game data to the file with given filename as a property
     */
    public void writeToFile()
    {
        // when is need to be written to the file - game data should be saved
        this.saveGameData();

        try
        {
            FileWriter fileWriter = new FileWriter(file);

            // Writing the currentLevel to the file
            fileWriter.write(level + "\n");

            // Writing the objects to the file
            for(String eachData : gameData)
            {
                String data = eachData;
                fileWriter.write(data + "\n");
            }

            fileWriter.close();

            isSuccessfulWrite = true;                   // did write

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *  Reads the game data from the file with the game data.
     */
    public void readFromFile()
    {
        // To handle the repetition in the file
        gameData.clear();

        try {
            Scanner fileReader = new Scanner(file);

            String level_str = fileReader.nextLine();
            LevelManager.getInstance().changeLevel(Integer.parseInt(level_str));

            //TODO: Handle character size according to the game mode
            characterPositions = new Point[1];

            enemyPositions = new Point[LevelManager.getInstance().getCurrentLevel().getEnemySize()];

            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();

                String[] data = line.split("-");

                ObjectID id = this.parseReadData(data);

                if (id.equals(ObjectID.ClassicFighter) || id.equals(ObjectID.ModernFighter) || id.equals(ObjectID.Robot))
                    GameObjectHandler.getInstance().addGameObject(id, characterPositions);
                else
                    GameObjectHandler.getInstance().addGameObject(id, enemyPositions);
            }

            fileReader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


    }

    /**
     *  Parses the data read from the file to create the objects with those properties
     *  @param data - data array in String
     *  @return objectID - ObjectID of the game object - from read data
     */
    private ObjectID parseReadData(String [] data)
    {
        double x = Double.parseDouble(data[1]);
        double y = Double.parseDouble(data[2]);

        if(data[0].equals("ClassicFighter") || data[0].equals("ModernFighter") || data[0].equals("Robot"))
        {
            for(int i = 0; i < characterPositions.length; i++)
            {
                if(characterPositions[i] == null)
                {
                    characterPositions[i] = new Point((int) x, (int) y);
                }
            }
        }

        if(data[0].equals("ClassicSoldier") || data[0].equals("ModernSoldier") || data[0].equals("Alien"))
        {
            for (int i = 0; i < enemyPositions.length; i++)
            {
                if (enemyPositions[i] == null)
                {
                    enemyPositions[i] = new Point((int) x, (int) y);
                }
            }
        }

        switch (data[0])
        {
            case "ClassicFighter":
            {
                return ObjectID.ClassicFighter;
            }
            case "ModernFighter":
            {
                return ObjectID.ModernFighter;
            }
            case "Robot":
            {
                return ObjectID.Robot;
            }
            case "ClassicSoldier":
            {
                return ObjectID.ClassicSoldier;
            }
            case "ModernSoldier":
            {
                return ObjectID.ModernSoldier;
            }
            case "Alien":
            {
                return ObjectID.Alien;
            }
            default:
                return ObjectID.ClassicSoldier;
        }
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
