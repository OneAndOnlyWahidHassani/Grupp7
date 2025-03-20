package model;

import java.io.*;

/**
 * The HighscoreList class handles the storage and management of high scores in a game.
 * It allows adding new scores, reading and writing the list of high scores to and from a file.
 * The class supports sorting the high scores and storing up to 10 scores.
 *
 * @author Elvira Grubb
 */
public class HighscoreList {
    int[] highscoreListScores;
    String[] highscoreListNames;
    /**
     * Constructs a new HighscoreList instance and reads the high score data from a file.
     *
     * @throws IOException if an I/O error occurs during reading the file.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */
    public HighscoreList() throws IOException, ClassNotFoundException {
        read();
    }

    /**
     * Adds a new score to the high score list. If the new score is higher than the lowest score,
     * it will be inserted in the correct position in the sorted list.
     *
     * @param score The score to be added.
     * @param name The name associated with the score.
     */
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
    /**
     * Writes the current high score list to a file.
     *
     * @throws IOException if an I/O error occurs during writing to the file.
     */
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
    /**
     * Reads the high score list from a file.
     *
     * @throws IOException if an I/O error occurs during reading the file.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */
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
