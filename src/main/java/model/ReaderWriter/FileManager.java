package model.ReaderWriter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {


    public static void write(List<int[][]> worldMap, String worldName) throws IOException, IOException {
        String fileName = "maps\\" + worldName + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            oos.writeObject(worldMap);
            oos.flush();
        }
    }

    public List<int[][]> read(String mapName) throws IOException, ClassNotFoundException {
        List<int[][]> map;
        //For Windows
        //String fileName = "maps\\" + mapName + ".dat";

        //For Mac
        String fileName = "maps/" + mapName + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            map = (List<int[][]>) ois.readObject();
        }
        return map;
    }

    public static void writeLevelData(String levelName, String theme, String dimension, int[][] maze) {
        String fileName = "createdLevels/" + levelName + ".dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            oos.writeObject(levelName);
            oos.writeObject(theme);
            oos.writeObject(dimension);
            oos.writeObject(maze); // Spara labyrinten
            System.out.println("Level saved successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Todo: USe this to be able to load the level data

    public static Map<String, Object> readLevelData(String levelName) {
        String fileName = "createdLevels/" + levelName + ".dat";
        Map<String, Object> dataMap = new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            dataMap.put("levelName", (String) ois.readObject());
            dataMap.put("theme", (String) ois.readObject());
            dataMap.put("dimension", (String) ois.readObject());
            dataMap.put("maze", (int[][]) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dataMap;
    }


}
