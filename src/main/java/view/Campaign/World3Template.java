package view.Campaign;

import control.GameController;
import control.EnemyController;
import model.Enemy;

import java.io.FileNotFoundException;

public class World3Template extends WorldTemplate {


    public World3Template(){
        super();
    }
    public World3Template(int[][] levelArray, GameController gameController) throws FileNotFoundException {
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
        EnemyController enemyController = getGameController().getEnemyController();
        Enemy enemy;

        switch (getGameController().getLevel()) {
            case 1:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),125,251,65,-147);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),5,5);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),84, 123, 65, -147);
                enemyController.setAnimation(enemy, 4, false);
                add(enemy.getIcon(),14,5);

                break;

            case 2:

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),125, 120, 65, -147);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),10,2);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),210, 125, 190, -231);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),9,3);

                break;

            case 3:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),0, 83, 105, -232);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),10,1);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),0, 83, 105, -232);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),15,1);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),0, 83, 105, -232);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),12,1);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),125, 83, 190, -230);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),10,4);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),125, 83, 190, -230);
                enemyController.setAnimation(enemy, 3, true);
                add(enemy.getIcon(),15,4);
                break;

            case 4:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),83, 125, 65, -150);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),8,3);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),0, 125, 65, -150);
                enemyController.setAnimation(enemy, 1.5, true);
                add(enemy.getIcon(),8,8);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),400, 0, 20, -150);
                enemyController.setAnimation(enemy, 2.5, true);
                add(enemy.getIcon(),8,12);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),400, 0, 20, -150);
                enemyController.setAnimation(enemy, 3, true);
                add(enemy.getIcon(),8,11);

                break;

            case 5:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),83, 125, 65, -150);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),7,6);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),0, 40, 65, -150);
                enemyController.setAnimation(enemy, 1.5, true);
                add(enemy.getIcon(),8,2);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),170, 87, 20, 20);
                enemyController.setAnimation(enemy, 2.5, true);
                add(enemy.getIcon(),8,6);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("Ghost"),getSquareSize(),170, 87, 20, 20);
                enemyController.setAnimation(enemy, 3, true);
                add(enemy.getIcon(),8,10);

                break;

            default:
                break;
        }
    }
}