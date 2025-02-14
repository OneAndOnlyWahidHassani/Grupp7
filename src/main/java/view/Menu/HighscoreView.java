package view.Menu;

import control.MainProgram;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.HighscoreList;
import view.AudioPlayer;

import java.util.ArrayList;

import static javax.swing.text.StyleConstants.setAlignment;

public class HighscoreView extends Pane{
    private MainProgram mainProgram;
    private Image selectLevel;
    private Image returnImage;
    private Image returnResize;
    private AudioPlayer audioPlayer;
    private int selectedMap;
    HighscoreList highscoreList;
    private int[] highscoreListScores;
    private String[] highscoreListNames;
    ArrayList<Label> highscoreLabels = new ArrayList<>();


    /**
     * Konstruktor som tar emot mainProgram och audioPlayer och kör några metoder för att
     * sätta bilder och knappar
     *
     * @param mainProgram tas emot och instansvariabeln sätts
     * @param audioPlayer tas emot och instansvariabeln sätts
     */
    public HighscoreView(MainProgram mainProgram, AudioPlayer audioPlayer, int map, HighscoreList highscoreList) {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.selectedMap = map;
        this.highscoreList = highscoreList;
        highscoreListScores = highscoreList.getHighscoreScores();
        highscoreListNames = highscoreList.getHighscoreNames();
        setBackground();
        setupLabelsAndImages();
        addButtons();
    }

    /**
     * Metod som länkar Image-objekten till png-filer
     */
    public void setupLabelsAndImages() {
        selectLevel = new Image("file:files/texts/HighscoreTitle.png", 800, 600, false, false);

        for(int i = 0; i < highscoreListScores.length; i++)
        {
            if (highscoreListNames != null)
            {
                Label label = new Label(highscoreListNames[i] + ", " + highscoreListScores[i]);
                highscoreLabels.add(label);
            }

            else
            {
                break;
            }
        }

        returnImage = new Image("file:files/texts/return.png", 250, 30, false, false);
        returnResize = new Image("file:files/texts/return.png", 255, 33, false, false);
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

    /**
     * Metod som placerar bilderna som klickbara ImageViews i scenen med events för knapptryck och hovering.
     * Bilderna förstoras när man hovrar över dem och scenen byts när man trycker på dem.
     */
    public void addButtons() {
        ImageView dimensionView = new ImageView(selectLevel);
        dimensionView.setStyle("fx-background-color: transparent;");

        int x = 275;
        int y = 100;
        String fontFamily = "Arial";
        double fontSize = 26;

        for (Label l : highscoreLabels)
        {
            l.setStyle("-fx-font: 24 arial;");
            l.setTextFill(Color.WHITE);
            l.setTranslateX(x);
            l.setTranslateY(y);
            l.toFront();
            getChildren().add(l);

            y = y + 35;
        }

        ImageView returnView = new ImageView(returnImage);
        returnView.setStyle("fx-background-color: transparent;");
        returnView.setTranslateX(300);
        returnView.setTranslateY(450);
        returnView.toFront();
        returnView.setPickOnBounds(true);
        returnView.setOnMouseEntered(e -> {
            returnView.setImage(returnResize);
            returnView.setTranslateX(298);
            returnView.setTranslateY(448);
        });
        returnView.setOnMouseExited(e -> {
            returnView.setImage(returnImage);
            returnView.setTranslateX(300);
            returnView.setTranslateY(450);
        });
        returnView.setOnMouseClicked(e -> {
            mainProgram.changeToMenu();
            audioPlayer.playButtonSound();
        });

        getChildren().addAll(dimensionView, returnView);
    }
}
