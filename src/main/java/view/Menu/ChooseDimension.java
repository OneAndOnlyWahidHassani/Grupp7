package view.Menu;

import control.MainProgram;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;

import java.io.FileNotFoundException;
/**
 * Represents the view where the player can choose the dimensions of the game grid.
 * This class sets up images and buttons for different grid sizes, handling mouse events
 * for hover and click actions. The game switches to the chosen grid size when a button is clicked.
 *
 */
public class ChooseDimension extends Pane {
    private MainProgram mainProgram;
    private Image chooseDimension;
    private Image tenByTen;
    private Image tenByTenResize;
    private Image fourteen;
    private Image fourteenResize;
    private Image eighteen;
    private Image eighteenResize;
    private Image pain;
    private Image painResize;
    private Image returnImage;
    private Image returnResize;
    private AudioPlayer audioPlayer;


    /**
     * Constructor that initializes the main program and audio player and sets up the images and buttons.
     *
     * @param mainProgram the main program controlling the game.
     * @param audioPlayer the audio player for managing sound effects.
     */
    public ChooseDimension(MainProgram mainProgram, AudioPlayer audioPlayer){
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.setId("chooseDimension");
        setBackground();
        setupImages();
        addButtons();
    }

    /**
     * Sets up the images for buttons and backgrounds used in the Choose Dimension view.
     */
    public void setupImages(){
        chooseDimension = new Image("file:files/texts/ChooseDimension.png", 800, 600, false,false);
        tenByTen = new Image("file:files/texts/10x10.png", 250, 30, false, false);
        tenByTenResize = new Image("file:files/texts/10x10.png", 255, 33, false, false);
        fourteen = new Image("file:files/texts/14x14.png", 250, 30, false, false);
        fourteenResize = new Image("file:files/texts/14x14.png", 255, 33, false, false);
        eighteen = new Image("file:files/texts/18x18.png", 250, 30, false, false);
        eighteenResize = new Image("file:files/texts/18x18.png", 255, 33, false, false);
        pain = new Image("file:files/texts/Pain.png", 250, 30, false, false);
        painResize = new Image("file:files/texts/Pain.png", 255, 33, false, false);
        returnImage = new Image("file:files/texts/return.png", 250,30,false,false);
        returnResize = new Image("file:files/texts/return.png", 255,33,false,false);
    }
    /**
     * Sets the background image for the Choose Dimension screen.
     */
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    /**
     * Adds interactive buttons to the screen. Each button corresponds to a different grid size.
     * When a button is hovered, its image is resized. When clicked, the game changes to the selected grid size.
     */
    public void addButtons(){
        ImageView dimensionView = new ImageView(chooseDimension);
        dimensionView.setStyle("fx-background-color: transparent;");


        ImageView tenByTenView = new ImageView(tenByTen);
        tenByTenView.setStyle("fx-background-color: transparent;");
        tenByTenView.setTranslateX(275);
        tenByTenView.setTranslateY(200);
        tenByTenView.toFront();
        tenByTenView.setPickOnBounds(true);
        tenByTenView.setId("tenByTenView");
        tenByTenView.setOnMouseEntered(e -> {
            tenByTenView.setImage(tenByTenResize);
            tenByTenView.setTranslateX(273);
            tenByTenView.setTranslateY(198);
        });
        tenByTenView.setOnMouseExited(e -> {
            tenByTenView.setImage(tenByTen);
            tenByTenView.setTranslateX(275);
            tenByTenView.setTranslateY(200);
        });
        tenByTenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(10);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView fourteenView = new ImageView(fourteen);
        fourteenView.setStyle("fx-background-color: transparent;");
        fourteenView.setTranslateX(275);
        fourteenView.setTranslateY(250);
        fourteenView.toFront();
        fourteenView.setPickOnBounds(true);
        fourteenView.setOnMouseEntered(e -> {
            fourteenView.setImage(fourteenResize);
            fourteenView.setTranslateX(273);
            fourteenView.setTranslateY(248);
        });
        fourteenView.setOnMouseExited(e -> {
            fourteenView.setImage(fourteen);
            fourteenView.setTranslateX(275);
            fourteenView.setTranslateY(250);
        });
        fourteenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(14);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView eighteenView = new ImageView(eighteen);
        eighteenView.setStyle("fx-background-color: transparent;");
        eighteenView.setTranslateX(275);
        eighteenView.setTranslateY(300);
        eighteenView.toFront();
        eighteenView.setPickOnBounds(true);
        eighteenView.setOnMouseEntered(e -> {
            eighteenView.setImage(eighteenResize);
            eighteenView.setTranslateX(273);
            eighteenView.setTranslateY(298);
        });
        eighteenView.setOnMouseExited(e -> {
            eighteenView.setImage(eighteen);
            eighteenView.setTranslateX(275);
            eighteenView.setTranslateY(300);
        });
        eighteenView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(18);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        ImageView painView = new ImageView(pain);
        painView.setStyle("fx-background-color: transparent;");
        painView.setTranslateX(275);
        painView.setTranslateY(350);
        painView.toFront();
        painView.setPickOnBounds(true);
        painView.setOnMouseEntered(e -> {
            painView.setImage(painResize);
            painView.setTranslateX(273);
            painView.setTranslateY(348);
        });
        painView.setOnMouseExited(e -> {
            painView.setImage(pain);
            painView.setTranslateX(275);
            painView.setTranslateY(350);
        });
        painView.setOnMouseClicked(e -> {
            try {
                mainProgram.changeToRandomize(28);
                audioPlayer.playButtonSound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

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

        getChildren().addAll(dimensionView,tenByTenView,fourteenView,eighteenView,painView,returnView);
    }

}
