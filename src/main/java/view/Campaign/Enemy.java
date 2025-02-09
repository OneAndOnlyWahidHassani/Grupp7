package view.Campaign;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Enemy {
    private ArrayList<ImageView> ghosts;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<PathTransition> animations;
    private WorldTemplate worldTemplate;
    PathTransition animation;

    public Enemy(){}

    public Enemy(String enemyIconFilePath, int squareSize, WorldTemplate worldTemplate, int nbrOfGhosts){
        this.worldTemplate = worldTemplate;
        Image enemyIcon = new Image(enemyIconFilePath, squareSize, squareSize, false, false);
        setUpGhosts(nbrOfGhosts, enemyIcon);
        setUpAnimations(nbrOfGhosts);
        setUpRectangle(nbrOfGhosts);
    }

    public void setUpGhosts(int nbrOfGhosts, Image icon){
        ghosts = new ArrayList<>();
        for(int i = 0; i < nbrOfGhosts; i++){
            ImageView ghost = new ImageView();
            ghost.setImage(icon);
            ghost.setOnMouseEntered(e -> worldTemplate.enteredGhost(e));
            ghosts.add(ghost);
        }
    }
    public void setUpAnimations(int nbrOfAnimations){
        animations = new ArrayList<>();
        for (int i = 0; i < nbrOfAnimations; i++){
            animations.add(new PathTransition());
        }
    }

    public void setUpRectangle(int nbrOfRectangle){
        rectangles = new ArrayList<>();
        for(int i = 0; i < nbrOfRectangle; i++){
            rectangles.add(new Rectangle());
        }
    }

    public ArrayList<ImageView> getGhosts() {
        return ghosts;
    }

    public ArrayList<PathTransition> getAnimations() {
        return animations;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public void setAnimation(int index, double duration, boolean reverse) {
        animation = new PathTransition();
        animation.setNode(ghosts.get(index));
        animation.setDuration(Duration.seconds(duration));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setPath(rectangles.get(index));
        animation.setAutoReverse(reverse);

        animations.set(index, animation);
        animations.get(index).play();
    }
    public void setAnimation(int index, double duration, boolean reverse, double newDuration){
        animation = new PathTransition();
        animation.setNode(ghosts.get(index));
        animation.setDuration(Duration.seconds(duration));
        animation.setCycleCount(1);
        animation.setPath(rectangles.get(index));
        animation.setAutoReverse(reverse);
        animations.set(index, animation);
        animations.get(index).play();

        animations.get(index).setOnFinished(actionEvent -> {
            animations.get(index).setDuration(Duration.seconds(newDuration));
            animations.get(index).setCycleCount(Animation.INDEFINITE);
            animations.get(index).play();
        });
    }
    public void setAnimation(int index, double duration, boolean reverse, int repetition, int secondRepetition){
        animation = new PathTransition();
        animation.setNode(ghosts.get(index));
        animation.setDuration(Duration.seconds(duration));
        animation.setCycleCount(repetition);
        animation.setPath(getRectangles().get(0));
        animations.set(index, animation);
        animations.get(index).play();

        animations.get(index).setOnFinished(actionEvent -> {
            animations.get(index).setCycleCount(secondRepetition);
            animations.get(index).setAutoReverse(reverse);
            animations.get(index).setPath(getRectangles().get(1));
            animations.get(index).play();
            animations.get(index).setOnFinished(actionEvent1 -> {
                animations.get(index).setCycleCount(Animation.INDEFINITE);
                animations.get(index).setAutoReverse(reverse);
                animations.get(index).setPath(getRectangles().get(0));
                animations.get(index).play();
            });
        });
    }

    public void setRectangle( double var1, double var2, int y, int x, int index) {
        Rectangle rectangle = new Rectangle(var1, var2);
        rectangle.setY(y);
        rectangle.setX(x);
        rectangles.set(index, rectangle);
    }
}
