package Game.ButtonListeners;

import Game.Main;
import Game.Windows.GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 26.04.2017.
 */
public class BackButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        if(Main.getGameWindow() != null) {
            GameWindow.getTimer().stop();
            Main.setGameWindow(null);
        }
        Main.changeWindow(0);
    }
}
