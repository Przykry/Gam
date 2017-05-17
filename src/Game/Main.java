package Game;

import Game.ButtonListeners.ButtonListeners.Menu.MenuKeyListener;
import Game.ButtonListeners.PlayerMoveListener;
import Game.Windows.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Main {
    private static JFrame frame = new JFrame();
    private static Container contentPane;
    private static ArrayList<JPanel> windows;
    private static GameWindow gameWindow = null;
    static int n = 0;


    public static void setGameWindow(GameWindow gameWindow) {
        Main.gameWindow = gameWindow;
    }
    public static GameWindow getGameWindow() {
        return gameWindow;
    }
    public static int getWindow(){return n;}
    public static void changeWindow(int n){
        Main.n = n;
        for(KeyListener kL : frame.getKeyListeners()) frame.removeKeyListener(kL);
        contentPane = frame.getContentPane();
        contentPane.removeAll();
        contentPane.setFocusable(false);
        contentPane.add(windows.get(n));
        if(n == 4)  frame.addKeyListener(new PlayerMoveListener(gameWindow.getPlayer(1), gameWindow.getPlayer(2)));
        else if(n == 2) frame.addKeyListener( new MenuKeyListener(windows.get(2)));
        frame.validate();
        frame.repaint();
        //frame.setFocusable(true);
        frame.setVisible(true);
    }

    public static void addWindow(WindowInt window){
        windows.add((JPanel)window);
    }

    public static void removeWindow(){
        windows.remove(4);
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
        frame.setBounds((int)width/5,(int)(height/11),860,640);
        frame.setTitle("Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.add(windows.get(0));
        frame.setVisible(true);
    }
}
