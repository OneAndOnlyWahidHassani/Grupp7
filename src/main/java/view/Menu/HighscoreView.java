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

/**
 * The HighscoreView class displays the high score list for a specific game map.
 * It shows the high scores, names of players, and provides a return button to navigate back to the main menu.
 * The high scores are retrieved from a HighscoreList object, and the display is interactive with hover and click effects.
 *
 * @author Elvira Grubb
 */
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
     * Constructor that initializes the HighscoreView with the provided main program, audio player, map selection, and high score list.
     * It also sets up the background, labels, and buttons for the high score display.
     *
     * @param mainProgram The main program managing the application's logic.
     * @param audioPlayer The audio player used for sound effects.
     * @param map The map for which high scores are displayed.
     * @param highscoreList The list of high scores to be displayed.
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
     * Sets up the labels and images for displaying the high scores and other visual elements in the menu.
     * It retrieves the high scores and player names and creates labels for each entry.
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
     * Sets the background image for the high score screen.
     * This method is called during initialization to provide a visual background for the high score view.
     */
    public void setBackground() {
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg", 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    /**
     * Adds the interactive buttons and labels to the high score view.
     * The return button allows the user to navigate back to the main menu.
     * Labels displaying the high scores are also added and positioned on the screen.
     * The return button's image changes on hover, and it navigates to the menu when clicked.
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
