package Game.ButtonListeners;

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
        System.out.println("Hall of fame button pressed");
    }
}
