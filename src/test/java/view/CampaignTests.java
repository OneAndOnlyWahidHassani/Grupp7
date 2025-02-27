package view;

import control.MainProgram;
import javafx.stage.Stage;
import model.Maps.World1Maps;
import model.Maps.World2Maps;
import org.junit.jupiter.api.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import view.Menu.RightPanel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CampaignTests extends ApplicationTest {
    private MainProgram mainProgram;
    private Random random;
    private RightPanel rightPanel;

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
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("campaignButton");
        moveToAndClickOn("soundLabel");

        sleep(5000);
    }

    private void moveToAndClickOn(String buttonId){
        moveTo("#" + buttonId);
        clickOn();
    }
}