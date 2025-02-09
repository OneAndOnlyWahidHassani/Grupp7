package view.Campaign;

import control.CampaignController;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Filip Örnling
 */

public class World4Template extends WorldTemplate {

    public World4Template(){
        super();
    }
    public World4Template(int[][] levelArray, CampaignController campaignController) throws FileNotFoundException {
        super(levelArray, campaignController, 80);

        initialize();
    }
    /**
     * Metoden initialize instansierar olika antal ImageView objekt beroende på vilken bana som spelas
     * Metoden initialize instansierar även olika antal rectanglar i olika former beroende på bana
     * Metoden kopplar sedan samman ImageView objekt och rectanglar för att skapa animationer
     * Animationerna kan gå i olika hastigheter
     */

    public void initialize() {
        setEnemy(new Enemy("file:files/god_mob2.png", getSquareSize(), this, 7));
        ArrayList<ImageView> ghosts = enemy.getGhosts();
        switch (campaignController.getLevel()){
            case 1:
                add(ghosts.get(0), 15, 9);
                add(ghosts.get(1), 14, 5);
                add(ghosts.get(2), 5, 7);

                enemy.setRectangle(110, 150, 57, -130, 0);
                enemy.setRectangle(110, 75, 57, -130, 1);
                enemy.setRectangle(75, 75, 57, -130, 2);



                enemy.setAnimation(0, 3.5, false);
                enemy.setAnimation(1, 2, false);
                enemy.setAnimation(2, 2.5, true);
                break;

            case 2:

                add(ghosts.get(0), 1, 4);
                add(ghosts.get(1), 5, 9);

                enemy.setRectangle(115, 115, 18, 18, 0);
                enemy.setRectangle(190, 190, 18, 18, 1);

                enemy.setAnimation(0, 2, true);
                enemy.setAnimation(1, 2, false);
                break;

            case 3:
                add(ghosts.get(0), 0,2);
                add(ghosts.get(1), 0,4);
                add(ghosts.get(2), 0,4);
                add(ghosts.get(3), 0,8);
                add(ghosts.get(4), 0,10);
                add(ghosts.get(5), 0,12);
                add(ghosts.get(6), 0,14);

                enemy.setRectangle(570, 0, 20, 20, 0);
                enemy.setRectangle(570, 0, 20, 20, 1);
                enemy.setRectangle(570, 0, 20, 20, 2);
                enemy.setRectangle(570, 0, 20, 20, 3);
                enemy.setRectangle(570, 0, 20, 20, 4);
                enemy.setRectangle(570, 0, 20, 20, 5);
                enemy.setRectangle(570, 0, 20, 20, 6);

                enemy.setAnimation(0, 4, true);
                enemy.setAnimation(1, 4, true);
                enemy.setAnimation(2, 4, true);
                enemy.setAnimation(3, 4, true);
                enemy.setAnimation(4, 4, true);
                enemy.setAnimation(5, 4, true);
                enemy.setAnimation(6, 4, true);
                break;

            case 4:
                add(ghosts.get(0), 8, 2);
                add(ghosts.get(1), 9, 9);
                add(ghosts.get(2), 8, 12);
                add(ghosts.get(3), 8, 11);

                enemy.setRectangle(83, 125, 65,-150, 0);
                enemy.setRectangle(0, 125, 65,-150, 1);
                enemy.setRectangle(400, 0, 20,-150, 2);
                enemy.setRectangle(400, 0, 20,-150, 3);

                enemy.setAnimation(0, 2, true);
                enemy.setAnimation(1, 1.5, true);
                enemy.setAnimation(2, 2.5, true);
                enemy.setAnimation(3, 3, true);
                break;


            case 5:
                add(ghosts.get(0),6,6);
                add(ghosts.get(1),9,2);
                add(ghosts.get(2),9,7);
                add(ghosts.get(3),8,11);

                enemy.setRectangle(83, 125, -150, 65, 0);
                enemy.setRectangle(0, 40, 65, -150, 1);
                enemy.setRectangle(170, 87, 20, 20, 2);
                enemy.setRectangle(170, 87, 20, 20, 3);

                enemy.setAnimation(0,2, false);
                enemy.setAnimation(1,1.5,true);
                enemy.setAnimation(2,2.5,true);
                enemy.setAnimation(3,3,true);
                break;

            default:
                break;
        }
    }
}
