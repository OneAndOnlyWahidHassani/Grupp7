package view.Menu;

import control.MainProgram;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.service.finder.WindowFinder;
import org.testfx.util.WaitForAsyncUtils;
import view.AudioPlayer;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NewMenuButtonsTest extends ApplicationTest {
    private MainProgram mainProgram;

    @Override
    public void start(Stage stage) {
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

    /**
     * Testar om en CampaignScene har startat
     */
    @Test
    public void testCampaignButton()
    {
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("campaignButton");

        RightPanel panel = lookup("#campaignScene").query();
        assertNotNull(panel, "Panel should exist");
    }

    /**
     * Testar om Randomizeknappen leder till rätt ställe
     */
    @Test
    public void testRandomizeButton()
    {
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("randomizeButton");
        ChooseDimension choosePanel = lookup("#chooseDimension").query();
        assertNotNull(choosePanel, "Panel should exist");

        sleep(500);
    }

    @Test
    public void testHelpButton()
    {
        WaitForAsyncUtils.waitForFxEvents();
        moveToAndClickOn("introButton");
        moveToAndClickOn("helpButton");
        Help helpView = lookup("#helpView").query();
        assertNotNull(helpView, "Panel should exist");

        sleep(500);
    }



    private void moveToAndClickOn(String buttonId){
        moveTo("#" + buttonId);
        clickOn();
    }
}