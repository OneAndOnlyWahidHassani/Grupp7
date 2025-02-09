package view.Campaign;

import control.CampaignController;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.TimeThread;
import view.AudioPlayer;
import view.Menu.RightPanel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class World3Template extends WorldTemplate {


    /**
     * @author Filip Örnling
     */

    //private Image ghost;

    public World3Template(){
        super();
    }
    public World3Template(int[][] levelArray, CampaignController campaignController) throws FileNotFoundException {
        super(levelArray, campaignController, 60);
        initialize();
    }
    /**
     * Metoden initialize instansierar olika antal ImageView objekt beroende på vilken bana som spelas
     * Metoden initialize instansierar även olika antal rectanglar i olika former beroende på bana
     * Metoden kopplar sedan samman ImageView objekt och rectanglar för att skapa animationer
     * Animationerna kan gå i olika hastigheter
     */

    public void initialize() {
        setEnemy(new Enemy("file:files/ghost.png", getSquareSize(), this, 5));
        ArrayList<ImageView> ghosts = enemy.getGhosts();
        switch (campaignController.getLevel()) {
            case 1:
                add(ghosts.get(0), 5, 5);
                add(ghosts.get(1), 14, 5);

                enemy.setRectangle(125, 251, 65, -147, 0);
                enemy.setRectangle(84, 123, 65, -147, 1);

                enemy.setAnimation(0, 4, false);
                enemy.setAnimation(1, 4, false);
                break;

            case 2:
                add(ghosts.get(0),9,3);
                add(ghosts.get(1), 9, 3);

                enemy.setRectangle(125, 120, 65, -147, 0);
                enemy.setRectangle(210, 125, 190, -231, 1);

                enemy.setAnimation(0,  2, false);
                enemy.setAnimation(1,  2, false);
                break;

            case 3:

                add(ghosts.get(0),10,1);
                add(ghosts.get(1),15,1);
                add(ghosts.get(2),12,1);
                add(ghosts.get(3),10,4);
                add(ghosts.get(4),15,4);

                enemy.setRectangle(0, 83, 105, -232, 0);
                enemy.setRectangle(0, 83, 105, -232, 1);
                enemy.setRectangle(0, 83, 105, -232, 2);
                enemy.setRectangle(125, 83, 190, -230, 3);
                enemy.setRectangle(125, 83, 190, -230, 4);

                enemy.setAnimation(0, 2, true);
                enemy.setAnimation(1, 2, true);
                enemy.setAnimation(2, 2, true);
                enemy.setAnimation(3, 2, true);
                enemy.setAnimation(4, 3, true);
                break;

            case 4:

                add(ghosts.get(3), 8, 11);
                add(ghosts.get(0), 8, 3);
                add(ghosts.get(1), 8, 8);
                add(ghosts.get(2), 8, 12);

                enemy.setRectangle(83, 125, 65, -150, 0);
                enemy.setRectangle(0, 125, 65, -150, 1);
                enemy.setRectangle(400, 0, 20, -150, 2);
                enemy.setRectangle(400, 0, 20, -150, 3);

                enemy.setAnimation(3, 3, true);
                enemy.setAnimation(2, 2.5, true);
                enemy.setAnimation(1, 1.5, true);
                enemy.setAnimation(0, 2, true);
                break;

            case 5:

                add(ghosts.get(0),7,6);
                add(ghosts.get(1),8,2);
                add(ghosts.get(2),8,6);
                add(ghosts.get(3), 8,10);

                enemy.setRectangle(83, 125, 65, -150, 0);
                enemy.setRectangle(0, 40, 65, -150, 1);
                enemy.setRectangle(170, 87, 20, 20, 2);
                enemy.setRectangle(170, 87, 20, 20, 3);

                enemy.setAnimation(3, 3, true);
                enemy.setAnimation(2, 2.5, true);
                enemy.setAnimation(1, 1.5, true);
                enemy.setAnimation(0, 2, false);

                break;

            default:
                break;
        }
    }
}