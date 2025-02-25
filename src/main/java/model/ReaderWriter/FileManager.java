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

    public Map<String, Object> readLevels(String levelName) throws IOException, ClassNotFoundException {
        Map<String, Object> dataMap = new HashMap<>();

        // For Windows
        // String fileName = "maps\\" + mapName + ".dat";

        // For Mac
        String fileName = "createdLevels/" + levelName + ".dat";

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {

            // Read the first three pieces of data and store them in the map
            String level = (String) ois.readObject();   // Assuming the first item is levelName
            String theme = (String) ois.readObject();   // Assuming the second item is theme
            String dimension = (String) ois.readObject();   // Assuming the third item is dimension

            dataMap.put("levelName", level);
            dataMap.put("theme", theme);
            dataMap.put("dimension", dimension);

            // Read the rest of the data as it is
            List<int[][]> map = (List<int[][]>) ois.readObject();
            dataMap.put("map", map);
        }

        return dataMap;
    }

}
