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
        System.out.println("Start game button pressed");
        Main.contentPane = Main.frame.getContentPane();
        Main.contentPane.removeAll();
        try {
            Main.contentPane.add(new ChoosePlayerWindow(860, 640));
            Main.frame.validate();
            Main.frame.repaint();
            Main.frame.setVisible(true);

        }
        catch(IOException exc){
            System.out.println("Something went wrong");
        }
    }
}
