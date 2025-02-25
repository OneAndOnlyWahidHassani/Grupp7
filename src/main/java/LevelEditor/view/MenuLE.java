package LevelEditor.view;

import control.MainProgram;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import view.AudioPlayer;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuLE extends Pane {
    private MainProgram mainProgram;
    private AudioPlayer audioPlayer;
    private Font customFont;


    public MenuLE(MainProgram mainProgram, AudioPlayer audioPlayer) {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        loadFont();
        setBackground();
        addButtons();
    }



    private void loadFont() {
        try {
            customFont = Font.loadFont(new FileInputStream("files/fonts/PressStart2P.ttf"), 20);
        } catch (FileNotFoundException e) {
            System.out.println("Fontfilen hittades inte!");
        }
    }

    private void setBackground() {
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg", 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    private void addButtons() {
        ImageView titelImageView = new ImageView(new Image("file:files/texts/lvl.png", 300, 80, false, true));
        titelImageView.setStyle("fx-background-color: transparent;");
        titelImageView.setTranslateX(250);
        titelImageView.setTranslateY(50);


        Button createButton = new Button("Create New Level");
        createButton.setFont(customFont);
        createButton.setTranslateX(250);
        createButton.setTranslateY(220);
        createButton.setOnMouseEntered(e -> createButton.setTextFill(Color.RED));
        createButton.setOnMouseExited(e -> createButton.setTextFill(Color.BLACK));
        createButton.setOnAction(e -> {
            mainProgram.changeToLevelEditor();
            audioPlayer.playButtonSound();
        });


        Button editButton = new Button("Edit Level");
        editButton.setFont(customFont);
        editButton.setTranslateX(250);
        editButton.setTranslateY(310);
        editButton.setOnMouseEntered(e -> editButton.setTextFill(Color.RED));
        editButton.setOnMouseExited(e -> editButton.setTextFill(Color.BLACK));
        editButton.setOnAction(e -> {
            System.out.println("Edit Level clicked");
            audioPlayer.playButtonSound();
        });

        Image returnI = new Image("file:files/texts/return.png", 250, 30, false, false);
        Image returnIResize = new Image("file:files/texts/return.png", 260, 40, false, false);
        ImageView returnView = new ImageView(returnI);
        returnView.setTranslateX(250);
        returnView.setTranslateY(450);
        returnView.setOnMouseEntered(e -> {
            returnView.setImage(returnIResize);
            returnView.setTranslateX(245);
            returnView.setTranslateY(450);
        });
        returnView.setOnMouseExited(e -> {
            returnView.setImage(returnI);
            returnView.setTranslateX(250);
            returnView.setTranslateY(450);
        });
        returnView.setOnMouseClicked(e -> {
            mainProgram.changeToMenu();
            audioPlayer.playButtonSound();
        });

        getChildren().addAll(titelImageView, createButton, editButton, returnView);
    }

}
