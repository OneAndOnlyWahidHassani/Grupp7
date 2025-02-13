package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Menu.RightPanel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Testar LOG8.4
class TimeThreadTest {

    private TimeThread timeThread;
    private RightPanel panel;

    @BeforeEach
    void setUp() {
        panel = mock(RightPanel.class);
        timeThread = new TimeThread(3, panel);
    }

    @Test
    void testTimerReachesZeroAndGameEnds() throws InterruptedException {
        timeThread.start();

        Thread.sleep(3500);

        verify(panel, times(1)).startTask();

        assertTrue(timeThread.isInterrupted() || !timeThread.isAlive(), "Tråden borde vara stoppad");
    }
}
