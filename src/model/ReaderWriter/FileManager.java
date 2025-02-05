package model.ReaderWriter;

import java.io.*;
import java.util.List;

public class FileManager {
    public static void write (List<int[][]> worldMap, String worldName) throws IOException, IOException
    {
        String fileName = "maps\\" + worldName + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName))))
        {
            oos.writeObject(worldMap);
            oos.flush();
        }
    }

    public List<int[][]> read(String mapName) throws IOException, ClassNotFoundException {
        List<int[][]> map;
        String fileName = "maps\\" + mapName + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName))) )
        {
            map = (List<int[][]>) ois.readObject();
        }
        return map;
    }
}
