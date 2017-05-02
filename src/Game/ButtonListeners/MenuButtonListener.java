package Game.ButtonListeners;

import Game.Windows.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Main.changeWindow;

/**
 * Created by Daniel on 25.04.2017.
 */
public class MenuButtonListener implements ActionListener {

    public MenuButtonListener(){

    }
    @Override
    public void actionPerformed(ActionEvent e){
        changeWindow(2);
        MainWindow.stopBouncingBalls();

    }
}
