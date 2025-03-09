package LevelEditor.view;

import LevelEditor.controller.MainLE;
import LevelEditor.model.Level;
import control.MainProgram;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MazeGeneration.MazeGenerator;
import model.ReaderWriter.FileManager;
import view.AudioPlayer;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class MenuLE extends Pane {
    private MainProgram mainProgram;
    private AudioPlayer audioPlayer;
    private Font customFont;
    private FileManager fileManager;
    private MainLE mainLE;


    public MenuLE(MainProgram mainProgram, AudioPlayer audioPlayer, MainLE mainLE) {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.fileManager = new FileManager();
        this.mainLE = mainLE;
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
        createButton.setId("createLevelButton");
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
            showLevelSelection();
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

    private void showLevelSelection() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Select a Level");

        ListView<String> levelList = new ListView<>();
        File createdLevelsFolder = new File("createdLevels");

        if (createdLevelsFolder.exists() && createdLevelsFolder.isDirectory()) {
            String[] levelFiles = createdLevelsFolder.list((dir, name) -> name.endsWith(".dat"));

            if (levelFiles != null) {
                // Ta bort ".dat" från varje filnamn innan de läggs till i listan
                for (String levelFile : levelFiles) {
                    String levelNameWithoutExtension = levelFile.replace(".dat", "");
                    levelList.getItems().add(levelNameWithoutExtension);
                }
            }
        }

        Button selectButton = new Button("Load Level");
        selectButton.setOnAction(e -> {
            String selectedLevel = levelList.getSelectionModel().getSelectedItem();
            if (selectedLevel != null) {
                System.out.println("Loading level: " + selectedLevel);
                mainLE.loadLevel(selectedLevel);
                int themeInt = mainLE.getThemeInt();
                System.out.println("Theme: " + themeInt);
                int dimensionInt = Integer.parseInt(mainLE.getDimension().split("x")[0]);
                System.out.println("Dimension: " + dimensionInt);
                int[][] maze = mainLE.getMazeGenerator().getRawMazeArray();
                System.out.println("Maze: " + Arrays.deepToString(maze));

                try {
                    mainProgram.enterLevelEditorFromEdit(maze,dimensionInt, themeInt);
                } catch (FileNotFoundException fileNotFoundException) {
                    throw new RuntimeException("Could not find file");
                }

                popupStage.close();
            }
        });

        VBox layout = new VBox(10, levelList, selectButton);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 400);
        popupStage.setScene(scene);
        popupStage.show();
    }




}
