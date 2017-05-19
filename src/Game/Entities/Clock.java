package Game.Entities;

import Game.Windows.GameWindow;

import static java.lang.Thread.sleep;

/**
 * Created by Daniel on 19.05.2017.
 */
public class Clock implements Runnable{
    GameWindow gameWindow;
    public Clock(GameWindow gameWindow){
        this.gameWindow = gameWindow;
    }

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameWindow.setTime(gameWindow.getTime()-1);
    }
}
