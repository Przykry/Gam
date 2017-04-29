package Game.ButtonListeners;

import Game.Main;
import Game.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Daniel on 26.04.2017.
 */
public class BackButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Main.changeWindow(new MainWindow(860,640));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
