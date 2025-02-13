package view;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
// Testat LJU1.1, 1.4 och 1.7
class AudioPlayerTest {

    private AudioPlayer audioPlayer;

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        audioPlayer = spy(new AudioPlayer());
    }

    @Test
    void playBreakableWallSound() {
        audioPlayer.playBreakableWallSound();
        verify(audioPlayer).playBreakableWallSound();
    }

    @Test
    void playGameOverSound() {
        audioPlayer.playGameOverSound();
        verify(audioPlayer).playGameOverSound();
    }

    @Test
    void muteMusic() {
        audioPlayer.muteMusic(true);
        verify(audioPlayer).muteMusic(true);
    }
}
