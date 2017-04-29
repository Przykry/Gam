package Game.ButtonListeners;

import Game.Windows.ChoosePlayerWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Game.Main.changeWindow;

/**
 * Created by Daniel on 25.04.2017.
 */
public class StartGameButtonListener implements ActionListener{
    public StartGameButtonListener(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            changeWindow(new ChoosePlayerWindow(860,640));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
