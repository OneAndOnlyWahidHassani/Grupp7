package view.Campaign;


import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.TimeThread;
import model.TotalTime;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class World1Template extends WorldTemplate {

    public World1Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel, int world, AudioPlayer audioPlayer, int seconds) throws FileNotFoundException {
        super(level, currentLevel, heartCrystals, mainProgram, rightPanel, world, audioPlayer, seconds);
    }

    public World1Template() {
        super();
    }
}


