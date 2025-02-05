package model.ReaderWriter;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static void write (int[][] map, String mapName) throws IOException, IOException
    {
        String fileName = "maps\\" + mapName + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName))))
        {
            oos.writeObject(map);
            oos.flush();
        }
    }

    public int[][] read(String mapName) throws IOException, ClassNotFoundException {
        int[][] map;
        String fileName = "maps\\" + mapName + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName))) )
        {
            map = (int[][]) ois.readObject();
        }

        return map;
    }
}
