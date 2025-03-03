package view.Campaign;

import control.MainProgram;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.AudioPlayer;
import view.Menu.RightPanel;

import static org.mockito.Mockito.*;

// Testar LOG5.3
class World1TemplateTest {
    private MainProgram mainProgramMock;
    private AudioPlayer audioPlayerMock;
    private RightPanel rightPanelMock;
    private int heartCrystals;
    private boolean startButtonPressed;

    /**
     * Tester från sprint 1. Inte funktionella längre?
     */

    /*@BeforeEach
    void setUp() {
        mainProgramMock = mock(MainProgram.class);
        audioPlayerMock = mock(AudioPlayer.class);
        rightPanelMock = mock(RightPanel.class);
        heartCrystals = 2;
        startButtonPressed = true;
    }

    @Test
    void testEnteredGhost_LoseHeart() {
        ImageView ghostMock = mock(ImageView.class);
        MouseEvent mockEvent = mock(MouseEvent.class);
        when(mockEvent.getSource()).thenReturn(ghostMock);

        enteredGhost(mockEvent);
        verify(audioPlayerMock).playMobSound();
        verify(audioPlayerMock).playDeathSound();

        verify(rightPanelMock).changeHeartCounter("1");
    }

    @Test
    void testEnteredGhost_GameOver() {
        heartCrystals = 1;
        ImageView ghostMock = mock(ImageView.class);
        MouseEvent mockEvent = mock(MouseEvent.class);
        when(mockEvent.getSource()).thenReturn(ghostMock);

        enteredGhost(mockEvent);

        verify(mainProgramMock).gameOver();
    }

    public void enteredGhost(MouseEvent e) {
        if (startButtonPressed) {
            audioPlayerMock.playMobSound();
            audioPlayerMock.playDeathSound();
            heartCrystals--;
            rightPanelMock.changeHeartCounter(String.valueOf(heartCrystals));

            if (heartCrystals == 0) {
                mainProgramMock.gameOver();
            }
            startButtonPressed = false;
        }
    }*/
}