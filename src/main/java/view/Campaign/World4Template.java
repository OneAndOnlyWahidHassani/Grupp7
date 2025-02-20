package view.Campaign;

import control.GameController;
import control.EnemyController;
import model.Enemy;

import java.io.FileNotFoundException;

/**
 * @author Filip Örnling
 */

public class World4Template extends WorldTemplate {
    public World4Template(){
        super();
    }
    public World4Template(int[][] levelArray, GameController gameController) throws FileNotFoundException {
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
        switch (getGameController().getLevel()){
            case 1:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),110, 150, 57, -130);
                enemyController.setAnimation(enemy, 3.5, false);
                add(enemy.getIcon(),15,9);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),110, 75, 57, -130);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),14,5);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),75, 75, 57, -130);
                enemyController.setAnimation(enemy, 2.5, true);
                add(enemy.getIcon(),5,7);

                break;

            case 2:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),115, 115, 18, 18);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),1,4);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),190, 190, 18, 18);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),5,9);

                break;

            case 3:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),570, 0, 20, 20);
                enemyController.setAnimation(enemy, 4, true);
                add(enemy.getIcon(),0,2);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),570, 0, 20, 20);
                enemyController.setAnimation(enemy, 4, true);
                add(enemy.getIcon(),0,4);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),570, 0, 20, 20);
                enemyController.setAnimation(enemy, 4, true);
                add(enemy.getIcon(),0,6);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),570, 0, 20, 20);
                enemyController.setAnimation(enemy, 4, true);
                add(enemy.getIcon(),0,8);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),570, 0, 20, 20);
                enemyController.setAnimation(enemy, 4, true);
                add(enemy.getIcon(),0,10);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),570, 0, 20, 20);
                enemyController.setAnimation(enemy, 4, true);
                add(enemy.getIcon(),0,12);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),570, 0, 20, 20);
                enemyController.setAnimation(enemy, 4, true);
                add(enemy.getIcon(),0,14);
                break;

            case 4:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),83, 125, 65,-150);
                enemyController.setAnimation(enemy, 2, true);
                add(enemy.getIcon(),8,2);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),0, 125, 65,-150);
                enemyController.setAnimation(enemy, 1.5, true);
                add(enemy.getIcon(),9,9);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),400, 0, 20,-150);
                enemyController.setAnimation(enemy, 2.5, true);
                add(enemy.getIcon(),8,12);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),400, 0, 20,-150);
                enemyController.setAnimation(enemy, 3, true);
                add(enemy.getIcon(),8,11);

                break;


            case 5:
                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),83, 125, -150, 65);
                enemyController.setAnimation(enemy, 2, false);
                add(enemy.getIcon(),6,6);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),0, 40, 65, -150);
                enemyController.setAnimation(enemy, 1.5, true);
                add(enemy.getIcon(),9,2);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),170, 87, 20, 20);
                enemyController.setAnimation(enemy, 2.5, true);
                add(enemy.getIcon(),9,7);

                enemy = enemyController.createEnemy(enemyController.getIconFilePath("God"),getSquareSize(),170, 87, 20, 20);
                enemyController.setAnimation(enemy, 3, true);
                add(enemy.getIcon(),8,11);

                break;

            default:
                break;
        }
    }
}
