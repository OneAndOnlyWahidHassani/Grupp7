package model.MazeGeneration;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeGeneratorTest {

    @Test
    @Story("Get a raw maze")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get a raw maze")
    @Issue("TestCase-?33?")
    @TmsLink("Test-?33?")
    void getRawMaze() {
        MazeGenerator mazeGenerator = new MazeGenerator(10, false);
        String rawMaze = mazeGenerator.getRawMaze();
        assertNotNull(rawMaze);
    }
}