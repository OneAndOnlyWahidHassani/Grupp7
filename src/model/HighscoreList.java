package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighscoreList {
    int[] highscoreList;

    public HighscoreList() throws IOException, ClassNotFoundException {
        read();
    }
    public void addScore(int score)
    {
        if (highscoreList != null)
        {
            if (score > highscoreList[highscoreList.length])
            {
                //Säkerställer att highscorelistan inte blir längre än 10
                int length = highscoreList.length;
                if (length == 9)
                {
                    length--;
                }

                for (int i = length; i > 0; i--)
                {
                    if (score > highscoreList[i])
                    {
                        highscoreList[i + 1] = highscoreList[i];
                    }

                    else
                    {
                        highscoreList[i + 1] = score;
                    }
                }
            }

            for (int i = 0; i < highscoreList.length; i++)
            {
                System.out.println(highscoreList[i]);
            }
        }
    }

    public void update() throws IOException {
        write(highscoreList);
    }

    public static void write (int[] highscoreList) throws IOException, IOException
    {
        String fileName = "maps\\highscore.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName))))
        {
            oos.writeObject(highscoreList);
            oos.flush();
        }
    }

    public void read() throws IOException, ClassNotFoundException {
        String fileName = "maps\\highscore.dat";
        File f = new File(fileName);
        if(f.exists() && !f.isDirectory())
        {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName))) )
            {
                highscoreList = (int[]) ois.readObject();
            }
        }
    }
}
