package model;

import control.EnemyController;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
/**
 * The Enemy class represents an enemy character in a game. It defines the enemy's movement paths,
 * icon (sprite), and animations. The class provides functionality to create the enemy with specific paths
 * and set up animations for the enemy's movements on screen.
 * @author Elvira Grubb
 */
public class Enemy {


    private Rectangle movementPath1;
    private Rectangle movementPath2;
    private EnemyController enemyController;
    private PathTransition animation;
    private ImageView icon;

    /**
     * Constructor to create an enemy with a single movement path.
     * Initializes the enemy with the given icon and movement path coordinates.
     *
     * @param enemyController The controller responsible for controlling the enemy.
     * @param iconFilePath The file path of the image used as the enemy's icon.
     * @param seize The size of the enemy's icon.
     * @param var1 The width of the first movement path.
     * @param var2 The height of the first movement path.
     * @param y The Y-coordinate of the first movement path.
     * @param x The X-coordinate of the first movement path.
     */
    public Enemy(EnemyController enemyController, String iconFilePath, int seize, double var1, double var2, int y, int x){
        this.enemyController = enemyController;
        setUpIcon(new Image(iconFilePath, seize, seize, false, false));
        setUpRectangle1(var1,var2,y,x);
    }
    /**
     * Constructor to create an enemy with two movement paths.
     * Initializes the enemy with the given icon and movement path coordinates.
     *
     * @param enemyController The controller responsible for controlling the enemy.
     * @param iconFilePath The file path of the image used as the enemy's icon.
     * @param seize The size of the enemy's icon.
     * @param var1 The width of the first movement path.
     * @param var2 The height of the first movement path.
     * @param y1 The Y-coordinate of the first movement path.
     * @param x1 The X-coordinate of the first movement path.
     * @param var3 The width of the second movement path.
     * @param var4 The height of the second movement path.
     * @param y2 The Y-coordinate of the second movement path.
     * @param x2 The X-coordinate of the second movement path.
     */
    public Enemy(EnemyController enemyController, String iconFilePath, int seize, double var1, double var2, int y1, int x1, double var3, double var4, int y2, int x2){
        this.enemyController = enemyController;
        setUpIcon(new Image(iconFilePath, seize, seize, false, false));
        setUpRectangle1(var1,var2,y1,x1);
        setUpRectangle2(var3,var4,y2,x2);
    }
    /**
     * Sets up the icon for the enemy by loading the image and adding a mouse event handler.
     *
     * @param image The Image object representing the enemy's icon.
     */
    private void setUpIcon(Image image){
        icon = new ImageView();
        icon.setImage(image);
        icon.setOnMouseEntered( e -> enemyController.getGameController().enteredGhost(e));
    }

    /**
     * Sets up the first movement path for the enemy using a rectangle.
     *
     * @param var1 The width of the first movement path.
     * @param var2 The height of the first movement path.
     * @param y The Y-coordinate of the first movement path.
     * @param x The X-coordinate of the first movement path.
     */
    private void setUpRectangle1(double var1, double var2, int y, int x) {
        movementPath1 = new Rectangle(var1, var2);
        movementPath1.setY(y);
        movementPath1.setX(x);
    }

    /**
     * Sets up the second movement path for the enemy using a rectangle.
     *
     * @param var1 The width of the second movement path.
     * @param var2 The height of the second movement path.
     * @param y The Y-coordinate of the second movement path.
     * @param x The X-coordinate of the second movement path.
     */
    private void setUpRectangle2(double var1, double var2, int y, int x) {
        movementPath2 = new Rectangle(var1, var2);
        movementPath2.setY(y);
        movementPath2.setX(x);
    }

    public void setAnimation(PathTransition animation) {
        this.animation = animation;
    }

    public PathTransition getAnimation() {
        return animation;
    }

    public Rectangle getMovementPath1() {
        return movementPath1;
    }

    public Rectangle getMovementPath2() {
        return movementPath2;
    }

    public ImageView getIcon() {
        return icon;
    }
}
