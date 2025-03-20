package view.Randomize;


import control.GameController;
import model.MazeGeneration.GenerateNextLevel;
import control.MainProgram;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import javafx.scene.media.Media;
import model.TimeThread;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author André Eklund
 * edit Viktor Näslund
 */

public class MapTemplate extends GridPane {

    private MainProgram mainProgram;
    private GenerateNextLevel generateNextLevel;
    private GameController controller;
    private RightPanel rightPanel;
    private int[][] level;
    private int themeInt;
    private ArrayList<Label> collectibles = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();
    private AudioPlayer audioPlayer;

    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private int collectiblesObtained = 0;
    private boolean pickaxeObtained;
    private ArrayList<Label> pickaxes = new ArrayList<>();
    private int squareSize;;

    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;
    private int heartCrystals;

    private File diamondSound = new File("files/sounds/Diamond1.mp3");
    private Media diamondMedia = new Media(diamondSound.toURI().toString());
    private MediaPlayer diamondPlayer = new MediaPlayer(diamondMedia);

    private File deathSound = new File("files/sounds/MazegenDeath.mp3");
    private Media deathMedia = new Media(deathSound.toURI().toString());
    private MediaPlayer deathPlayer = new MediaPlayer(deathMedia);


    private File startSound = new File("files/sounds/MazegenStart.mp3");
    private Media startMedia = new Media(startSound.toURI().toString());
    private MediaPlayer startPlayer = new MediaPlayer(startMedia);

    private File goalSound = new File("files/sounds/MazegenGoal.mp3");
    private Media goalMedia = new Media(goalSound.toURI().toString());
    private MediaPlayer goalPlayer = new MediaPlayer(goalMedia);

    private int seconds;
    private boolean totalTimeStarted = false;
    private boolean gameStarted;
    private TimeThread time;
    private boolean startNotClickedOnce;
    private boolean wallDestroyed;


    /**
     * Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
     */
    public MapTemplate(int[][] level, MainProgram mainProgram, GenerateNextLevel generateNextLevel) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.level = level;
        this.generateNextLevel = generateNextLevel;

        squareSize = 600/(level.length+2);
        setBackground();
        setupImages(new Random().nextInt(6));
        setupBorders();
        setupLevel();
    }

    public MapTemplate (int[][] maze, MainProgram mainProgram, GameController gameController, GenerateNextLevel generateNextLevel, int themeInt) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.level = maze;
        squareSize = 600/(level.length+2);
        this.generateNextLevel = generateNextLevel;
        this.themeInt = themeInt;
        this.controller = gameController;
        this.seconds = gameController.getSeconds();
        this.heartCrystals = gameController.getHeartCrystals();
        this.rightPanel = gameController.getRightPanel();
        this.audioPlayer = gameController.getAudioPlayer();
        rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        setBackground();
        setupImages(themeInt);
        setupBorders();
        setupLevelforEdited();
        rightPanel.setSTARTTIME(seconds);
        rightPanel.resetTimerLabel();
    }
    /**
     * Sätter bakgrunden i fönstret.
     */
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }
    /**
     * Skapar en ram runt spelplanen.
     */
    public void setupBorders() {
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), i, 0);
        }
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), 0, i);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), i, level.length + 1);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), level.length + 1, i);
        }
    }
    /**
     * Omvandlar värdena i arrayen av siffror till olika grafiska komponenter baserat på vilken siffra en position har.
     * Exempelvis så representerar 1:or väg, 0:or väggar, och 7:or hjärtan osv.
     */
    public void setupLevel() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {

                if (level[i][j] == 1) {
                    add(getPath(),j + 1,i + 1);
                    if (new Random().nextInt(5) == 4) {
                        add(addCollectible(),j + 1,i + 1);
                    }
                }
                else if (level[i][j] == 0){
                    add(getWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 2){
                    add(getStart(),j + 1,i + 1);
                }
                else if (level[i][j] == 3){
                    add(getGoal(),j + 1,i + 1);
                }
            }
        }
    }

    public void setupLevelforEdited() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {
                switch (level[i][j]) {
                    case 0:
                        add(getWallforEdited(getThemeString(themeInt)), j + 1, i + 1);
                        break;
                    case 1:
                        add(getPathForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        break;
                    case 2:
                        add(getStartForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        break;
                    case 3:
                        add(getGoalForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        break;
                    case 4:
                        add(getPathForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        add(addCollectibleForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        break;
                    case 5:
                        add(getPathForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        add(addHeartForEdited(), j + 1, i + 1);
                        break;
                    case 6:
                        add(getPathForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        add(addPickaxeForEdited(), j + 1, i + 1);
                        break;
                    case 7:
                        add(getBreakableWallForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        break;
                    case 8:
                        add(getBordersForEdited(getThemeString(themeInt)), j + 1, i + 1);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private String getThemeString(int themeInt) {
        switch (themeInt) {
            case 0: return "forest";
            case 1: return "lava";
            case 2: return "underground";
            case 3: return "cloud";
            case 4: return "desert";
            case 5: return "space";
            default: return "forest";
        }
    }
    /**
     * Instansierar de olika bilderna som används som grafik inuti spelet.
     * Baserad på value så sätts bilderna till en specifik folder per värld.
     * @param value Den aktuella världen.
     */
    public void setupImages(int value){

        String folder = "";

        if (value == 0) {
            folder = "forest";
        }
        else if (value == 1) {
            folder = "lava";
        }
        else if (value == 2) {
            folder = "underground";
        }
        else if(value == 3) {
            folder = "cloud";
        }
        else if(value == 4) {
            folder = "desert";
        }
        else if(value == 5) {
            folder = "space";
        }

        path = new Image("file:files/" + folder + "/path.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/" + folder + "/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/" + folder + "/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/" + folder + "/start.png", squareSize, squareSize, false, false);

        if(value!=5){
            border = new Image("file:files/" + folder + "/border.png", squareSize, squareSize, false, false);
            wall = new Image("file:files/" + folder + "/wall.png", squareSize, squareSize, false, false);
        }
    }

    /**
     * En metod som skapar ett objekt av label som representerar en vägg.
     * @return Returnerar en label.
     */
    public Label getWall() {
        Label label = new Label();
        ImageView wallView = new ImageView(wall);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }

    public Label getWallforEdited(String theme) {
        Label label = new Label();
        Image wallImage = new Image("file:files/" + theme + "/wall.png", squareSize, squareSize, false, false);
        ImageView wallView = new ImageView(wallImage);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        label.setOnMouseEntered(e -> enteredWallForEdited(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    /**
     * En metod som skapar ett objekt av label som representerar en väg.
     * @return Returnerar en label.
     */
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        return label;
    }

    public Label getPathForEdited(String theme) {
        Label label = new Label();
        Image pathImage = new Image("file:files/" + theme + "/path.png", squareSize, squareSize, false, false);
        ImageView pathView = new ImageView(pathImage);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        return label;
    }
    /**
     * En metod som skapar ett objekt av label som representerar en border.
     * @return Returnerar en label.
     */
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }

    private Label getBordersForEdited(String theme) {
        Label label = new Label();
        Image borderImage = new Image("file:files/" + theme + "/border.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(borderImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> enteredWallForEdited(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    /**
     * En metod som skapar ett objekt av label som representerar en förstörbar vägg.
     * @return Returnerar en label.
     */
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> {
            try {
                enteredGoal();
            } catch (FileNotFoundException | InterruptedException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        return label;
    }

    private Label getGoalForEdited(String theme) {
        Label label = new Label();
        Image goalImage = new Image("file:files/" + theme + "/goal.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(goalImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> {
            try {
                enteredGoalFromEdit();
            } catch (FileNotFoundException | InterruptedException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        return label;
    }
    /**
     * En metod som skapar ett objekt av label som representerar start.
     * @return Returnerar en label.
     */
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView(start);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseClicked(e -> startLevel());
        return label;
    }

    private Label getStartForEdited(String theme) {
        Label label = new Label();
        Image startImage = new Image("file:files/" + theme + "/start.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(startImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseClicked(e -> startLevelFromEdited()
        );
        return label;
    }
    /**
     * En metod som skapar ett objekt av label som representerar en collectible.
     * @return Returnerar en label.
     */
    public Label addCollectible() {
        Label collectible = new Label();
        ImageView borderView = new ImageView(diamond);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        collectible.setStyle("fx-background-color: transparent;");
        collectible.setGraphic(borderView);
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        collectibles.add(collectible);
        return collectible;
    }

    public Label addCollectibleForEdited(String theme) {
        Label collectible = new Label();
        Image diamond = new Image("file:files/" + theme + "/collectible.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(diamond);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        collectible.setStyle("fx-background-color: transparent;");
        collectible.setGraphic(borderView);
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        collectibles.add(collectible);
        return collectible;
    }

    public Label addHeartForEdited() {
        Label heartCrystal = new Label();
        Image heartImage = new Image("file:files/items/heart.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(heartImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.8);
        borderView.setEffect(glow);
        heartCrystal.setStyle("fx-background-color: transparent;");
        heartCrystal.setGraphic(borderView);
        heartCrystal.setOpacity(0.8);
        heartCrystal.setOnMouseEntered(e -> heartCrystalObtained(e));
        return heartCrystal;
    }

    public void heartCrystalObtained(MouseEvent e) {
        Label label = (Label)e.getSource();

        if (startButtonPressed) {
            audioPlayer.playHeartSound();
            label.setVisible(false);
            if(heartCrystals<3){
                heartCrystals++;
                rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
            }
        }
    }

    public Label addPickaxeForEdited() {
        Label pickAxe = new Label();
        Image pickaxeImage = new Image("file:files/items/pickaxe.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(pickaxeImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        pickAxe.setStyle("fx-background-color: transparent;");
        pickAxe.setGraphic(borderView);
        pickAxe.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        pickaxes.add(pickAxe);
        return pickAxe;
    }

    private Label getBreakableWallForEdited(String theme) {
        Label label = new Label();
        Image breakableWallImage = new Image("file:files/" + theme + "/breakableWall.png", squareSize, squareSize, false, false);
        ImageView borderView = new ImageView(breakableWallImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> enteredBreakableWall(e));
        return label;
    }

    /**
     * Om spelaren vidrör muspekaren vid en vägg avslutas spelrundan.
     * @param e Används för att hitta rätt label.
     */
    public void enteredWall(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.play();

        if (startButtonPressed) {
            deathPlayer.play();
            deathPlayer.seek(Duration.ZERO);
            startButtonPressed = false;
        }
    }

    public void enteredWallForEdited(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.play();

        if (startButtonPressed) {

            heartCrystals--;
            rightPanel.changeHeartCounter(String.valueOf(heartCrystals));

            if (heartCrystals == 0) {
                controller.gameOver();
            }
            controller.getAudioPlayer().playDeathSound();
            startButtonPressed = false;
        }
    }

    public void enteredBreakableWall(MouseEvent e){
    Label label = (Label) e.getSource();
    ImageView pathView = new ImageView(new Image("file:files/" + getThemeString(themeInt) + "/path.png", squareSize, squareSize, false, false));

        if (startButtonPressed) {
            if (pickaxeObtained) {
                label.setGraphic(pathView);
                pickaxeObtained = false;
                rightPanel.removePickaxe();
                wallDestroyed = true;
                audioPlayer.playBreakableWallSound();
            } else if (!wallDestroyed) {
                enteredWall(e);
            }
        }
    }
    /**
     * Om spelrundan är aktiverad och spelaren har plockat upp alla collectibles startas nästa nivå.
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public void enteredGoal() throws FileNotFoundException, InterruptedException {
        if (startButtonPressed && allCollectiblesObtained) {
            goalPlayer.play();
            goalPlayer.seek(Duration.ZERO);
            generateNextLevel.generateNewMaze();
        }
    }

    public void enteredGoalFromEdit() throws FileNotFoundException, InterruptedException {
        if(collectibles.isEmpty()){
            allCollectiblesObtained = true;
        }
        if (startButtonPressed && allCollectiblesObtained) {
            goalPlayer.play();
            goalPlayer.seek(Duration.ZERO);
            gameStarted = false;
            controller.gameOver();
        }
    }


    /**
     * Startar spelrundan och timern.
     */
    public void startLevel() {
        startPlayer.play();
        startPlayer.seek(Duration.ZERO);
        startButtonPressed = true;
    }
    public void startLevelFromEdited() {
        if (!totalTimeStarted){
            rightPanel.startTotalTimer();
            rightPanel.setTimerIsStarted(true);
        }
        if (!gameStarted){
            rightPanel.resumeClock();
            gameStarted = true;
            time = new TimeThread(seconds, rightPanel);
            controller.setTime(time);
            time.setGameOver(false);
            time.start();

        }else if (startNotClickedOnce){
            rightPanel.runClock();
            time = new TimeThread(seconds, rightPanel);
            time.setGameOver(false);
            time.start();

        }
        totalTimeStarted = true;
        startNotClickedOnce = false;
        audioPlayer.playStartSound();
        startButtonPressed = true;
    }
    /**
     * När muspekaren lämnar en label slutar den att highlightas.
     * @param e Används för att hitta rätt label.
     */
    private void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }
    /**
     * En listener som körs när spelaren plockar upp en collectible.
     */
    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {
                diamondPlayer.play();
                diamondPlayer.seek(Duration.ZERO);
                for (Label label : pickaxes){
                    if (e.getSource()== label){
                        audioPlayer.playPickAxeSound();
                        label.setVisible(false);
                        pickaxeObtained = true;
                        rightPanel.addPickaxe();
                    }
                }
                Label labelCheck = null;
                for (Label label: collectibles) {
                    if (e.getSource() == label) {
                        audioPlayer.playCollectibleSound();
                        label.setVisible(false);
                        labelCheck = label;
                        break;
                    }
                }
                if(labelCheck != null){
                    collectibles.remove(labelCheck);
                    if(collectibles.isEmpty()){
                        allCollectiblesObtained = true;
                    }
                }

            }
        }
    }
}
