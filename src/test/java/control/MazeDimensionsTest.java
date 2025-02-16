package control;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.MazeGeneration.MazeGenerator;

public class MazeDimensionsTest {

    private MazeGenerator mazeGenerator;

    @Test
    public void testMazeDimensions() {
        // Test 10x10 dimension
        mazeGenerator = new MazeGenerator(10, false);
        int[][] maze10 = mazeGenerator.getMaze();
        assertEquals(10, maze10.length, "10x10 maze should have 10 rows");
        assertEquals(10, maze10[0].length, "10x10 maze should have 10 columns");

        // Test 14x14 dimension
        mazeGenerator = new MazeGenerator(14, false);
        int[][] maze14 = mazeGenerator.getMaze();
        assertEquals(14, maze14.length, "14x14 maze should have 14 rows");
        assertEquals(14, maze14[0].length, "14x14 maze should have 14 columns");

        // Test 18x18 dimension
        mazeGenerator = new MazeGenerator(18, false);
        int[][] maze18 = mazeGenerator.getMaze();
        assertEquals(18, maze18.length, "18x18 maze should have 18 rows");
        assertEquals(18, maze18[0].length, "18x18 maze should have 18 columns");

        // Test 28x28 dimension (Pain mode)
        mazeGenerator = new MazeGenerator(28, false);
        int[][] maze28 = mazeGenerator.getMaze();
        assertEquals(28, maze28.length, "28x28 maze should have 28 rows");
        assertEquals(28, maze28[0].length, "28x28 maze should have 28 columns");
    }
}
