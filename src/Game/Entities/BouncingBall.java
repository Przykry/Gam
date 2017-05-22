package Game.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przykry on 02.05.2017.
 */
public class BouncingBall implements Runnable {
    private Ball ball;
    private List<Ball> ballsList;
    private volatile boolean threadSuspended = false;

    public BouncingBall(List<Ball> ballsList, int iterator){
        ball = ballsList.get(iterator);
        this.ballsList = new ArrayList<>(ballsList);
    }

    @Override
    public void run() {
        while (true) {
            ball.directoryOfBall();
            for(Ball check : ballsList) {
                if (!ball.equals(check)) ball.checkIfIntersects(check);
            }
            try {
                Thread.sleep(15);
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
