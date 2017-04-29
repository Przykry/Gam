package Game.Windows;

import Game.Entities.Ball;
import Game.ButtonListeners.ExitGameButtonListener;
import Game.ButtonListeners.HallOfFameButtonListener;
import Game.ButtonListeners.MenuButtonListener;
import Game.ButtonListeners.StartGameButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Daniel on 24.04.2017.
 */
public class MainWindow extends JPanel implements ActionListener, WindowInt {
    int width;
    int heigth;
    public Ball ball1, ball2;
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

    public MainWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        try {
            this.mainBackground = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.ball1 = new Ball(0, 0, 50, 50, this);
        this.ball2 = new Ball(width - 60, 0, -40, 40, this);
        for (int i = 0; i < 4; i++) {
            createButton(buttons[i], width - 300, i * 60 + i * 40 + 60, filePaths[i], listeners[i]);
            this.add(buttons[i]);
        }
        this.setFocusable(true);
        this.setLayout(null);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        //background
        drawBackground(graphics, mainBackground, this);
        drawBorders(graphics,width,heigth);

        graphics.drawImage(ball1.getBallImage(), ball1.getX(), ball1.getY(), this);
        graphics.drawImage(ball2.getBallImage(), ball2.getX(), ball2.getY(), this);
    }

    public void directoryOfBall() {
        ball1.move();
        ball2.move();
        ballHittingBorder(ball1);
        ballHittingBorder(ball2);
    }

    public void ballHittingBorder(Ball ball) {
        if (new Rectangle(0, 0, 15, heigth).intersects(new Rectangle(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2))) {
            ball.reverseSpeedX();
            if (ball.getX() < 15) ball.setX(16);
        }
        if (new Rectangle(0, 0, width, 15).intersects(new Rectangle(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2))) {
            ball.reverseSpeedY();
            if (ball.getY() < 15) ball.setY(16);
        }
        if (new Rectangle(0, heigth - 15, width, 15).intersects(new Rectangle(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2))) {
            ball.reverseSpeedY();
            if (ball.getY() + ball.getHeigth() > heigth - 15) ball.setY(heigth - 1 - 15 - ball.getHeigth());
        }
        if (new Rectangle(width - 15, 0, 15, heigth).intersects(new Rectangle(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2))) {
            ball.reverseSpeedX();
            if (ball.getX() + ball.getWidth() > width - 15) ball.setX(width - 1 - 15 - ball.getWidth());
        }
    }

    public double calculatePythagoras() {
        double aplusb = Math.pow(ball1.getCenterX() - ball2.getCenterX(), 2) + Math.pow(ball2.getCenterY() - ball1.getCenterY(), 2);
        double toReturn = Math.sqrt(aplusb);
        return toReturn;
    }

    public void calculateDirection() {
        double xDir1 = (ball1.getCenterX() - ball2.getCenterX()) / calculatePythagoras();
        double yDir1 = (ball2.getCenterY() - ball1.getCenterY()) / calculatePythagoras();

        double xDir2 = (ball2.getCenterX() - ball1.getCenterX()) / calculatePythagoras();
        double yDir2 = (ball1.getCenterY() - ball2.getCenterY()) / calculatePythagoras();

        int dirX1 = (int) Math.round(xDir1 * 70);
        int dirY1 = (int) Math.round(yDir1 * 70);

        int dirX2 = (int) Math.round(xDir2 * 70);
        int dirY2 = (int) Math.round(yDir2 * 70);
        ball1.setSpeedX(dirX1);
        ball1.setSpeedY(-dirY1);
        ball2.setSpeedX(dirX2);
        ball2.setSpeedY(-dirY2);
    }

    public void checkIfIntersects() {
        if (calculatePythagoras() <= ball1.getRadius() + ball2.getRadius()) {
            calculateDirection();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        directoryOfBall();
        checkIfIntersects();
        this.repaint();
    }
}
