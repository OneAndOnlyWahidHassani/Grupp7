package control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MazeGeneration.GenerateNextLevel;
import view.AudioPlayer;
import view.GameOverScreen;
import view.Randomize.MapTemplate;
import model.MazeGeneration.MazeGenerator;
import view.Menu.*;
import view.WorldIntroAnimation;


import java.io.FileNotFoundException;

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
    private Scene chooseDimensionScene;
    private Intro intro;
    private Menu menu;
    private Help help;
    private ChooseDimension chooseDimension;
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
    private CampaignController campaignController;


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
        chooseDimension = new ChooseDimension(this, audioPlayer);
        introScene = new Scene(intro, 800, 600);
        menuScene = new Scene(menu, 800, 600);
        helpScene = new Scene(help, 800, 600);
        chooseDimensionScene = new Scene(chooseDimension, 800, 600);

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
    }

    /**
     * Byter scen till huvudmenyn.
     */
    public void changeToMenu() {
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
        campaignController = new CampaignController(this, rightPanel, audioPlayer, gameOverScreen, mainPaneCampaign);
        try{
            campaignController.campaignWorldManager();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mainWindow.setScene(campaignScene);
        campaignController.setUpNewWorldAnimation();
    }


    public CampaignController getCampaignController(){
        return campaignController;
    }
    /**
     * Byter scen till den del av menyn där användaren får välja dimension på labyrinten.
     */
    public void chooseDimension() {
        mainWindow.setScene(chooseDimensionScene);
    }

    /**
     * Byter scen till hjälpfönstret.
     */
    public void changeToHelp() {
        mainWindow.setScene(helpScene);
    }


    /**
     * Main startar programmet.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
