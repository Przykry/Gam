package Game.ButtonListeners;

import Game.Main;
import Game.Windows.ChoosePlayerWindow;
import Game.Windows.GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Main.changeWindow;



/**
 * Created by Daniel on 26.04.2017.
 */
public class AcceptButtonListener implements ActionListener{
    private ChoosePlayerWindow window;
    public  AcceptButtonListener(ChoosePlayerWindow window){
        this.window = window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.addWindow(new GameWindow(860,640,window));
        changeWindow(3);
        GameWindow.getTimer().start();
        GameWindow.runEntities();
    }
}
