package model;

import java.io.*;

public class HighscoreList {
    int[] highscoreListScores;
    String[] highscoreListNames;

    public HighscoreList() throws IOException, ClassNotFoundException {
        read();
    }
    public void addScore(int score, String name)
    {
        if (highscoreListScores != null)
        {
            if (score >= highscoreListScores[highscoreListScores.length - 1])
            {
                for (int i = 0; i < highscoreListScores.length; i++)
                {
                    System.out.println("Jämför " + score + " och " + highscoreListScores[i]);
                    if (score >= highscoreListScores[i])
                    {
                        System.out.println(score + " är högre än " + highscoreListScores[i]);
                       /* for (int j = i + 1; j < highscoreListScores.length - 1; j++)
                        {
                            System.out.println("Sätter " + highscoreListScores[j] + " på plats " + (j));
                            highscoreListScores[j] = highscoreListScores[j - 1];
                            highscoreListNames[j] = highscoreListNames[j - 1];
                        }*/

                        for (int j = highscoreListScores.length - 1; j > i; j--)
                        {
                            System.out.println("Sätter " + highscoreListScores[j - 1] + " på plats " + (j));
                            highscoreListScores[j] = highscoreListScores[j - 1];
                            highscoreListNames[j] = highscoreListNames[j - 1];
                        }

                        System.out.println("Sätter " + score + " på plats " + i);
                        highscoreListScores[i] = score;
                        highscoreListNames[i] = name;
                        break;
                    }
                }
            }
        }

        else
        {
            highscoreListNames = new String[10];
            highscoreListScores = new int[10];
            highscoreListNames[0] = name;
            highscoreListScores[0] = score;
        }
    }

    public void write() throws IOException
    {
        System.out.println("Writing list");
        if (highscoreListNames != null && highscoreListScores != null)
        {
            String fileName = "maps\\highscore.dat";
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName))))
            {
                oos.writeObject(highscoreListNames);
                oos.writeObject(highscoreListScores);
                oos.flush();
            }
        }
    }

    public void read() throws IOException, ClassNotFoundException {
        System.out.println("Reading highscore list");
        String fileName = "maps\\highscore.dat";
        File f = new File(fileName);
        if(f.exists() && !f.isDirectory())
        {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName))) )
            {
                highscoreListNames = (String[]) ois.readObject();
                highscoreListScores = (int[]) ois.readObject();
            }

            for (int i = 0; i < highscoreListScores.length; i++)
            {
                System.out.println(highscoreListNames[i] + ", score: " + highscoreListScores[i]);
            }
        }
    }

    public int[] getHighscoreScores()
    {
        if (highscoreListScores != null)
        {
            return highscoreListScores;
        }
        return highscoreListScores = new int[10];
    }

    public String[] getHighscoreNames()
    {
        if (highscoreListNames != null)
        {
            return highscoreListNames;
        }
        return highscoreListNames = new String[10];
    }
}
