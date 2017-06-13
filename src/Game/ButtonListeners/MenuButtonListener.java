package Game.ButtonListeners;

import Game.Windows.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Main.changeWindow;

/**
 * Created by Daniel on 25.04.2017.
 */

/**
 *
 * Klasa implementuje ActionListenera który jest dodany do przycisku w menu głównym który przechodzi do ekranu ustawień sterowania.
 */
public class MenuButtonListener implements ActionListener {

    public MenuButtonListener(){

    }
    @Override
    public void actionPerformed(ActionEvent e){
        changeWindow(1);
        MainWindow.stopBouncingBalls();

    }
}
