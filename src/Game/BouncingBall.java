package Game;

import Game.Entities.Ball;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Przykry on 02.05.2017.
 */
public class BouncingBall implements Runnable {
    Ball ball;
    List<Ball> ballsList;
    private volatile boolean threadSuspended = false;

    public BouncingBall(List<Ball> ballsList, int iterator){
        ball = ballsList.get(iterator);
        this.ballsList = new ArrayList<>(ballsList);
    }

    @Override
    public void run() {
        while (true) {
            ball.directoryOfBall();
            for(Ball cheack : ballsList) {
                if (!ball.equals(cheack)) ball.checkIfIntersects(cheack);
            }
            try {
                Thread.sleep(8);
                if(threadSuspended){
                    synchronized(this){
                        while (threadSuspended){
                            wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
