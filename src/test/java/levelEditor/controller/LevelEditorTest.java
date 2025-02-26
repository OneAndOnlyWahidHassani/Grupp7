package levelEditor.controller;

import control.MainProgram;
import javafx.stage.Stage;
import model.Maps.World1Maps;
import model.Maps.World2Maps;
import org.junit.jupiter.api.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LevelEditorTest extends ApplicationTest {


    private MainProgram mainProgram;
    private Random random;

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
    }


    @Test
    public void testCreatingLevelThatExistWithEditor(){
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
        write("TestLevel_Niv");
        moveToAndClickOn("selectButtonLevelEditor");
        clickOn("OK");
        moveToAndClickOn("textFieldLevelEditor");
        eraseText(13);
        write("TestLevelNewName_Niv");
        moveToAndClickOn("selectButtonLevelEditor");
        assertTrue(Files.exists(expectedFilePath));
    }
    @Test
    public void testCreatingLevelWithoutName(){
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("levelEditorButton");
        moveToAndClickOn("createLevelButton");
        moveToAndClickOn("selectButtonLevelEditor");
        sleep(5000);
        /*För att kunna testa mainProgram.getSetUp().getMainLE().getLevel() för att
        kontrollera att en lvl är null så länge inte någon fyllt i ett namn
         */
    }

    @Test
    @Order(1)
    public void testCreatingLevelWithEditor(){
        Path expectedFilePath = Paths.get("createdLevels/TestLevel_Niv.dat");
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
        write("TestLevel_Niv");
        moveToAndClickOn("selectButtonLevelEditor");
        assertTrue(Files.exists(expectedFilePath));

    }

    @Test
    public void testCreatingLevelRandomDimensionEditor(){
        Path expectedFilePath = Paths.get("createdLevels/TestLevel_Niv.dat");
        int dimensionCLicks = random.nextInt(3) + 1;
        int worldsCLicks = random.nextInt(6) + 1;
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
        moveTo("#LeftButtonDimension").moveBy(0, -80);
        clickOn();
        moveTo("#RightButtonDimension").moveBy(0, -80);
        for(int i = 0; i < dimensionCLicks; i++){
            clickOn();
            sleep(500);
        }
        moveTo("#LeftButtonThem").moveBy(0, -50);
        clickOn();
        moveTo("#RightButtonThem").moveBy(0, -50);
        for(int i = 0; i < worldsCLicks; i++){
            clickOn();
            sleep(500);
        }
        moveToAndClickOn("textFieldLevelEditor");
        write("TestLevel_Niv");
        moveToAndClickOn("selectButtonLevelEditor");
        assertTrue(Files.exists(expectedFilePath));

    }



    private void moveToAndClickOn(String buttonId){
        moveTo("#" + buttonId);
        clickOn();
    }
}