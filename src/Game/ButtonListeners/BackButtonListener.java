package Game.ButtonListeners;

import Game.Main;
import Game.Windows.GameWindow;
import Game.Windows.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Main.getGameWindow;

/**
 * Created by Daniel on 26.04.2017.
 */
public class BackButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        if(getGameWindow() != null) {
            GameWindow.getTimer().stop();
        }
        Main.changeWindow(0);
        MainWindow.startBouncingBalls();
    }
}
