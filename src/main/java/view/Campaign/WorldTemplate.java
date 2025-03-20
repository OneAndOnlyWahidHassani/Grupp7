package view.Campaign;


import control.GameController;
import control.EnemyController;
import javafx.animation.FadeTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.util.Duration;
import view.AudioPlayer;
import view.Menu.RightPanel;
import view.WorldIntroAnimation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class represents the World Template in the game, handling the graphical layout of the world,
 * including borders, paths, walls, collectibles, and the game objects. It uses JavaFX components
 * to render the level based on the level array provided.
 *
 * @author Elvira Grubb
 */
public class WorldTemplate extends GridPane {
    //private MainProgram mainProgram;
    private GameController gameController;
    private int[][] levelArray;
    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;
    private Image heart;
    private Image breakableWall;
    private int squareSize;
    private int heartCrystals;
    private Image pickAxeImage;
    private int seconds;

    private RightPanel rightPanel;
    private AudioPlayer audioPlayer;

    public WorldTemplate(){

    }

    /**
     * Constructor that initializes the WorldTemplate with the given level array and GameController.
     * Sets up the world and graphics based on the level data and controller information.
     *
     * @param levelArray The array representing the world layout.
     * @param gameController The GameController that manages the game state.
     * @throws FileNotFoundException if there is an issue loading world images.
     */
    public WorldTemplate(int[][] levelArray, GameController gameController) throws FileNotFoundException {
        this.gameController = gameController;
        this.levelArray = levelArray;
        this.seconds = gameController.getSeconds();
        this.heartCrystals = gameController.getHeartCrystals();
        this.rightPanel = gameController.getRightPanel();
        this.audioPlayer = gameController.getAudioPlayer();
        this.setId("worldTemplate");
        rightPanel.changeHeartCounter(String.valueOf(heartCrystals));
        squareSize = 600/(levelArray.length+2);
        setBackground();
        setupImages(gameController.getWorld());
        setupBorders();
        setupLevel();
        rightPanel.setSTARTTIME(seconds);
        rightPanel.resetTimerLabel();
    }

    /**
     * Sets the background for the world template.
     */
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }

    /**
     * Sets up the borders around the world layout.
     */
    public void setupBorders() {
        for (int i = 0; i < levelArray.length + 1; i++) {
            add(getBorders(), i, 0);
        }
        for (int i = 0; i < levelArray.length + 1; i++) {
            add(getBorders(), 0, i);
        }
        for (int i = 0; i < levelArray.length + 2; i++) {
            add(getBorders(), i, levelArray.length + 1);
        }
        for (int i = 0; i < levelArray.length + 2; i++) {
            add(getBorders(), levelArray.length + 1, i);
        }
    }

    /**
     * Converts the level array values into graphical components (e.g., walls, paths, collectibles)
     * and adds them to the grid.
     */
    public void setupLevel() {
        for (int i = 0; i < levelArray.length; i++) {
            for (int j = 0; j < levelArray.length; j++) {

                if (levelArray[i][j] == 1) {
                    add(getPath(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 0){
                    add(getWall(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 2){
                    add(getStart(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 3){
                    add(getGoal(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 4){
                    add(getPath(),j + 1,i + 1);
                    add(addCollectible(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 5){
                    add(getPath(),j + 1,i + 1);
                    add(addPickAxe(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 6){
                    add(getBreakableWall(),j + 1,i + 1);
                }
                else if (levelArray[i][j] == 7){
                    add(getPath(),j + 1,i + 1);
                    add(addHeartCrystal(),j + 1,i + 1);
                }
            }
        }
    }
    /**
     * Returns the name of the world based on the integer value.
     *
     * @param value The integer value representing the world.
     * @return The name of the world as a string.
     */
    public String getWorldName(int value){
        switch (value){
            case 1:
                return "forest";
            case 2:
                return  "underground";
            case 3:
                return "lava";
            case 4:
                return "cloud";
            case 5:
                return "desert";
            case 6:
                return "space";
            default:
                return "";
        }
    }


    /**
     * Sets up the images for different components (e.g., walls, paths) based on the current world.
     *
     * @param value The current world value used to select the correct set of images.
     */
    public void setupImages(int value){
        String folder = getWorldName(value);
        path = new Image("file:files/" + folder + "/path.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/" + folder + "/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/" + folder + "/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/" + folder + "/start.png", squareSize, squareSize, false, false);
        pickAxeImage = new Image("file:files/items/pickaxe.png", squareSize, squareSize, false, false);
        heart = new Image("file:files/items/heart.png", squareSize, squareSize, false, false);
        if (value == 3) {
            breakableWall = new Image("file:files/cloud/breakablewall.png", squareSize, squareSize, false, false);
        }
        else {
            breakableWall = new Image("file:files/breakablewall.png", squareSize, squareSize, false, false);
        }
        if(value!=5){
            border = new Image("file:files/" + folder + "/border.png", squareSize, squareSize, false, false);
            wall = new Image("file:files/" + folder + "/wall.png", squareSize, squareSize, false, false);
        }
    }
    /**
     * Creates and returns a Label that represents a wall in the game.
     * The label is clickable and triggers events when the mouse enters or exits.
     *
     * @return The Label representing a wall.
     */
    public Label getWall() {
        Label label = new Label();
        ImageView wallView = new ImageView(wall);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        label.setOnMouseEntered(e -> gameController.enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    /**
     * Creates and returns a Label that represents a path in the game.
     *
     * @return The Label representing a path.
     */
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        return label;
    }
    /**
     * Creates and returns a Label that represents a border around the game.
     *
     * @return The Label representing a border.
     */
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> gameController.enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }

    /**
     * Creates and returns a Label that represents a breakable wall in the game.
     *
     * @return The Label representing a breakable wall.
     */
    private Label getBreakableWall() {
        Label label = new Label();
        ImageView borderView = new ImageView(breakableWall);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> gameController.enteredBreakableWall(e));
        return label;
    }
    /**
     * Creates and returns a Label that represents the goal in the game.
     *
     * @return The Label representing the goal.
     */
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setId("goalLabel");
        label.setOnMouseEntered(e -> {
            try {
                gameController.enteredGoal();
            } catch ( IOException | ClassNotFoundException | InterruptedException fileNotFoundException  ) {
                fileNotFoundException.printStackTrace();
            }
        });
        return label;
    }

    /**
     * Creates and returns a Label that represents the start position in the game.
     *
     * @return The Label representing the start.
     */
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView(start);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setId("startLabel");
        label.setOnMouseClicked(e -> gameController.startLevel());
        return label;
    }

    /**
     * Creates and returns a Label that represents a collectible item in the game.
     *
     * @return The Label representing a collectible item.
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
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, gameController.getMouseListener());
        gameController.getCollectibles().add(collectible);
        return collectible;
    }

    /**
     * Creates and returns a Label that represents a heart crystal in the game.
     *
     * @return The Label representing a heart crystal.
     */
    public Label addHeartCrystal() {
        Label heartCrystal = new Label();
        ImageView borderView = new ImageView(heart);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.8);
        borderView.setEffect(glow);
        heartCrystal.setStyle("fx-background-color: transparent;");
        heartCrystal.setGraphic(borderView);
        heartCrystal.setOpacity(0.8);
        heartCrystal.setOnMouseEntered(e -> gameController.heartCrystalObtained(e));
        return heartCrystal;
    }

    /**
     * Creates and returns a Label that represents a pickaxe item in the game.
     *
     * @return The Label representing a pickaxe.
     */
    public Label addPickAxe() {
        Label pickAxe = new Label();
        ImageView borderView = new ImageView(pickAxeImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        pickAxe.setStyle("fx-background-color: transparent;");
        pickAxe.setGraphic(borderView);
        pickAxe.addEventHandler(MouseEvent.MOUSE_ENTERED, gameController.getMouseListener());
        gameController.getPickaxes().add(pickAxe);
        return pickAxe;
    }


    /**
     * Handles the event when the mouse exits a label by removing any highlight effects.
     *
     * @param e The MouseEvent that triggered this method.
     */
    public void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }
    /**
     * Sets up an animation for introducing a new world.
     *
     * @param mainPaneCampaign The main pane for the campaign view.
     * @param world The world identifier for the world to be introduced.
     */
    public void setNewWorldAnimation(BorderPane mainPaneCampaign, int world){
        WorldIntroAnimation introAnimation = new WorldIntroAnimation(String.valueOf(world));
        mainPaneCampaign.getChildren().add(introAnimation);
        introAnimation.setDisable(true);
        audioPlayer.playWorldIntroSound();
        audioPlayer.playLevelMusic(getWorldName(world));
        rightPanel.setTheTime(seconds);
        rightPanel.resetTimerLabel();
    }


    public int getSquareSize() {
        return squareSize;
    }

    public Image getImagePath(){
        return path;
    }

    public GameController getGameController() {
        return gameController;
    }
}
