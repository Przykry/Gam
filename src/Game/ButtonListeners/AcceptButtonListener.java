package Game.ButtonListeners;

import Game.Windows.ChoosePlayerWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Game.Main.changeWindow;



/**
 * Created by Daniel on 26.04.2017.
 */
public class AcceptButtonListener implements ActionListener{
    public  AcceptButtonListener(){
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        changeWindow(4);
    }
}
