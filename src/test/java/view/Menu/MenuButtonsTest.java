package view.Menu;

import static org.mockito.Mockito.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;
import view.AudioPlayer;

import control.MainProgram;

import java.io.FileNotFoundException;

@ExtendWith(ApplicationExtension.class)
class MenuButtonsTest {

    private Menu menu;
    private MainProgram mainProgram;
    private AudioPlayer audioPlayer;
    private RightPanel panel;

    @BeforeEach
    void setUp() {
        mainProgram = mock(MainProgram.class);
        audioPlayer = mock(AudioPlayer.class);
        panel = mock(RightPanel.class);

        menu = new Menu(mainProgram, audioPlayer, panel);
    }

    @Test
    void testCampaignButton() throws FileNotFoundException {
        ImageView campaignButton = findButton(275, 200);
        assert campaignButton != null;

        fireClickEvent(campaignButton);

        verify(mainProgram).changeToCampaign();
        verify(audioPlayer).playLevelMusic("forest");
        verify(panel).setTheTime(25);
        verify(panel).resetTimerLabel();
    }

    @Test
    void testRandomizeButton() {
        ImageView randomizeButton = findButton(275, 250);
        assert randomizeButton != null;

        fireClickEvent(randomizeButton);

        verify(mainProgram).chooseDimension();
        verify(audioPlayer).playButtonSound();
    }

    @Test
    void testHelpButton() {
        ImageView helpButton = findButton(275, 300);
        assert helpButton != null;

        fireClickEvent(helpButton);

        verify(mainProgram).changeToHelp();
        verify(audioPlayer).playButtonSound();
    }


    private ImageView findButton(double x, double y) {
        return menu.getChildren()
                .stream()
                .filter(node -> node instanceof ImageView && node.getTranslateX() == x && node.getTranslateY() == y)
                .map(node -> (ImageView) node)
                .findFirst()
                .orElse(null);
    }

    private void fireClickEvent(ImageView button) {
        MouseEvent clickEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,
                MouseButton.PRIMARY, 1, true, true, true, true, true,
                true, true, true, true, true, null);
        WaitForAsyncUtils.waitForFxEvents(); // Ensures JavaFX UI updates
        button.fireEvent(clickEvent);
    }
}
