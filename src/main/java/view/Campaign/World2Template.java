package view.Campaign;

import control.EnemyController;
import control.GameController;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import model.Enemy;

import java.io.FileNotFoundException;

/**
 * @author Sebastian Helin & Filip Örnling
 */


public class World2Template extends WorldTemplate {

    private PathTransition animation;
    private PathTransition animation2;
    private PathTransition animation3;
    private PathTransition animation4;
    private PathTransition animation5;
    private PathTransition animation6;

    public World2Template(){
        super();
    }
    public World2Template(int[][] levelArray, GameController gameController) throws FileNotFoundException {
        super(levelArray, gameController);
        initialize();
    }

    /**
     * Metoden initialize instansierar olika antal ImageView objekt beroende på vilken bana som spelas
     * Metoden initialize instansierar även olika antal rectanglar i olika former beroende på bana
     * Metoden kopplar sedan samman ImageView objekt och rectanglar för att skapa animationer
     * Animationerna kan gå i olika hastigheter
     */

    public void initialize() {
        Image ghost = new Image("file:files/ghost.png", getSquareSize(), getSquareSize(), false, false);
        if(getGameController().getLevel() == 5) {
            EnemyController enemyController = getGameController().getEnemyController();
            Enemy enemy;

            ImageView ghost1V = new ImageView(ghost);
            ImageView ghost2V = new ImageView(ghost);
            ImageView ghost3V = new ImageView(ghost);
            ImageView ghost4V = new ImageView(ghost);
            ImageView ghost5V = new ImageView(ghost);
            ImageView ghost6V = new ImageView(ghost);

            Label ghost1VLabel = new Label();
            Label ghost2VLabel = new Label();
            Label ghost3VLabel = new Label();
            Label ghost4VLabel = new Label();
            Label ghost5VLabel = new Label();
            Label ghost6VLabel = new Label();

            ghost1VLabel.setGraphic(ghost1V);
            ghost2VLabel.setGraphic(ghost2V);
            ghost3VLabel.setGraphic(ghost3V);
            ghost4VLabel.setGraphic(ghost4V);
            ghost5VLabel.setGraphic(ghost5V);
            ghost6VLabel.setGraphic(ghost6V);

            ghost1V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost2V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost3V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost4V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost5V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost6V.setOnMouseEntered(e -> getGameController().enteredGhost(e));

            add(ghost1V, 10, 0);
            add(ghost2V, 9, 0);
            add(ghost3V, 8, 0);
            add(ghost4V, 1, 0);
            add(ghost5V, 2, 0);
            add(ghost6V, 3, 0);

            Polyline line1 = new Polyline();
            Polyline line2 = new Polyline();
            Polyline line3 = new Polyline();
            Polyline line4 = new Polyline();
            Polyline line5 = new Polyline();
            Polyline line6 = new Polyline();

            line1.getPoints().addAll(
                    16.0, -10.5,
                    10.5, 650.0);
            line2.getPoints().addAll(
                    15.0, -10.5,
                    10.5, 650.0);
            line3.getPoints().addAll(
                    14.0, -10.5,
                    10.5, 650.0);
            line4.getPoints().addAll(
                    16.0, -10.5,
                    10.5, 650.0);
            line5.getPoints().addAll(
                    15.0, -10.5,
                    10.5, 650.0);
            line6.getPoints().addAll(
                    14.0, -10.5,
                    10.5, 650.0);


            animation3 = new PathTransition();
            animation3.setNode(ghost3V);
            animation3.setDuration(Duration.seconds(4));
            animation3.setCycleCount(Animation.INDEFINITE);
            animation3.setPath(line3);
            animation3.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(3));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(line2);
            animation2.play();

            animation = new PathTransition();
            animation.setNode(ghost1V);
            animation.setDuration(Duration.seconds(3.5));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(line1);
            animation.play();

            animation4 = new PathTransition();
            animation4.setNode(ghost4V);
            animation4.setDuration(Duration.seconds(3.5));
            animation4.setCycleCount(Animation.INDEFINITE);
            animation4.setPath(line4);
            animation4.play();

            animation5 = new PathTransition();
            animation5.setNode(ghost5V);
            animation5.setDuration(Duration.seconds(3));
            animation5.setCycleCount(Animation.INDEFINITE);
            animation5.setPath(line5);
            animation5.play();

            animation6 = new PathTransition();
            animation6.setNode(ghost6V);
            animation6.setDuration(Duration.seconds(4));
            animation6.setCycleCount(Animation.INDEFINITE);
            animation6.setPath(line6);
            animation6.play();

            Polyline line = new Polyline();
            line.getPoints().addAll(
                    -100.0, -50.0,
                    -50.0, 100.0,
                    100.0, 200.0,
                    200.0, -150.0);
            animation = new PathTransition();
            animation.setNode(new ImageView());
            animation.setDuration(Duration.seconds(10));
            animation.setPath(line);
            animation.setCycleCount(PathTransition.INDEFINITE);
            animation.play();

            ghost1V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost2V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost3V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost4V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost5V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
            ghost6V.setOnMouseEntered(e -> getGameController().enteredGhost(e));
        }
    }
}
