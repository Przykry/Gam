package Game;

import Game.Windows.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Main {
    private static JFrame frame = new JFrame();
    private static Container contentPane;

    private static ArrayList<JPanel> windows;

    private static GameWindow gameWindow;

    public static JPanel getPanel(int i){
        return windows.get(i);
    }

    public static GameWindow getGameWindow() {
        return gameWindow;
    }

    public static void setGameWindow(GameWindow gameWindow) {
        Main.gameWindow = gameWindow;
    }

    public static void changeWindow(int n){
        contentPane = frame.getContentPane();
        contentPane.removeAll();
        contentPane.add(windows.get(n));
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    public static void changeWindowToGame(){
        contentPane = frame.getContentPane();
        contentPane.removeAll();
        contentPane.add(gameWindow);
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    public static void main(String [] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        windows = new ArrayList();
        windows.add(new MainWindow(860,640));
        windows.add(new HallOfFameWindow(860,640));
        windows.add(new MenuWindow(860,640));
        windows.add(new ChoosePlayerWindow(860,640));
        //windows.add(new GameWindow(860,640));
        frame.setBounds((int)width/5,(int)(height/11),860,640);
        frame.setTitle("Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.add(windows.get(0));
        frame.setVisible(true);
    }
}
