package Game.ButtonListeners;

import Game.ChoosePlayerWindow;
import Game.Main;
import Game.MenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Game.Main.changeWindow;

/**
 * Created by Daniel on 25.04.2017.
 */
public class MenuButtonListener implements ActionListener {

    public MenuButtonListener(){

    }
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            changeWindow(new MenuWindow(860,640));
        }
        catch (IOException exp){
            exp.printStackTrace();
        }

    }
}
