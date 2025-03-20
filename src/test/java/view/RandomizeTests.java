package view;

import control.MainProgram;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Maps.World1Maps;
import model.Maps.World2Maps;
import org.junit.jupiter.api.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import view.Menu.ChooseDimension;
import view.Menu.RightPanel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RandomizeTests extends ApplicationTest {
    private MainProgram mainProgram;
    private Random random;
    private RightPanel rightPanel;

    @Override
    public void start(Stage stage)
    {
        random = new Random();
        mainProgram = new MainProgram();
        try {
            mainProgram.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Testar om Randomize funktionen skapar en map
     * @author Elvira Grubb
     */
    @Test
    public void testRandomizeFunction()
    {
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("randomizeButton");

        moveToAndClickOn("tenByTenView");
        RightPanel levelPanel = lookup("#campaignScene").query();
        assertNotNull(levelPanel, "Panel should exist");

        sleep(500);
    }

    private void moveToAndClickOn(String buttonId){
        moveTo("#" + buttonId);
        clickOn();
    }
}