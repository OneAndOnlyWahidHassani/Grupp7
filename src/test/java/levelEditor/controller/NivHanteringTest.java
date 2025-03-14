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
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import view.Randomize.MapTemplate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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

    //TODO Avvaktar med denna efter NIVH 2.1 och OBJ 1.1
    /**
     *
     */
    public void NIVH_1_1(){
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
    }

    @Test
    /**
     * Testing modified file is saved with changes
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

        /// ////DRAG AND DROP
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


        int[][] level = mapTemplateLE.getLevel(); // If you have a getter for the raw level array
        mazeGenerator = mainProgram.getMazeGenerator();


        assertEquals(0, level[expectedCol][expectedRow],
                "After dropping 'wallButton', the level cell should be set to 0 for WALL");
        moveToAndClickOn("saveLevelButton");


        moveToAndClickOn("saveLevelButton");
        moveToAndClickOn("menuButton");
        WaitForAsyncUtils.waitForFxEvents();
        sleep(1000);

        moveToAndClickOn("levelEditorButton");
        moveToAndClickOn("editLevelButton");
        sleep(500);
        type(KeyCode.DOWN);
        type(KeyCode.UP);
        sleep(1000);
        moveToAndClickOn("LoadLevelButton");
        clickOn();


        int[][] reloadedMaze = mainLE.getMazeGenerator().getRawMazeArray();

        assertArrayEquals(originalMaze, reloadedMaze, "The modified maze should be saved and reloaded correctly.");
        assertNotEquals(originalMaze, reloadedMaze, "The modified maze should be saved and reloaded correctly.");

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
        return null;  // if nothing matched
    }
}
