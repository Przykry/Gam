package Game.ButtonListeners;

import Game.ChoosePlayerWindow;
import Game.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Daniel on 25.04.2017.
 */
public class StartGameButtonListener implements ActionListener{
    public StartGameButtonListener(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Main.changeWindow(new ChoosePlayerWindow(860,640));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
