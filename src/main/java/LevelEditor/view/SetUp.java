package LevelEditor.view;

import LevelEditor.controller.LevelEditorController;
import LevelEditor.controller.MainLE;
import control.MainProgram;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import view.AudioPlayer;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SetUp extends Pane {

    private MainProgram mainProgram;
    private MainLE mainLE;
    private Image titel;
    private Image returnImage;
    private Image selectImage;
    private AudioPlayer audioPlayer;
    private TextField levelNameField;

    
    private Image dRButton;
    private Image dLButton;

    private Image[] dimensions;
    private int currentDimensionIndex = 0;
    private ImageView dimensionView;

    private Image RightButton;
    private Image LeftButton;

    private Image[] themes;
    private int currentThemeIndex = 0;
    private ImageView themeView;


    /**
     * Konstruktor som tar emot mainProgram och audioPlayer och kör några metoder för att
     * sätta bilder och knappar
     *
     * @param mainProgram tas emot och instansvariabeln sätts
     * @param audioPlayer tas emot och instansvariabeln sätts
     */
    public SetUp(MainProgram mainProgram, AudioPlayer audioPlayer) {
        this.mainProgram = mainProgram;
        this.audioPlayer = audioPlayer;
        this.mainLE = new MainLE();
        setBackground();
        setupImages();
        addButtons();
        addTextField();
        addTitles();
    }

    /**
     * Metod som länkar Image-objekten till png-filer
     */
    public void setupImages() {
        titel = new Image("file:files/texts/lvl.png", 300, 80, false, true);

        dRButton = new Image("file:files/texts/RB.png", 430, 300, false, false);
        dLButton = new Image("file:files/texts/LB.png", 430, 300, false, false);


        dimensions = new Image[]{
                new Image("file:files/texts/10x10.png", 250, 50, false, false),
                new Image("file:files/texts/14x14.png", 250, 50, false, false),
                new Image("file:files/texts/18x18.png", 250, 50, false, false)
        };


        RightButton = new Image("file:files/texts/RB.png", 430, 300, false, false);
        LeftButton = new Image("file:files/texts/LB.png", 430, 300, false, false);

        themes = new Image[]{
                new Image("file:files/theme/cloud.png", 125, 82, false, false),
                new Image("file:files/theme/desert.png", 125, 82, false, false),
                new Image("file:files/theme/forest.png", 115, 110, false, false),
                new Image("file:files/theme/lava.png", 115, 100, false, false),
                new Image("file:files/theme/space.png", 140, 90, false, false),
                new Image("file:files/theme/underground.png", 125, 100, false, false)
        };


        returnImage = new Image("file:files/texts/return.png", 250, 30, false, false);
        selectImage = new Image("file:files/texts/Select.png", 250, 30, false, false);
    }

    /**
     * Metod som sätter bakgrundsbilden
     */
    public void setBackground() {
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg", 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));

    }


    /**
     * Metod som placerar bilderna som klickbara ImageViews i scenen med events för knapptryck och hovering.
     * Bilderna förstoras när man hovrar över dem och scenen byts när man trycker på dem.
     */
    public void addButtons() {

        ImageView titelImageView = new ImageView(titel);
        titelImageView.setStyle("fx-background-color: transparent;");
        titelImageView.setTranslateX(250);
        titelImageView.setTranslateY(50);

        ImageView rDButtonView = new ImageView(dRButton);
        rDButtonView.setId("RightButtonDimension");
        rDButtonView.setStyle("fx-background-color: transparent;");
        rDButtonView.setTranslateX(350);
        rDButtonView.setTranslateY(220);
        rDButtonView.toFront();
        rDButtonView.setOnMouseEntered(e -> {
            rDButtonView.setImage(dRButton);
            rDButtonView.setTranslateX(350);
            rDButtonView.setTranslateY(220);
        });
        rDButtonView.setOnMouseExited(e -> {
            rDButtonView.setImage(dRButton);
            rDButtonView.setTranslateX(350);
            rDButtonView.setTranslateY(220);
        });
        rDButtonView.setOnMouseClicked(e -> {
            nextDimension();
            audioPlayer.playButtonSound();

        });


        ImageView lDButtonView = new ImageView(dLButton);
        lDButtonView.setId("LeftButtonDimension");
        lDButtonView.setStyle("fx-background-color: transparent;");
        lDButtonView.setTranslateX(50);
        lDButtonView.setTranslateY(220);
        lDButtonView.toFront();
        lDButtonView.setOnMouseEntered(e -> {
            lDButtonView.setImage(dLButton);
            lDButtonView.setTranslateX(50);
            lDButtonView.setTranslateY(220);
        });
        lDButtonView.setOnMouseExited(e -> {
            lDButtonView.setImage(dLButton);
            lDButtonView.setTranslateX(50);
            lDButtonView.setTranslateY(220);
        });
        lDButtonView.setOnMouseClicked(e -> {
            previousDimension();
            audioPlayer.playButtonSound();

        });


        dimensionView = new ImageView(dimensions[currentDimensionIndex]);
        dimensionView.setStyle("fx-background-color: transparent;");
        dimensionView.setTranslateX(270);
        dimensionView.setTranslateY(270);
        dimensionView.toFront();
        dimensionView.setPickOnBounds(true);


        ImageView RButtonView = new ImageView(RightButton);
        RButtonView.setId("RightButtonThem");
        RButtonView.setStyle("fx-background-color: transparent;");
        RButtonView.setTranslateX(350);
        RButtonView.setTranslateY(350);
        RButtonView.toFront();
        RButtonView.setPickOnBounds(true);
        RButtonView.setOnMouseEntered(e -> {
            RButtonView.setImage(RightButton);
            RButtonView.setTranslateX(350);
            RButtonView.setTranslateY(350);
        });
        RButtonView.setOnMouseExited(e -> {
            RButtonView.setImage(RightButton);
            RButtonView.setTranslateX(350);
            RButtonView.setTranslateY(350);
        });
        RButtonView.setOnMouseClicked(e -> {
            nextTheme();
            audioPlayer.playButtonSound();

        });


        ImageView LButtonView = new ImageView(LeftButton);
        LButtonView.setId("LeftButtonThem");
        LButtonView.setStyle("fx-background-color: transparent;");
        LButtonView.setTranslateX(50);
        LButtonView.setTranslateY(350);
        LButtonView.toFront();
        LButtonView.setPickOnBounds(true);
        LButtonView.setOnMouseEntered(e -> {
            LButtonView.setImage(LeftButton);
            LButtonView.setTranslateX(50);
            LButtonView.setTranslateY(350);
        });
        LButtonView.setOnMouseExited(e -> {
            LButtonView.setImage(LeftButton);
            LButtonView.setTranslateX(50);
            LButtonView.setTranslateY(350);
        });
        LButtonView.setOnMouseClicked(e -> {
            previousTheme();
            audioPlayer.playButtonSound();

        });


        themeView = new ImageView(themes[currentThemeIndex]);
        themeView.setStyle("fx-background-color: transparent;");
        themeView.setTranslateX(350);
        themeView.setTranslateY(400);
        themeView.toFront();
        themeView.setPickOnBounds(true);


        ImageView returnView = new ImageView(returnImage);
        returnView.setStyle("fx-background-color: transparent;");
        returnView.setTranslateX(5);
        returnView.setTranslateY(550);
        returnView.toFront();
        returnView.setPickOnBounds(true);
        returnView.setOnMouseEntered(e -> {
            returnView.setImage(returnImage);
            returnView.setTranslateX(5);
            returnView.setTranslateY(550);
        });
        returnView.setOnMouseExited(e -> {
            returnView.setImage(returnImage);
            returnView.setTranslateX(5);
            returnView.setTranslateY(550);
        });
        returnView.setOnMouseClicked(e -> {
            mainProgram.changeToLevelEditorMenu();
            audioPlayer.playButtonSound();
        });

        ImageView selectView = new ImageView(selectImage);
        selectView.setId("selectButtonLevelEditor");
        selectView.setStyle("fx-background-color: transparent;");
        selectView.setTranslateX(550);
        selectView.setTranslateY(550);
        selectView.toFront();
        selectView.setPickOnBounds(true);
        selectView.setOnMouseEntered(e -> {
            selectView.setImage(selectImage);
            selectView.setTranslateX(550);
            selectView.setTranslateY(550);
        });
        selectView.setOnMouseExited(e -> {
            selectView.setImage(selectImage);
            selectView.setTranslateX(550);
            selectView.setTranslateY(550);
        });
        selectView.setOnMouseClicked(e -> {
            String levelName = levelNameField.getText().trim();
            if (levelName.isEmpty()) {
                System.out.println("Vänligen ange ett nivånamn innan du sparar.");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vänligen ange ett nivånamn innan du sparar.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // Kontrollera om filen redan finns i mappen "createdLevels"
            File createdLevelsFolder = new File("createdLevels");
            File levelFile = new File(createdLevelsFolder, levelName + ".dat");

            if (levelFile.exists()) {
                System.out.println("Detta nivånamn finns redan. Välj ett annat.");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Detta nivånamn finns redan. Välj ett annat.", ButtonType.OK);
                alert.showAndWait();
                return;
            }


            String selectedTheme = themes[currentThemeIndex].getUrl().replace("file:files/theme/", "").replace(".png", "");
            String selectedDimension = dimensions[currentDimensionIndex].getUrl().replace("file:files/texts/", "").replace(".png", "");

            String selectedDimensionPrefix = selectedDimension.substring(0, 2); // Hämtar de två första bokstäverna
            int selectedDimensionInt = 0;
            selectedDimensionInt = Integer.parseInt(selectedDimensionPrefix);
            System.out.println(selectedDimensionInt);

            mainLE.saveLevel(levelName, selectedTheme, selectedDimension);
            int themeInt = mainLE.getThemeInt();


            try {
                mainProgram.enterLevelEditor(selectedDimensionInt, themeInt);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            audioPlayer.playButtonSound();
        });




        getChildren().addAll(titelImageView, dimensionView, rDButtonView, lDButtonView, RButtonView, themeView, LButtonView, returnView, selectView);
    }

    private void nextDimension() {
        currentDimensionIndex = (currentDimensionIndex + 1) % dimensions.length;
        dimensionView.setImage(dimensions[currentDimensionIndex]);
    }

    private void previousDimension() {
        currentDimensionIndex = (currentDimensionIndex - 1 + dimensions.length) % dimensions.length;
        dimensionView.setImage(dimensions[currentDimensionIndex]);
    }


    private void nextTheme() {
        currentThemeIndex = (currentThemeIndex + 1) % themes.length;
        themeView.setImage(themes[currentThemeIndex]);
    }

    private void previousTheme() {
        currentThemeIndex = (currentThemeIndex - 1 + themes.length) % themes.length;
        themeView.setImage(themes[currentThemeIndex]);
    }


    public void addTextField() {
        try {
            Font customFont = Font.loadFont(new FileInputStream("files/fonts/PressStart2P.ttf"), 20);

            levelNameField = new TextField();
            levelNameField.setId("textFieldLevelEditor");
            levelNameField.setFont(customFont);
            levelNameField.setPromptText("LEVEL NAME...");
            levelNameField.setTranslateX(250);
            levelNameField.setTranslateY(150);
            levelNameField.setPrefWidth(300);
            levelNameField.setStyle(
                    "-fx-background-color: white; " +
                            "-fx-text-fill: blue; " +
                            "-fx-font-weight: bold; " +
                            "-fx-border-color: black; " +
                            "-fx-border-width: 2px; " +
                            "-fx-prompt-text-fill: blue;"
            );
            levelNameField.setFocusTraversable(false);
            getChildren().add(levelNameField);
        } catch (FileNotFoundException e) {
            System.out.println("Fontfilen hittades inte!");
        }
    }

    public void addTitles() {
        try {
            Font customFont = Font.loadFont(new FileInputStream("files/fonts/PressStart2P.ttf"), 20);

            Text title2 = new Text("CHOOSE DIMENSION");
            title2.setFont(customFont);
            title2.setFill(javafx.scene.paint.Color.RED);
            title2.setTranslateX(250);
            title2.setTranslateY(250);

            Text title1 = new Text("CHOOSE THEME");
            title1.setFont(customFont);
            title1.setFill(javafx.scene.paint.Color.RED);
            title1.setTranslateX(280);
            title1.setTranslateY(380);

            getChildren().addAll(title1, title2);

        } catch (FileNotFoundException e) {
            System.out.println("Fontfilen hittades inte!");
        }
    }


}
