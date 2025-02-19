package LevelEditor.controller;

import control.EnemyController;
import control.GameController;
import control.MainProgram;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LevelEditorController {
    private MainProgram mainProgram;
    private GameController gameController;
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


    public LevelEditorController(MainProgram mainProgram, RightPanel rightPanel, AudioPlayer audioPlayer, GameOverScreen gameOverScreen, BorderPane mainPaneCampaign, int world, int level) {
        this.mainProgram = mainProgram;
        this.rightPanel = rightPanel;
        this.audioPlayer = audioPlayer;
        this.gameOverScreen = gameOverScreen;
        this.mainPaneCampaign = mainPaneCampaign;
        enemyController = new EnemyController(gameController);
        totTime = new TotalTime(false);
        time = null;
        this.world = world;
        this.level = level;
        heartCrystals = 3;
        this.gameController = new GameController(mainProgram, rightPanel, audioPlayer, gameOverScreen, mainPaneCampaign, world, level);
    }

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

    public void campaignLevelManager(int world, int level) {
        rightPanel.changeLevelCounter(String.valueOf(world * 10 + level));
        mainPaneCampaign.setCenter(worldTemplate);
        if(level == 1){
            setUpNewWorldAnimation();
        }
    }
    public void campaignWorldManager() throws FileNotFoundException, InterruptedException, IOException, ClassNotFoundException {
        worldMaps = new WorldMaps(world);
        worldTemplate = new WorldTemplate();
        switch (world) {
            case 1:
                worldMaps = new World1Maps(world);
                seconds = 25;
                worldTemplate = new World1Template(setUpLevel(level), gameController);
                break;
            case 2:
                worldMaps = new World2Maps(world);
                seconds = 35;
                worldTemplate = new World2Template(setUpLevel(level), gameController);
                break;
            case 3:
                worldMaps = new World3Maps(world);
                seconds = 60;
                worldTemplate = new World3Template(setUpLevel(level), gameController);
                break;
            case 4:
                worldMaps = new World4Maps(world);
                seconds = 80;
                worldTemplate = new World4Template(setUpLevel(level), gameController);
                break;
            case 5:
                worldMaps = new World5Maps(world);
                seconds = 90;
                worldTemplate = new World5Template(setUpLevel(level), gameController);
                break;
            case 6:
                worldMaps = new World6Maps(world);
                seconds = 99;
                worldTemplate = new World6Template(setUpLevel(level), gameController);
                break;

        }
        campaignLevelManager(world, level);
    }


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

    public void nextLevel() throws FileNotFoundException, InterruptedException, IOException, ClassNotFoundException {
        if (level >= 5) {
            world++;
            level = 1;
        }else {
            level++;
        }
        campaignWorldManager();
    }
    public void setUpNewWorldAnimation(){
        worldTemplate.setNewWorldAnimation(mainPaneCampaign, world);
    }

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

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getHeartCrystals() {
        return heartCrystals;
    }

    public int getWorld() {
        return world;
    }

    public int getLevel() {
        return level;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }


    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public ArrayList<Label> getCollectibles() {
        return collectibles;
    }

    public ArrayList<Label> getPickaxes() {
        return pickaxes;
    }
}


