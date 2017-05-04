package Game.Windows;

import Game.ButtonListeners.BackButtonListener;
import Game.ButtonListeners.PlayerMoveListener;
import Game.Engine;
import Game.Entities.Ball;
import Game.Entities.BouncingBall;
import Game.Entities.Goal;
import Game.Entities.Player;
import Game.Input;
import Game.Main;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static Game.Windows.ChoosePlayerWindow.getPlayer1;
import static Game.Windows.ChoosePlayerWindow.getPlayer2;

/**
 * Created by Daniel on 29.04.2017.
 */
public class GameWindow extends JPanel implements WindowInt, ActionListener{
    private int width, heigth;
    private Image backgroundImage;
    private Goal leftGoal, rightGoal;
    private static Ball ball;
    private JButton backButton;
    private static Player player1, player2;
    private static Timer timer;
    final int g;
    static Thread[] entities;
    public static Timer getTimer() {
        return timer;
    }

    public static Player getPlayer(int player) {
       if(player == 1) return player1;
       else return player2;
    }

    public GameWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        this.player1 = getPlayer1();
        this.player2 = getPlayer2();
        try {
            backgroundImage = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        backButton = new JButton();
        createButton(backButton,50,25,"textures\\backButton.png",new BackButtonListener());
        this.add(backButton);
        leftGoal = new Goal(1);
        rightGoal = new Goal(2);
        ball = new Ball((width-30)/2, getGround() + 50,0,16,this);
        setPlayerStanding();
        player1.setKeys(1);
        player2.setKeys(2);
        g = 17;
        this.addKeyListener(new PlayerMoveListener(player1,player2));
        this.setFocusable(true);
        this.setLayout(null);
        Main.setGameWindow(this);
        timer = new Timer(5,this);
    }

    private void setPlayerStanding(){
        player1.setX(leftGoal.getWidth()+20+15);
        player1.setY(heigth-15-player1.getHeigthTorso()-2*player1.getRadiusHead());
        player2.setX(width - rightGoal.getWidth()-2*player2.getRadiusHead()-20);
        player2.setY(heigth-15-player2.getHeigthTorso()-2*player2.getRadiusHead());
        player1.setCenterHeadX(player1.getX() + player1.getRadiusHead());
        player1.setCenterHeadY(player1.getY() + player1.getRadiusHead());
        player2.setCenterHeadX(player2.getX() + player2.getRadiusHead());
        player2.setCenterHeadY(player2.getY() + player2.getRadiusHead());
    }

    public static void runEntities(){
        List<Runnable> entity = new ArrayList<>();
        entity.add(player1);
        entity.add(player2);
        entity.add(ball);
        entities = new Thread[3];
        for(int i=0;i<3;i++){
            entities[i] = new Thread(entity.get(i));
            entities[i].start();
        }
    }

    public static void stopEntities(){
        for(Thread t : entities){
            t.suspend();
        }
    }


    private void drawGoalsAndBall(Graphics graphics){
        ball.drawBall(graphics);
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

    public static int getGround(){
        return 485;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(ball.getSpeedY() < 0){
            ball.move();
            ball.setSpeedY(ball.getSpeedY()-g);
        }else if(ball.getSpeedY() > 0){
            ball.move();
            ball.setSpeedY(ball.getSpeedY()-g);
        }
        if(ball.getSpeedY() < 10 && ball.getSpeedY() > -10 && ball.getCenterY() + ball.getRadius() >= heigth - 17){
            ball.move();
            ball.setSpeedY(0);
        }
        if(ball.getCenterY() + ball.getRadius()>=heigth-17){
            ball.decreaseSpeedX(10);
        }*/
        checkIfBodyIntersects(ball,player2, player1);
        this.repaint();
    }
}
