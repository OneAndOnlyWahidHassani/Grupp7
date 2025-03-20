package model;

import view.Menu.RightPanel;

/**
 * En tråd som räknar ner den
 * totala tiden som skickas in från konstruktorn
 */

public class TimeThread extends Thread {

    private int seconds;
    private RightPanel panel;
    private boolean gameOver = false;

    /**
     * @param seconds Varje nivå skickar in en seconds variabel till tråden
     * @param panel
     */
    public TimeThread(int seconds, RightPanel panel){
        this.seconds = seconds;
        this.panel = panel;
    }

    /**
     * Körs tills det blir GameOver eller en bana blir avklarad
     * Instansieras om och körs igen för varje nivå
     */
    public void run() {
        while (!gameOver) {
            try {
                Thread.sleep(1000);
                if (seconds > 0) {
                    seconds--;
                }
                if (seconds == 5){
                    panel.fiveSecLeft();
                }
                if (seconds ==0) {
                    panel.startTask();
                    gameOver = true;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param gameOver setter för trådens villkor
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
