package Game.ButtonListeners;

import Game.Windows.ChoosePlayerWindow;
import Game.Windows.GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Main.changeWindowToGame;
import static Game.Main.setGameWindow;


/**
 * Created by Daniel on 26.04.2017.
 */
public class AcceptButtonListener implements ActionListener{
    ChoosePlayerWindow window;
    public  AcceptButtonListener(ChoosePlayerWindow window){
        this.window = window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setGameWindow(new GameWindow(860,640,window));
        changeWindowToGame();
    }
}
