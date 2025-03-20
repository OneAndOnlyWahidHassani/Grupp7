package levelEditor.controller;

import LevelEditor.controller.MainLE;
import control.MainProgram;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.MazeGeneration.MazeGenerator;
import javafx.scene.Node;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import LevelEditor.view.MapTemplateLE;
import javafx.scene.robot.Robot;

import java.awt.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ObjHanteringTest extends ApplicationTest {


    private MainProgram mainProgram;
    private Random random;
    private MainLE mainLE;
    private MazeGenerator mazeGenerator;
    private MapTemplateLE mapTemplateLE;

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
 * @author Linus Sigurd
 */ public void OBJ_1_1() {

    Path expectedFilePath = Paths.get("createdLevels/TestLevelNewName_Niv.dat");
    try {
        if (Files.exists(expectedFilePath)) {
            Files.delete(expectedFilePath);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    WaitForAsyncUtils.waitForFxEvents();
    moveToAndClickOn("introButton");
    moveToAndClickOn("levelEditorButton");
    moveToAndClickOn("createLevelButton");
    moveToAndClickOn("textFieldLevelEditor");
    write("TestLevelNewName_Niv");
    moveToAndClickOn("selectButtonLevelEditor");

    mapTemplateLE = mainProgram.getMapTemplateLE();
    GridPane gridPane = mapTemplateLE.getGridPane();
    int dimension = mapTemplateLE.getDimension();
    int x = random.nextInt(dimension)+1;
    int y = random.nextInt(dimension)+1;


    Node targetCell = getNodeByRowColumnIndex(x, y, gridPane);
    assertNotNull(targetCell, "Could not find target cell in the grid!");
        System.out.println("before");


    drag("wallLabel1");
    WaitForAsyncUtils.waitForFxEvents();
    dropTo(targetCell);
        System.out.println("after");
    int expectedCol = x-1;
    int expectedRow = y-1;
    System.out.println("Expected row: " + expectedRow + " Expected col: " + expectedCol);
    int[][] level = mapTemplateLE.getLevel(); // If you have a getter for the raw level array
    mazeGenerator = mainProgram.getMazeGenerator();
    System.out.println(mazeGenerator.getRawMaze());
    assertEquals(0, level[expectedCol][expectedRow],
            "After dropping 'wallButton', the level cell should be set to 0 for WALL");

    assertTrue(Files.exists(expectedFilePath));
    }

    /**
     * @Author Linus Sigurd
     */
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

    private void moveToAndClickOn(String buttonId) {
        moveTo("#" + buttonId);
        clickOn();
    }
    
}
