package Game.ButtonListeners;

import Game.Main;
import Game.Windows.GameWindow;
import Game.Windows.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Main.getGameWindow;
import static Game.Main.getWindow;
/**
 * Created by Daniel on 26.04.2017.
 */

/**
 *
 * Klasa implementuje Action Listenera który jest przypisany do przycisku wstecz w menu wyboru bohatera oraz w ekranie gry.
 */
public class BackButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        if(getGameWindow() != null) {
            GameWindow.getTimer().stop();
        }
        if(getWindow() == 3){
            GameWindow.stopEntities();
            Main.removeWindow();
            GameWindow.resetPoints();
        }
        Main.changeWindow(0);
        MainWindow.startBouncingBalls();
    }
}
