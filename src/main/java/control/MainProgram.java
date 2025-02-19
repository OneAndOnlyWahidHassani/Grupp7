package control;

import LevelEditor.controller.LevelEditorController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import model.HighscoreList;
import model.Maps.*;
import model.MazeGeneration.GenerateNextLevel;
import view.AudioPlayer;
import view.GameOverScreen;
import view.Randomize.MapTemplate;
import model.MazeGeneration.MazeGenerator;
import view.Menu.*;
import view.WorldIntroAnimation;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author André Eklund
 * @edit Filip Örnling, Viktor Näslund, Sebastian Helin
 */

public class MainProgram extends Application {

    private Stage mainWindow;
    private BorderPane mainPaneRandomMaze;
    private BorderPane mainPaneCampaign;
    private MapTemplate mapTemplate;
    private Scene menuScene;
    private Scene introScene;
    private Scene helpScene;
    private Scene highscoreScene;
    private Scene chooseDimensionScene;
    private Scene selectMapScene;
    private Scene selectLevelScene;
    private Intro intro;
    private Menu menu;
    private Help help;
    private ChooseDimension chooseDimension;
    private SelectWorldMap selectWorldMap;
    private SelectLevel selectLevel;
    private HighscoreView highscoreView;
    private Scene randomScene;
    private Scene campaignScene;
    private RightPanel rightPanel;
    private RightPanel rightPnlRndm;
    private MazeGenerator mazeGenerator;
    private GenerateNextLevel generateNextLevel;
    private WorldIntroAnimation introAnimation;
    private AudioPlayer audioPlayer;
    private GameOverScreen gameOverScreen;
    private Image cursorImage;
    private GameController gameController;
    private HighscoreList highscoreList;
    private LevelEditorController levelEditorController;


    /**
     * En metod som startar programmet.
     * Metoden instanierar även de olika komponenterna.
     * @param primaryStage JavaFX top Container, huvudkomponenten till programmet.
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        audioPlayer = new AudioPlayer();
        audioPlayer.playIntroMusic();

        rightPanel = new RightPanel(this, "11", audioPlayer, null);
        rightPanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        menu = new Menu(this, audioPlayer, rightPanel);
        intro = new Intro(this, audioPlayer);
        help = new Help(this, audioPlayer);
        highscoreList = new HighscoreList();
        highscoreView = new HighscoreView(this, audioPlayer, 1, highscoreList);
        chooseDimension = new ChooseDimension(this, audioPlayer);
        selectWorldMap = new SelectWorldMap(this, audioPlayer);
        introScene = new Scene(intro, 800, 600);
        menuScene = new Scene(menu, 800, 600);
        helpScene = new Scene(help, 800, 600);
        selectMapScene = new Scene(selectWorldMap, 800, 600);
        chooseDimensionScene = new Scene(chooseDimension, 800, 600);
        highscoreScene = new Scene(highscoreView, 800, 600);

        mainPaneRandomMaze = new BorderPane();
        mainPaneCampaign = new BorderPane();
        introAnimation = new WorldIntroAnimation();

        mainWindow = primaryStage;
        cursorImage = new Image("file:files/imagecursor.png");

        mainWindow.setTitle("Mazegen");
        mainWindow.setResizable(false);
        mainWindow.setOnCloseRequest(windowEvent -> System.exit(0));
        //world1Maps = new World1Maps();
        mainPaneCampaign.setRight(rightPanel);

        rightPnlRndm = new RightPanel(this, "Random", audioPlayer, null);
        rightPnlRndm.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        mainPaneRandomMaze.setRight(rightPnlRndm);

        campaignScene = new Scene(mainPaneCampaign, 800, 600);
        randomScene = new Scene(mainPaneRandomMaze, 800, 600);

        mainWindow.setScene(introScene);
        mainWindow.show();

        introScene.setCursor(new ImageCursor(cursorImage));
        menuScene.setCursor(new ImageCursor(cursorImage));
        campaignScene.setCursor(new ImageCursor(cursorImage));
        chooseDimensionScene.setCursor(new ImageCursor(cursorImage));
        helpScene.setCursor(new ImageCursor(cursorImage));
        randomScene.setCursor(new ImageCursor(cursorImage));

        //Körs när användaren stänger programmet
        //Används för att spara Highscore listan
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    highscoreList.write();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
        });
    }

    /**
     * Byter scen till huvudmenyn.
     */
    public void changeToMenu()
    {
        mainWindow.setScene(menuScene);
    }

    /**
     * Byter scen till Randomize.
     * @param dimension Storleken på labyrinten som ska genereras.
     * @throws FileNotFoundException
     */
    public void changeToRandomize(int dimension) throws FileNotFoundException {
        mazeGenerator = new MazeGenerator(dimension, true);
        generateNextLevel = new GenerateNextLevel(this, mainPaneRandomMaze, mazeGenerator, rightPanel, dimension);
        mapTemplate = new MapTemplate(mazeGenerator.getMaze(), this, generateNextLevel);
        mainPaneRandomMaze.setCenter(mapTemplate);
        mainWindow.setScene(randomScene);
    }

    /**
     * Byter scen till kampanjläget.
     * @throws FileNotFoundException
     */
    public void changeToCampaign() {
        gameController = new GameController(this, rightPanel, audioPlayer, gameOverScreen, mainPaneCampaign, 1, 1);
        try {
            gameController.campaignWorldManager();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        mainWindow.setScene(campaignScene);
        gameController.setUpNewWorldAnimation();
    }

    public GameController getCampaignController(){
        return gameController;
    }

    public void changeToSpecifiedCampaign(int world, int level)
    {
        gameController = new GameController(this, rightPanel, audioPlayer, gameOverScreen, mainPaneCampaign, world, level);
        try {
            gameController.campaignWorldManager();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        mainWindow.setScene(campaignScene);
        gameController.setUpNewWorldAnimation();
    }
    /**
     * Byter scen till den del av menyn där användaren får välja dimension på labyrinten.
     */
    public void chooseDimension() {
        mainWindow.setScene(chooseDimensionScene);
    }

    public void selectWorldMap()
    {
        mainWindow.setScene(selectMapScene);
    }

    public void selectLevelMap(int map)
    {
        selectLevel = new SelectLevel(this, audioPlayer, map);
        selectLevelScene = new Scene(selectLevel, 800, 600);
        mainWindow.setScene(selectLevelScene);
    }

    /**
     * Byter scen till hjälpfönstret.
     */
    public void changeToHelp() {
        mainWindow.setScene(helpScene);
    }

    public void showHighscore() {
        mainWindow.setScene(highscoreScene);
    }


    /**
     * Kopierat från "changeToCampaign()"
     *
     */
    public void changeToLevelEditor() {
        levelEditorController = new LevelEditorController(this, rightPanel, audioPlayer, gameOverScreen, mainPaneCampaign, 1, 1);
        try {
            levelEditorController.campaignWorldManager();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        mainWindow.setScene(campaignScene);
        gameController.setUpNewWorldAnimation();
    }

    /**
     * Main startar programmet.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

