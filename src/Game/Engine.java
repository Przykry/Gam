package Game;

import Game.Entities.Ball;
import Game.Entities.Player;
import Game.Windows.GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Daniel on 24.04.2017.
 */
public class Engine extends Thread{
    Player player;
    Ball ball;
    public Engine(Player player, Ball ball) {
        this.player = player;
        this.ball = ball;
    }


    public double calculatePythagoras(Ball ball, Player player) {
        double aplusb = Math.pow(ball.getCenterX() - player.getCenterHeadX(), 2) + Math.pow(player.getCenterHeadY() - ball.getCenterY(), 2);
        double toReturn = Math.sqrt(aplusb);
        return toReturn;
    }

    public void calculateDirection(Ball ball, Player player) {
        double xDir1 = (ball.getCenterX() - player.getCenterHeadX()) / calculatePythagoras(ball,player);
        double yDir1 = (player.getCenterHeadY() - ball.getCenterY()) / calculatePythagoras(ball, player);

        int dirX1 = (int) Math.round(xDir1 * 40);
        int dirY1 = (int) Math.round(yDir1 * 40);

        ball.setSpeedX(dirX1);
        ball.setSpeedY(-dirY1);
    }

    private void checkIfIntersects(Ball ball, Player player) {
        if (calculatePythagoras(ball, player) <= ball.getRadius() + player.getRadiusHead()) {
            calculateDirection(ball,player);
        }
    }

        @Override
        public void run() {

        }
}
