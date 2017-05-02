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
    JPanel observer;
    boolean paused = false;
    private volatile boolean threadSuspended = false;


    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public BouncingBall(List<Ball> ballsList, int iterator, JPanel observer){
        ball = ballsList.get(iterator);
        this.ballsList = new ArrayList<>(ballsList);
        this.observer = observer;
    }

    @Override
    public void run() {
        while (true) {
            ball.directoryOfBall();
            for(Ball cheack : ballsList){
                if(!ball.equals(cheack)) ball.checkIfIntersects(cheack);
            }
            System.out.println(ball.getX());
            observer.repaint();
            try {
                Thread.sleep(6);
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
