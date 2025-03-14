package levelEditor.controller;

import LevelEditor.controller.MainLE;
import LevelEditor.view.MapTemplateLE;
import control.MainProgram;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.MazeGeneration.MazeGenerator;
import model.ReaderWriter.FileManager;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import view.Randomize.MapTemplate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NivHanteringTest extends ApplicationTest {


    private MainProgram mainProgram;
    private Random random;
    private MainLE mainLE;
    private MapTemplateLE mapTemplateLE;
    private MazeGenerator mazeGenerator;



    @Override
    public void start(Stage stage) {
        random = new Random();
        mainProgram = new MainProgram();
        try {
            mainProgram.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
        mainLE = mainProgram.getSetUp().getMainLE();
    }


    @Test
/**
 * Testing opening an existing level in Level Editor.
 * @author AlanahColeman
 */ public void NIVH_1_0() {
        WaitForAsyncUtils.waitForFxEvents();

        moveToAndClickOn("introButton");
        moveToAndClickOn("levelEditorButton");
        moveToAndClickOn("editLevelButton");
        sleep(500);

        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.UP);
        type(KeyCode.UP);

        sleep(1000);

        moveToAndClickOn("LoadLevelButton");
        clickOn();


        ListView<String> levelList = new ListView<>();
        File createdLevelsFolder = new File("createdLevels");
        if (createdLevelsFolder.exists() && createdLevelsFolder.isDirectory()) {
            String[] levelFiles = createdLevelsFolder.list((dir, name) -> name.endsWith(".dat"));

            String levelFile = levelFiles[0];
            System.out.println(levelFile);
            Path expectedFilePath = Paths.get("createdLevels/" + levelFile);

            assertTrue(Files.exists(expectedFilePath), "Level file did not load correctly.");
        }

    }



    @Test
    /**
     * Testing modify existing file
     * Ensuring modified file is saved with changes
     * @author AlanahColeman
     */
    public void NIVH_2_0(){
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("levelEditorButton");
        moveToAndClickOn("editLevelButton");
        sleep(500);
        type(KeyCode.DOWN);
        type(KeyCode.UP);
        sleep(1000);
        moveToAndClickOn("LoadLevelButton");
        clickOn();
        int[][] originalMaze = mainLE.getMazeGenerator().getRawMazeArray();

        //// DRAG AND DROP  ////
        mapTemplateLE = mainProgram.getMapTemplateLE();
        GridPane gridPane = mapTemplateLE.getGridPane();
        int dimension = mapTemplateLE.getDimension();
        int x = random.nextInt(dimension)+1;
        int y = random.nextInt(dimension)+1;

        Node targetCell = getNodeByRowColumnIndex(x, y, gridPane);
        assertNotNull(targetCell, "Could not find target cell in the grid!");

        drag("wallLabel1");
        WaitForAsyncUtils.waitForFxEvents();
        dropTo(targetCell);
        System.out.println("after");
        int expectedCol = x-1;
        int expectedRow = y-1;


        int[][] level = mapTemplateLE.getLevel();
        mazeGenerator = mainProgram.getMazeGenerator();


        assertEquals(0, level[expectedCol][expectedRow],
                "After dropping 'wallButton', the level cell should be set to 0 for WALL");
        moveToAndClickOn("saveLevelButton");


        int[][] modifiedMaze = mapTemplateLE.getLevel();
        assertEquals(0, modifiedMaze[expectedCol][expectedRow],
                "After dropping 'wallButton', the level cell should be set to 0 for WALL");

        // Save the modified level
        moveToAndClickOn("saveLevelButton");

        File createdLevelsFolder = new File("createdLevels");
        if (createdLevelsFolder.exists() && createdLevelsFolder.isDirectory()) {
            String[] levelFiles = createdLevelsFolder.list((dir, name) -> name.endsWith(".dat"));

            assertNotNull(levelFiles, "No level files found!");
            assertTrue(levelFiles.length > 0, "No saved levels found in directory");

            String levelFile = levelFiles[0];
            Path expectedFilePath = Paths.get("createdLevels/" + levelFile);

            assertTrue(Files.exists(expectedFilePath), "Level file did not load correctly.");

            FileManager fileManager = new FileManager();
            try {
                Map<String, Object> loadedData = fileManager.readLevelData(levelFile.replace(".dat", ""));
                int[][] reloadedMaze = (int[][]) loadedData.get("maze");

                assertNotNull(reloadedMaze, "Reloaded maze is null!");
                assertNotEquals(originalMaze, reloadedMaze, "The modified maze should be different from the original.");
            } catch (Exception e) {
                fail("Exception occurred while reading level file: " + e.getMessage());
            }
        }
    }

    private void moveToAndClickOn(String buttonId) {
        moveTo("#" + buttonId);
        clickOn();
    }

    private Node getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            // By default, getRowIndex() or getColumnIndex() can be null, so handle that
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            if (r == null) r = 0;
            if (c == null) c = 0;

            if (r == row && c == col) {
                return node;
            }
        }
        return null;
    }
}
