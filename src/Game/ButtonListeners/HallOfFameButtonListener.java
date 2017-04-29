package Game.ButtonListeners;

import Game.HallOfFameWindow;
import Game.Main;
import Game.MenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 25.04.2017.
 */
public class HallOfFameButtonListener implements ActionListener{

    public HallOfFameButtonListener(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.changeWindow(new HallOfFameWindow(860,640));
    }
}
