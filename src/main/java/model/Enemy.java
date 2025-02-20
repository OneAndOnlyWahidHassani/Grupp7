package model;

import control.EnemyController;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Enemy {


    private Rectangle movementPath1;
    private Rectangle movementPath2;
    private EnemyController enemyController;
    private PathTransition animation;
    private ImageView icon;


    public Enemy(EnemyController enemyController, String iconFilePath, int seize, double var1, double var2, int y, int x){
        this.enemyController = enemyController;
        setUpIcon(new Image(iconFilePath, seize, seize, false, false));
        setUpRectangle1(var1,var2,y,x);
    }

    public Enemy(EnemyController enemyController, String iconFilePath, int seize, double var1, double var2, int y1, int x1, double var3, double var4, int y2, int x2){
        this.enemyController = enemyController;
        setUpIcon(new Image(iconFilePath, seize, seize, false, false));
        setUpRectangle1(var1,var2,y1,x1);
        setUpRectangle2(var3,var4,y2,x2);
    }

    private void setUpIcon(Image image){
        icon = new ImageView();
        icon.setImage(image);
        icon.setOnMouseEntered( e -> enemyController.getGameController().enteredGhost(e));
    }
    private void setUpRectangle1(double var1, double var2, int y, int x) {
        movementPath1 = new Rectangle(var1, var2);
        movementPath1.setY(y);
        movementPath1.setX(x);
    }
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
