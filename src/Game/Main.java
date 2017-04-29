package Game;

import Game.Windows.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Main {
    private static JFrame frame = new JFrame();
    private static Container contentPane;
    private static JPanel [] windows;

    public static void changeWindow(int n){
        contentPane = frame.getContentPane();
        contentPane.removeAll();
        contentPane.add(windows[n]);
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    public static void main(String [] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        windows = new JPanel[]{
                new MainWindow(860,640),
                new HallOfFameWindow(860,640),
                new MenuWindow(860,640),
                new ChoosePlayerWindow(860, 640),
                new GameWindow(860,640)

        };
        frame.setBounds((int)width/5,(int)(height/11),860,640);
        frame.setTitle("Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.add(windows[0]);
        frame.setVisible(true);
    }
}
