package Game.Windows;

import Game.BouncingBall;
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
    Timer timer = new Timer(8, this);
    Thread thread[];
    List<Ball> ballList = new ArrayList<>();

    public MainWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        try {
            this.mainBackground = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        addBouncingBalls(2);
        for (int i = 0; i < 4; i++) {
            createButton(buttons[i], width - 300, i * 60 + i * 40 + 60, filePaths[i], listeners[i]);
            this.add(buttons[i]);
        }
        this.setFocusable(true);
        this.setLayout(null);
        timer.start();
    }


    private void addBouncingBalls(int numberOfBalls){
        thread = new Thread[numberOfBalls];
        for(int i=0;i<numberOfBalls;i++){
            ballList.add(new Ball(
                        new Random().nextInt(850),
                        new Random().nextInt(630),
                        30,
                        20,
                        this)
                        );
        }

        List<Runnable> bouncingBall = new ArrayList<>();
        for(int i=0;i<numberOfBalls;i++) {
            bouncingBall.add(new BouncingBall(ballList,i,this));
            thread[i] = new Thread(bouncingBall.get(i));
            thread[i].start();
        }
    }

    public void paintComponent(Graphics graphics) {
        //background
        drawBackground(graphics, mainBackground, this);
        drawBorders(graphics,width,heigth);
        for(Ball ball : ballList) ball.drawBall(graphics);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
