package Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Main {
    public static JFrame frame = new JFrame();
    public static Container contentPane;
    public static void main(String [] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        JPanel mainWindow = null;
        frame.setBounds((int)width/5,(int)(height/11),860,640);
        frame.setTitle("Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        try {
            mainWindow = new MainWindow(860,640);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.add(mainWindow);
        frame.setVisible(true);
    }
}
