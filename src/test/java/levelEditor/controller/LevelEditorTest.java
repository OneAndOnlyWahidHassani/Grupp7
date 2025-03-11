package levelEditor.controller;

import LevelEditor.controller.MainLE;
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
     * Test valid input of Level editor name
     * Test invalid input of Level editor name
     */
    public void NIVS1_2_3_1() {
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
    public void NIVS_1_2_3_2() {
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
    /**
     * Testing valid input for creating a new level.
     */
    public void NIVS_1_2() {
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
    /**
     * Testing random dimension and that it's saved properly.
     */
    public void NIVS_1_2_1() {
        Path expectedFilePath = Paths.get("createdLevels/TestLevel_Niv.dat");
        int dimensionCLicks = random.nextInt(3) + 1;

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
        for (int i = 0; i < dimensionCLicks; i++) {
            clickOn();
            sleep(500);
        }


        moveTo("#LeftButtonThem").moveBy(0, -50);
        clickOn();

        moveToAndClickOn("textFieldLevelEditor");
        write("TestLevel_Niv");
        moveToAndClickOn("selectButtonLevelEditor");

        switch (dimensionCLicks) {
            case 1:
                assertEquals("14x14", mainLE.getDimension(), "Dimension 14X14");
                break;
            case 2:
                assertEquals("10x10", mainLE.getDimension(), "Dimension 10X10");
                break;
            case 3:
                assertEquals("18x18", mainLE.getDimension(), "Dimension 18X18"); // Cirkulär rotation
                break;

        }



        assertTrue(Files.exists(expectedFilePath));

    }

    /**
     * testing random theme when creating level
     */
    @Test
    public void NIVS_1_2_2(){
        Path expectedFilePath = Paths.get("createdLevels/TestLevel_Niv.dat");
        int worldsCLicks = random.nextInt(6) + 1;
        System.out.println(worldsCLicks + "tre");
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
        if (worldsCLicks == 1)
            assertEquals(3, mainLE.getThemeInt(), "cloud");
        else if (worldsCLicks == 2)
            assertEquals(4, mainLE.getThemeInt(), "desert");
        else if (worldsCLicks == 3)
            assertEquals(0, mainLE.getThemeInt(), "forest");
        else if (worldsCLicks == 4)
            assertEquals(1, mainLE.getThemeInt(), "lava");
        else if (worldsCLicks == 5)
            assertEquals(5, mainLE.getThemeInt(), "space");
        else if (worldsCLicks == 6)
            assertEquals(2, mainLE.getThemeInt(), "underground");
        else if (worldsCLicks == 7)
            assertEquals(3, mainLE.getThemeInt(), "cloud");


        assertTrue(Files.exists(expectedFilePath));

    }

    @Test
    /**
     * Testing opening an exsisting level in level Editor.
     * @author alanahColeman
     */
    public void NIVH_1_0() {
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
        moveToAndClickOn("editLevelButton");

        sleep(500); // Vänta på popupen

        // Välj en nivå i ListView
        moveToAndClickOn("TestLevelNewName_Niv");
        clickOn("TestLevelNewName_Niv");

        sleep(500);

        moveToAndClickOn("LoadLevelButton");
        clickOn();

        assertTrue(Files.exists(expectedFilePath));
    }


    private void moveToAndClickOn(String buttonId) {
        moveTo("#" + buttonId);
        clickOn();
    }
}