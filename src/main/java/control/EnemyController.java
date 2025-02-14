package control;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import model.Enemy;

public class EnemyController {
    private GameController gameController;
    private PathTransition animation;

    public EnemyController(){}

    public EnemyController(GameController gameController){
        this.gameController = gameController;
    }

    public void setAnimation(Enemy enemy, double duration, boolean reverse) {
        animation = new PathTransition();
        animation.setNode(enemy.getIcon());
        animation.setDuration(Duration.seconds(duration));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setPath(enemy.getMovementPath1());
        animation.setAutoReverse(reverse);
        animation.play();
    }

    public void setAnimation(Enemy enemy, double firstDuration, double secondDuration, int repetition, boolean reverse){
        animation = new PathTransition();
        animation.setNode(enemy.getIcon());
        animation.setDuration(Duration.seconds(firstDuration));
        animation.setCycleCount(repetition);
        animation.setPath(enemy.getMovementPath1());
        animation.setAutoReverse(reverse);
        enemy.setAnimation(animation);
        enemy.getAnimation().play();
        enemy.getAnimation().setOnFinished(actionEvent -> {
            enemy.getAnimation().setDuration(Duration.seconds(secondDuration));
            enemy.getAnimation().setCycleCount(Animation.INDEFINITE);
            enemy.getAnimation().play();
        });
    }

    public void setAnimation(Enemy enemy, double firstDuration, double secondDuration, int firstRepetition, int secondRepetition, boolean reverse){
        animation = new PathTransition();
        animation.setNode(enemy.getIcon());
        animation.setDuration(Duration.seconds(firstDuration));
        animation.setCycleCount(firstRepetition);
        animation.setPath(enemy.getMovementPath1());
        animation.setAutoReverse(reverse);
        enemy.setAnimation(animation);
        enemy.getAnimation().play();
        enemy.getAnimation().setOnFinished(actionEvent -> {
            enemy.getAnimation().setDuration(Duration.seconds(secondDuration));
            enemy.getAnimation().setCycleCount(secondRepetition);
            enemy.getAnimation().setPath(enemy.getMovementPath2());
            enemy.getAnimation().play();
            enemy.getAnimation().setOnFinished(actionEvent1 -> {
                enemy.getAnimation().setCycleCount(Animation.INDEFINITE);
                enemy.getAnimation().setPath(enemy.getMovementPath1());
                enemy.getAnimation().play();
            });
        });
    }

    public Enemy createEnemy(String iconFilePath, int seize, double var1, double var2, int y, int x){
        Enemy enemy = new Enemy(this, iconFilePath, seize, var1, var2, y, x);
        return enemy;
    }

    public Enemy createEnemy(String iconFilePath, int seize, double var1, double var2, int y1, int x1,double var3, double var4, int y2, int x2){
        Enemy enemy = new Enemy(this, iconFilePath, seize, var1, var2, y1, x1, var3, var4, y2, x2);
        return enemy;
    }

    public String getIconFilePath(String icon){
        switch (icon){
            case "Ghost":
                return "file:files/ghost.png";
            case "God":
                return "file:files/god_mob2.png";
            case "Egypt":
                return "file:files/mob_egypt.png";
            case "Space":
                return "file:files/space_mob3.png";
            default:
                return "";
        }
    }

    public GameController getGameController() {
        return gameController;
    }
}
