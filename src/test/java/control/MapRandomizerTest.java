package control;

import model.MazeGeneration.MazeGenerator;
import org.junit.jupiter.api.Test;

public class MapRandomizerTest {

    @Test
    public void testMazeAutoGeneration() {
        int dimension = 8;
        int sampleSize = 5;
        int[][][] mazes = new int[sampleSize][][];
        // Generate multiple mazes
        for (int i = 0; i < sampleSize; i++) {
            MazeGenerator generator = new MazeGenerator(dimension, true);
            mazes[i] = generator.getMaze();
        }

    }   }