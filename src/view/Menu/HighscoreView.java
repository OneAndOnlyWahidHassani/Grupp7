package view.Menu;

import control.MainProgram;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;

import java.io.IOException;

public class HighscoreView extends Pane {
    private MainProgram mainProgram;
    private AudioPlayer audioPlayer;
    private int[] highscoreList = {1, 2, 3, 4};


    /**
     * Konstruktor som tar emot mainProgram och audioPlayer och kör några metoder för att
     * sätta bilder och knappar
     *
     * @param mainProgram tas emot och instansvariabeln sätts
     * @param audioPlayer tas emot och instansvariabeln sätts
     */
    public HighscoreView(MainProgram mainProgram, AudioPlayer audioPlayer) {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        setBackground();
        addHighscoreList();
    }

    /**
     * Metod som sätter bakgrundsbilden
     */
    public void setBackground() {
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg", 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    public void addHighscoreList()
    {
        for(int i = 0; i < highscoreList.length; i++)
        {
            
        }



        ImageView highscoreView = new ImageView(highscore);
        highscoreView.setStyle("fx-background-color: transparent;");
        highscoreView.setTranslateX(275);
        highscoreView.setTranslateY(400);
        highscoreView.toFront();
        highscoreView.setPickOnBounds(true);
        highscoreView.setOnMouseEntered(e -> {
            highscoreView.setImage(highscoreResize);
            highscoreView.setTranslateX(273);
            highscoreView.setTranslateY(397);
        });
        highscoreView.setOnMouseExited(e -> {
            highscoreView.setImage(highscore);
            highscoreView.setTranslateX(275);
            highscoreView.setTranslateY(400);
        });
        highscoreView.setOnMouseClicked(e -> {
            mainProgram.showHighscore();
            audioPlayer.playButtonSound();
        });

        this.getChildren().addAll(campaignView,randomizeView,selectView, helpView,highscoreView, mazegenView);
    }
}
