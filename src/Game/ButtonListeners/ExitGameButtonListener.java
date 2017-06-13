package Game.ButtonListeners;

import java.awt.event.*;

/**
 * Created by Daniel on 25.04.2017.
 * Klasa implementuje ActionListenera który jest przypisany do przycisku wyjścia z gry.
 */
public class ExitGameButtonListener implements ActionListener{

    public ExitGameButtonListener(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
