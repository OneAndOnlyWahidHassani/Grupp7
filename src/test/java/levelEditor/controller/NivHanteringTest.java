package levelEditor.controller;

import LevelEditor.controller.MainLE;
import control.MainProgram;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NivHanteringTest extends ApplicationTest {


    private MainProgram mainProgram;
    private Random random;
    private MainLE mainLE;


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
        //TODO Drag and drop för att redigera i nuvarande datfil

        moveToAndClickOn("saveLevelButton");

        int[][] reloadedMaze = mainLE.getMazeGenerator().getRawMazeArray();
        assertArrayEquals(originalMaze, reloadedMaze, "The modified maze should be saved and reloaded correctly.");
        assertNotEquals(originalMaze, reloadedMaze, "The modified maze should be saved and reloaded correctly.");
    }

    private void moveToAndClickOn(String buttonId) {
        moveTo("#" + buttonId);
        clickOn();
    }
}
