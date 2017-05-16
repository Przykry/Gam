package Game.Windows;

import Game.Entities.BouncingBall;
import Game.Entities.Ball;
import Game.ButtonListeners.ExitGameButtonListener;
import Game.ButtonListeners.HallOfFameButtonListener;
import Game.ButtonListeners.MenuButtonListener;
import Game.ButtonListeners.StartGameButtonListener;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Daniel on 24.04.2017.
 */
public class MainWindow extends JPanel implements ActionListener, WindowInt {
    int width;
    int heigth;
    JButton[] buttons = new JButton[]{
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton()
    };
    String[] filePaths = new String[]{
            "textures\\startGameButton.png",
            "textures\\hallOfFameButton.png",
            "textures\\menuButton.png",
            "textures\\exitGameButton.png"
    };
    ActionListener[] listeners = new ActionListener[]{
            new StartGameButtonListener(),
            new HallOfFameButtonListener(),
            new MenuButtonListener(),
            new ExitGameButtonListener()
    };
    Image mainBackground = null;
    static Thread thread[];
    List<Ball> ballList = new ArrayList<>();
    Timer timer;
    public MainWindow(int width, int height){
        this.width = width;
        this.heigth = height;
        try {
            this.mainBackground = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        addBouncingBalls(new Random().nextInt(15));
        for (int i = 0; i < buttons.length; i++) {
            createButton(buttons[i], width - 300, i * 60 + i * 40 + 60, filePaths[i], listeners[i]);
            this.add(buttons[i]);
        }
        timer = new Timer(8,this);
        this.setFocusable(true);
        this.setLayout(null);
        timer.start();
    }


    private void addBouncingBalls(int numberOfBalls){
        thread = new Thread[numberOfBalls];
        for(int i=0;i<numberOfBalls;i++){
            ballList.add(new Ball(
                        new Random().nextInt(560),
                        new Random().nextInt(465),
                        150,
                        150,
                        this)
                        );
        }

        List<Runnable> bouncingBall = new ArrayList<>();
        for(int i=0;i<numberOfBalls;i++) {
            bouncingBall.add(new BouncingBall(ballList,i));
            thread[i] = new Thread(bouncingBall.get(i));
            thread[i].start();
        }
    }

    public static void stopBouncingBalls(){
        for(Thread t : thread){
            t.suspend();
        }
    }

    public static void startBouncingBalls(){
        for(Thread t : thread){
            t.resume();
        }
    }

    public void paintComponent(Graphics graphics) {
        drawBackground(graphics, mainBackground, this);
        drawBorders(graphics,width,heigth);
        for(Ball ball : ballList) ball.drawBall(graphics);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
