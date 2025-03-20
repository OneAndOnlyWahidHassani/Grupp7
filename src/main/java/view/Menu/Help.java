package view.Menu;

import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import view.AudioPlayer;


/**
 * The Help class represents a view that provides instructions or assistance to the user.
 * It displays an animated image indicating that the user can interact with the help screen.
 * The view transitions back to the main menu when clicked.
 *
 */

public class Help extends VBox {
    private MainProgram mainProgram;
    private Image pressMouse;
    private AudioPlayer audioPlayer;

    /**
     * Constructor that initializes the help screen with the provided main program and audio player.
     * It also sets up the background, animations, and event listeners for user interaction.
     *
     * @param mainProgram The main program managing the application's logic.
     * @param audioPlayer The audio player used for sound effects.
     */
    public Help(MainProgram mainProgram, AudioPlayer audioPlayer){
        pressMouse = new Image("file:files/menuImages/helppicmouse.png");
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.setId("helpView");
        setBackground();
        pressMouseAnimation();
        addListener();
    }
    /**
     * Sets the background image of the help screen.
     * This method is called during initialization to provide a visual background for the help view.
     */
    public void setBackground(){
        BackgroundImage myBI= new BackgroundImage(new Image("file:files/menuImages/helppicnew.png",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
    }

    /**
     * Adds a mouse click listener to the help screen. When the screen is clicked,
     * it transitions to the main menu and plays a button sound.
     */
    public void pressMouseAnimation(){
        ImageView pressMouseView = new ImageView(pressMouse);
        pressMouseView.setStyle("fx-background-color: transparent;");
        pressMouseView.toFront();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1500), pressMouseView);
        fadeTransition.setCycleCount(Transition.INDEFINITE);
        pressMouseView.setOpacity(0);
        getChildren().add(pressMouseView);

        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


    /**
     * Lägger till listener för knapptryck och skiftar scen till menyn
     */
    public void addListener(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainProgram.changeToMenu();
                audioPlayer.playButtonSound();
            }
        });
    }

}
