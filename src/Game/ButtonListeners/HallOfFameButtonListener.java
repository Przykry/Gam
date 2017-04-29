package Game.ButtonListeners;

import Game.HallOfFameWindow;
import Game.Main;
import Game.MenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Game.Main.changeWindow;

/**
 * Created by Daniel on 25.04.2017.
 */
public class HallOfFameButtonListener implements ActionListener{

    public HallOfFameButtonListener(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            changeWindow(new HallOfFameWindow(860,640));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
