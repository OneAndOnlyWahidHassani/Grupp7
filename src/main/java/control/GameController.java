package control;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import model.Maps.*;
import model.TimeThread;
import model.TotalTime;
import view.AudioPlayer;
import view.Campaign.*;
import view.GameOverScreen;
import view.Menu.RightPanel;
import view.Randomize.MapTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The GameController class is responsible for managing the game state and logic.
 * It handles the game clock, player actions, and game over conditions.
 * The class also manages the game audio, collectibles, and level transitions.
 * The GameController class is used in the main game loop to control the game state.
 * The class is also used in the level editor to manage the game state and logic.
 * @author Kasper Svenlin, Elvira Grubb, Wahid Hassani
 */

public class GameController {
    private MainProgram mainProgram;
    private WorldMaps worldMaps;
    private WorldTemplate worldTemplate;
    private RightPanel rightPanel;
    private AudioPlayer audioPlayer;
    private GameOverScreen gameOverScreen;
    private BorderPane mainPaneCampaign;
    private ArrayList<Label> pickaxes = new ArrayList<>();
    private ArrayList<Label> collectibles = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();
    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private int seconds;
    private boolean gameStarted;
    private int world;
    private int level;
    private int heartCrystals;
    private EnemyController enemyController;
    private boolean startNotClickedOnce = true;
    private boolean pickaxeObtained;
    private boolean wallDestroyed;
    private TimeThread time;
    private TotalTime totTime;
    private boolean totalTimeStarted = false;

    /**
     * Constructor for the GameController class.
     * Initializes the game controller with the main program, right panel, audio player, game over screen, and main pane.
     * @param mainProgram
     * @param rightPanel
     * @param audioPlayer
     * @param gameOverScreen
     * @param mainPaneCampaign
     * @param world
     * @param level
     * @author Elvira Grubb
     */

    public GameController(MainProgram mainProgram, RightPanel rightPanel, AudioPlayer audioPlayer, GameOverScreen gameOverScreen, BorderPane mainPaneCampaign, int world, int level) {
        this.mainProgram = mainProgram;
        this.rightPanel = rightPanel;
        this.audioPlayer = audioPlayer;
        this.gameOverScreen = gameOverScreen;
        this.mainPaneCampaign = mainPaneCampaign;
        enemyController = new EnemyController(this);
        totTime = new TotalTime(false);
        time = null;
        this.world = world;
        this.level = level;
        heartCrystals = 3;
    }

    /**
     * Constructor for the GameController class.
     * Initializes the game controller with the main program, right panel, audio player, game over screen, and main pane and more flexible settings for the leveleditor.
     * @param mainProgram
     * @param rightPanel
     * @param audioPlayer
     * @param gameOverScreen
     * @param customLevelPane
     * @param level
     * @param heartCrystals
     * @param time
     * @param themeInt
     * @author Wahid Hassani
     */

    public GameController(MainProgram mainProgram, RightPanel rightPanel, AudioPlayer audioPlayer, GameOverScreen gameOverScreen, BorderPane customLevelPane, int level, int heartCrystals, int time, int themeInt) {
        this.mainProgram = mainProgram;
        this.rightPanel = rightPanel;
        this.audioPlayer = audioPlayer;
        this.gameOverScreen = gameOverScreen;
        this.mainPaneCampaign = customLevelPane;
        enemyController = new EnemyController(this);
        totTime = new TotalTime(false);
        this.time = null;
        seconds = time;
        this.level = level;
        this.heartCrystals = heartCrystals;
        world = themeInt;

    }

    /**
     * This method initializes the game over screen and other logic related to ending the game.
     * It plays the game over sound, stops the music, pauses the clock, and sets the game state to game over.
     * @author Elvira Grubb
     */

    public void gameOver() {
        gameOverScreen = new GameOverScreen(mainProgram);
        mainPaneCampaign.getChildren().add(gameOverScreen);
        audioPlayer.playGameOverSound();
        audioPlayer.stopMusic();
        rightPanel.pauseClock();
        gameStarted = false;
        time.setGameOver(true);
        rightPanel.setGameOver(true);
        time = null;
        rightPanel.removePickaxe();
    }

    /**
     * This method is used to change levels in the campaign mode.
     * @param world
     * @param level
     * @author Elvira Grubb
     */

    public void campaignLevelManager(int world, int level) {
        rightPanel.changeLevelCounter(String.valueOf(world * 10 + level));
        mainPaneCampaign.setCenter(worldTemplate);
        if(level == 1){
            setUpNewWorldAnimation();
        }
    }

    /**
     * This method is used to manage the campaign world.
     * It sets the world maps, seconds, and world template based on the world number.
     * It then sets up the level based on the level number and calls the campaign level manager.
     * @throws FileNotFoundException
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     * @author Elvira Grubb
     */

    public void campaignWorldManager() throws FileNotFoundException, InterruptedException, IOException, ClassNotFoundException {
        worldMaps = new WorldMaps(world);
        worldTemplate = new WorldTemplate();
        switch (world) {
            case 1:
                worldMaps = new World1Maps(world);
                seconds = 25;
                worldTemplate = new World1Template(setUpLevel(level), this);
                break;
            case 2:
                worldMaps = new World2Maps(world);
                seconds = 35;
                worldTemplate = new World2Template(setUpLevel(level), this);
                break;
            case 3:
                worldMaps = new World3Maps(world);
                seconds = 60;
                worldTemplate = new World3Template(setUpLevel(level), this);
                break;
            case 4:
                worldMaps = new World4Maps(world);
                seconds = 80;
                worldTemplate = new World4Template(setUpLevel(level), this);
                break;
            case 5:
                worldMaps = new World5Maps(world);
                seconds = 90;
                worldTemplate = new World5Template(setUpLevel(level), this);
                break;
            case 6:
                worldMaps = new World6Maps(world);
                seconds = 99;
                worldTemplate = new World6Template(setUpLevel(level), this);
                break;

        }
        campaignLevelManager(world, level);
    }

    /**
     * This method sets up the level based on the level number.
     * It returns the level array based on the level number.
     * @param level
     * @return levelArray
     * @throws FileNotFoundException
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     * @author Elvira Grubb
     */

    public int[][] setUpLevel(int level) throws FileNotFoundException, InterruptedException, IOException, ClassNotFoundException {
        int[][] levelArray;
        switch (level) {
            case 1:
                levelArray = worldMaps.getLevel1();
                break;
            case 2:
                levelArray = worldMaps.getLevel2();
                break;
            case 3:
                levelArray = worldMaps.getLevel3();
                break;
            case 4:
                levelArray = worldMaps.getLevel4();
                break;
            case 5:
                levelArray = worldMaps.getLevel5();
                break;
            case 6:
                nextLevel();
            default:
                System.out.println("If we se this something crazy is going on at setUpLevel");
                throw new FileNotFoundException();
        }
        return levelArray;
    }

    /**
     * This method is used to increment the level.
     * It sets the level array based on the level number and returns the level array.
     * @throws FileNotFoundException
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     * @author Elvira Grubb
     */

    public void nextLevel() throws FileNotFoundException, InterruptedException, IOException, ClassNotFoundException {
        if (level >= 5) {
            world++;
            level = 1;
        }else {
            level++;
        }
        campaignWorldManager();
    }

    /**
     * This method is sets the up an animation in the beginning of a new world.
     * @author Elvira Grubb
     */

    public void setUpNewWorldAnimation(){
        worldTemplate.setNewWorldAnimation(mainPaneCampaign, world);
    }


    /**
     * This method is used when the mouse has entered a ghost.
     * @param e
     * @author Elvira Grubb
     */

    public void enteredGhost(MouseEvent e) {
        ImageView view = (ImageView) e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(view);
        fade.setDuration(Duration.seconds(1));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.setToValue(10);
        fade.play();

        if (startButtonPressed) {
            audioPlayer.playMobSound();
            audioPlayer.playDeathSound();
            rightPanel.changeHeartCounter(String.valueOf(--heartCrystals));
            if(heartCrystals == 0){
                gameOver();
            }
            startButtonPressed = false;
        }
    }

    /**
     * This method is used when the mouse has entered a heart crystal.
     * @param e
     * @author Elvira Grubb
     */

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

    /**
     * This method is used when the mouse has entered a wall.
     * @param e
     * @author Elvira Grubb
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

            heartCrystals--;
            rightPanel.changeHeartCounter(String.valueOf(heartCrystals));

            if (heartCrystals == 0) {
                gameOver();
            }
            audioPlayer.playDeathSound();
            startButtonPressed = false;
        }
    }

    /**
     * This method is used when the mouse has entered a goal.
     * @throws InterruptedException, IOException, ClassNotFoundException
     * @author Elvira Grubb
     */

    public void enteredGoal() throws  InterruptedException, IOException, ClassNotFoundException {
        if (startButtonPressed && allCollectiblesObtained) {
            audioPlayer.stopClockSound();
            audioPlayer.playGoalSound();
            nextLevel();
            rightPanel.pauseClock();
            rightPanel.setTheTime(seconds);
            gameStarted = false;
            time.setGameOver(true);
            time = null;
            allCollectiblesObtained = false;
        }
    }

    /**
     * This method starts the level and is called when the has clicked on a certain label, the start label.
     * It starts the total timer and the level timer. It also starts the game if it has not been started before.
     * If the start button has not been clicked once, it will start the level timer. It also plays the start sound.
     * @author Elvira Grubb
     */

    public void startLevel() {
        if (!totalTimeStarted){
            rightPanel.startTotalTimer();
            rightPanel.setTimerIsStarted(true);
        }
        if (!gameStarted){
            rightPanel.resumeClock();
            gameStarted = true;
            time = new TimeThread(seconds, rightPanel);
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
     * This method is used to start a level from the level editor. It is not implemented in this version but if refactored it would be used to start a level from the level editor.
     * @param customMapTemplate
     * @author Wahid Hassani
     */


    public void startLevelFromLevelEditor(MapTemplate customMapTemplate) {
        if (customMapTemplate != null) {
            mainPaneCampaign.setCenter(customMapTemplate);
        }
        if (!totalTimeStarted){
            rightPanel.startTotalTimer();
            rightPanel.setTimerIsStarted(true);
        }
        if (!gameStarted){
            rightPanel.resumeClock();
            gameStarted = true;
            time = new TimeThread(seconds, rightPanel);
            time.setGameOver(false);
            time.start();
        } else if (startNotClickedOnce){
            rightPanel.runClock();
            time.setGameOver(false);
            time.start();
        }
        totalTimeStarted = true;
        startNotClickedOnce = false;
        audioPlayer.playStartSound();
        startButtonPressed = true;
    }

    /**
     * This is called when the mouse has entered a breakable wall.
     * @param e
     * @author Elvira Grubb
     */

    public void enteredBreakableWall(MouseEvent e) {

        Label label = (Label)e.getSource();
        ImageView pathView = new ImageView(worldTemplate.getImagePath());

        if (startButtonPressed) {

            if (pickaxeObtained) {
                label.setGraphic(pathView);
                pickaxeObtained = false;
                rightPanel.removePickaxe();
                wallDestroyed = true;
                audioPlayer.playBreakableWallSound();
            }
            else if (!wallDestroyed) {
                enteredWall(e);
            }
        }
    }

    /**
     * This class is responsible for handling mouse events in the game.
     * It is used to handle mouse events such as clicking on collectibles and pickaxes.
     * @author Elvira Grubb
     */

    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {

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

    /**
     * This method is used to get the enemy controller.
     * @return EnemyController
     * @author Elvira Grubb
     */

    public EnemyController getEnemyController() {
        return enemyController;
    }

    /**
     * This method is used to get the seconds.
     * @return int seconds
     * @author Elvira Grubb
     */

    public int getSeconds() {
        return seconds;
    }

    /**
     * This method is used to get the heart crystals.
     * @return int heartCrystals
     * @author Elvira Grubb
     */

    public int getHeartCrystals() {
        return heartCrystals;
    }

    /**
     * This method is used to get the world.
     * @return int world
     * @author Elvira Grubb
     */

    public int getWorld() {
        return world;
    }

    /**
     * This method is used to get the level.
     * @return int level
     * @author Elvira Grubb
     */

    public int getLevel() {
        return level;
    }

    /**
     * Returns the audio player.
     * @return AudioPlayer
     * @author Elvira Grubb
     */

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    /**
     * Returns the right panel.
     * @return RightPanel rightPanel
     * @author Elvira Grubb
     */

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    /**
     * Returns the mouse listener.
     * @return MouseListener mouseListener
     * @author Elvira Grubb
     */

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    /**
     * Returns the collectibles.
     * @return ArrayList<Label> collectibles
     * @author Elvira Grubb
     */

    public ArrayList<Label> getCollectibles() {
        return collectibles;
    }

    /**
     * Returns the pickaxes.
     * @return ArrayList<Label> pickaxes
     * @author Elvira Grubb
     */

    public ArrayList<Label> getPickaxes() {
        return pickaxes;
    }

    /**
     * Sets the time.
     * @param time
     * @author Wahid Hassani
     */

    public void setTime(TimeThread time) {
        this.time = time;
    }
}


