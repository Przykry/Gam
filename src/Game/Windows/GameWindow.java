package Game.Windows;

import Game.ButtonListeners.BackButtonListener;
import Game.ButtonListeners.PlayerMoveListener;
import Game.Entities.Ball;
import Game.Entities.Goal;
import Game.Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Daniel on 29.04.2017.
 */
public class GameWindow extends JPanel implements WindowInt, ActionListener{
    private int width, heigth;
    private Image backgroundImage;
    private Goal leftGoal, rightGoal;
    private Ball ball;
    private JButton backButton;
    private Player player1, player2;
    private static Timer timer;
    final int g;

    public static Timer getTimer() {
        return timer;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
}

    public GameWindow(int width, int heigth, ChoosePlayerWindow window){
        this.width = width;
        this.heigth = heigth;
        this.player1 = window.getPlayer1();
        this.player2 = window.getPlayer2();
        try {
            backgroundImage = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        backButton = new JButton();
        createButton(backButton,50,25,"textures\\backButton.png",new BackButtonListener());
        this.setLayout(null);
        this.add(backButton);
        leftGoal = new Goal(1);
        rightGoal = new Goal(2);
        ball = new Ball((width-30)/2,50,0,16,this);
        player1.setX(leftGoal.getWidth()+20+15);
        player1.setY(heigth-15-player1.getHeigthTorso()-2*player1.getRadiusHead());
        player2.setX(width - rightGoal.getWidth()-2*player2.getRadiusHead()-20);
        player2.setY(heigth-15-player2.getHeigthTorso()-2*player2.getRadiusHead());
        player1.setCenterHeadX(player1.getX() + player1.getRadiusHead());
        player1.setCenterHeadY(player1.getY() + player1.getRadiusHead());
        player2.setCenterHeadX(player2.getX() + player2.getRadiusHead());
        player2.setCenterHeadY(player2.getY() + player2.getRadiusHead());
        player1.setKeys(1);
        player2.setKeys(2);
        g = 20;
        this.setFocusable(true);
        this.addKeyListener(new PlayerMoveListener(player1, player2));
        timer = new Timer(5,this);
        timer.start();
    }

    private void drawGoalsAndBall(Graphics graphics){
        graphics.drawImage(ball.getBallImage(),ball.getX(),ball.getY(),this);
        graphics.drawImage(leftGoal.getGoalImage(),16,heigth-15-leftGoal.getHeigth(),this);
        graphics.drawImage(rightGoal.getGoalImage(),width-15- rightGoal.getWidth(),heigth-15- rightGoal.getHeigth(),this);
    }

    private void drawPlayers(Graphics graphics){
        graphics.drawImage(player1.getHeadImage(2),player1.getX(),player1.getY(),this);
        graphics.drawImage(player1.getTorsoImage(3),player1.getX()+3,player1.getY()+2*player1.getRadiusHead(),this);

        graphics.drawImage(player2.getHeadImage(0),player2.getX(),player2.getY(),this);
        graphics.drawImage(player2.getTorsoImage(0),player2.getX()+3,player2.getY()+2*player2.getRadiusHead(),this);
    }

    public void paintComponent(Graphics graphics){
        drawBackground(graphics,backgroundImage,this);
        drawBorders(graphics,860,640);
        drawGoalsAndBall(graphics);
        drawPlayers(graphics);
    }

    /*private int calculateMaxHigh(int vy){
        int vykw = (int)Math.round(Math.pow(vy,2));

        return vykw/(2*g);
    }*/

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
            ball.setSpeedY((int)(ball.getSpeedY()*0.6));
            if (ball.getY() + ball.getHeigth() > heigth - 15) ball.setY(heigth - 1 - 15 - ball.getHeigth());
        }
        if (new Rectangle(width - 15, 0, 15, heigth).intersects(new Rectangle(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2))) {
            ball.reverseSpeedX();
            if (ball.getX() + ball.getWidth() > width - 15) ball.setX(width - 1 - 15 - ball.getWidth());
        }
    }

    public double calculatePythagoras(Ball ball, Player player) {
        double aplusb = Math.pow(ball.getCenterX() - player.getCenterHeadX(), 2) + Math.pow(player.getCenterHeadY() - ball.getCenterY(), 2);
        double toReturn = Math.sqrt(aplusb);
        return toReturn;
    }

    public void calculateDirection(Ball ball, Player player) {
        double xDir1 = (ball.getCenterX() - player.getCenterHeadX()) / calculatePythagoras(ball,player);
        double yDir1 = (player.getCenterHeadY() - ball.getCenterY()) / calculatePythagoras(ball, player);

        int dirX1 = (int) Math.round(xDir1 * 700);
        int dirY1 = (int) Math.round(yDir1 * 700);

        ball.setSpeedX(dirX1);
        ball.setSpeedY(-dirY1);
    }

    private void checkIfIntersects(Ball ball, Player player) {
        if (calculatePythagoras(ball, player) <= ball.getRadius() + player.getRadiusHead()) {
            calculateDirection(ball,player);
        }
    }

    private void checkIfBodyIntersects(Ball ball, Player player2, Player player1){
        if(ball.getCenterX() + ball.getRadius() >= player2.getX()+28 && ball.getY() <= player2.getY() + player2.getRadiusHead()+player2.getHeigthTorso() && ball.getY() > player2.getY() + player2.getRadiusHead()){
            ball.reverseSpeedX();
            ball.setSpeedX((int)(ball.getSpeedX()*0.8 + player2.getSpeed()*0.8));
        }
        if(ball.getCenterX() - ball.getRadius() <= player1.getX()-10 +player1.getWidthTorso() && ball.getY() <= player1.getY() + player1.getRadiusHead()+player1.getHeigthTorso() && ball.getY() > player1.getY() + player1.getRadiusHead()){
            ball.reverseSpeedX();
            ball.setSpeedX((int)(ball.getCenterX()*0.8 + player1.getSpeed()*0.8));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(ball.getSpeedY() < 0){
            ball.move();
            ball.setSpeedY(ball.getSpeedY()+g);
        }else if(ball.getSpeedY() > 0){
            ball.move();
            ball.setSpeedY(ball.getSpeedY()+g);
        }
        if(ball.getSpeedY() < 10 && ball.getSpeedY() > -10 && ball.getCenterY() + ball.getRadius() >= heigth - 17){
            ball.move();
            ball.setSpeedY(0);
        }
        if(ball.getCenterY() + ball.getRadius()>=heigth-17){
            ball.decreaseSpeedX(10);
        }
        if(player1.isMovingLeft()) player1.movePlayerLeft();
        if(player1.isMovingRight()) player1.movePlayerRight();
        if(player2.isMovingLeft()) player2.movePlayerLeft();
        if(player2.isMovingRight()) player2.movePlayerRight();
        ballHittingBorder(ball);
        checkIfIntersects(ball,player1);
        checkIfIntersects(ball,player2);
        checkIfBodyIntersects(ball,player2, player1);
        this.repaint();
    }
}
