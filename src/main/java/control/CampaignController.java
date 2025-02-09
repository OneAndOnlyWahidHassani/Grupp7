package control;

import javafx.scene.layout.BorderPane;
import model.Maps.*;
import view.AudioPlayer;
import view.Campaign.*;
import view.GameOverScreen;
import view.Menu.RightPanel;
import view.WorldIntroAnimation;

import java.io.FileNotFoundException;

public class CampaignController {
    private MainProgram mainProgram;
    private WorldMaps worldMaps;
    private WorldTemplate worldTemplate;
    private RightPanel rightPanel;
    private AudioPlayer audioPlayer;
    private GameOverScreen gameOverScreen;
    private BorderPane mainPaneCampaign;
    private int world;
    private int level;
    private int heartCrystals;


    public CampaignController(MainProgram mainProgram, RightPanel rightPanel, AudioPlayer audioPlayer, GameOverScreen gameOverScreen, BorderPane mainPaneCampaign) {
        this.mainProgram = mainProgram;
        this.rightPanel = rightPanel;
        this.audioPlayer = audioPlayer;
        this.gameOverScreen = gameOverScreen;
        this.mainPaneCampaign = mainPaneCampaign;
        world = 6;
        level = 5;
        heartCrystals = 3;
    }

    public void gameOver() {
        gameOverScreen = new GameOverScreen(mainProgram);
        mainPaneCampaign.getChildren().add(gameOverScreen);
    }

    public void campaignLevelManager(int world, int level) {
        rightPanel.changeLevelCounter(String.valueOf(world * 10 + level));
        mainPaneCampaign.setCenter(worldTemplate);
        if(level == 1){
            setUpNewWorldAnimation();
        }
    }
    public void campaignWorldManager() throws FileNotFoundException, InterruptedException {
        worldMaps = new WorldMaps(world);
        worldTemplate = new WorldTemplate();
        switch (world) {
            case 1:
                worldMaps = new World1Maps(world);
                worldTemplate = new World1Template(setUpLevel(level), this);
                break;
            case 2:
                worldMaps = new World2Maps(world);
                worldTemplate = new World2Template(setUpLevel(level), this);
                break;
            case 3:
                worldMaps = new World3Maps(world);
                worldTemplate = new World3Template(setUpLevel(level), this);
                break;
            case 4:
                worldMaps = new World4Maps(world);
                worldTemplate = new World4Template(setUpLevel(level), this);
                break;
            case 5:
                worldMaps = new World5Maps(world);
                worldTemplate = new World5Template(setUpLevel(level), this);
                break;
            case 6:
                worldMaps = new World6Maps(world);
                worldTemplate = new World6Template(setUpLevel(level), this);
                break;

        }
        campaignLevelManager(world, level);
    }


    public int[][] setUpLevel(int level) throws FileNotFoundException, InterruptedException {
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

    public void nextLevel() throws FileNotFoundException, InterruptedException {
        if (level == 5) {
            world++;
            level = 1;
        }else {
            level++;
        }
        campaignWorldManager();
    }
    public void setUpNewWorldAnimation(){
        WorldIntroAnimation introAnimation = new WorldIntroAnimation(String.valueOf(getWorld()));
        mainPaneCampaign.getChildren().add(introAnimation);
        introAnimation.setDisable(true);
        audioPlayer.playWorldIntroSound();
        audioPlayer.playLevelMusic(getWorldTemplate().getWorldName(getWorld()));
        rightPanel.setTheTime(getWorldTemplate().getSeconds());
        rightPanel.resetTimerLabel();
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

    public WorldTemplate getWorldTemplate() {
        return worldTemplate;
    }

}


