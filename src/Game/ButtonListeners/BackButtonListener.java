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
        Main.contentPane = Main.frame.getContentPane();
        Main.contentPane.removeAll();
        try {
            Main.contentPane.add(new MainWindow(860, 640));
            Main.frame.validate();
            Main.frame.repaint();
            Main.frame.setVisible(true);
        }
        catch(IOException exc){
            System.out.println("Something went wrong");
        }
    }
}
