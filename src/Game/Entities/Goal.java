package Game.Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniel on 29.04.2017.
 */

/**
 *
 * Klasa przechowuje informacje o bramce
 */
public class Goal {
    private int width, heigth;
    private Image goalImage;

    public Goal(int leftOrRight){
        this.width = 60;
        this.heigth = 210;
        loadGoalImage(leftOrRight);
    }

    /**
     * Metoda wczytuje obraz bramki w zależności od parametru, który decyduje w którą stronę bramka ma być skierowana
     * @param leftOrRight
     */
    private void loadGoalImage(int leftOrRight){
        if(leftOrRight == 1) {
            try {
                this.goalImage = ImageIO.read(new File("textures/Goal/leftGoal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                this.goalImage = ImageIO.read(new File("textures/Goal/rightGoal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metoda sprawdza czy piłka nie udeżyła w poprzeczkę którejś z bramek.
     * @param ball
     */
    public static void ballHittingGoal(Ball ball){
        if(ball.getCenterY() + ball.getRadius() <= 445 && ball.getCenterY() + ball.getRadius() > 415){
            if(ball.getCenterX() - ball.getRadius() < 75){
                ball.setY(416-60);
                ball.setSpeedX(ball.getSpeedX()+10);
                ball.reverseSpeedY();
            }
            else if (ball.getCenterX() + ball.getRadius() > 785){
                ball.setY(416-60);
                ball.setSpeedX(ball.getSpeedX()-10);
                ball.reverseSpeedY();
            }
        }else if(ball.getY() < 420 && ball.getY() >= 390){
            if(ball.getCenterX() - ball.getRadius() < 75){
                ball.setY(421);
                ball.setSpeedX(ball.getSpeedX()+10);
                ball.reverseSpeedY();
            }
            else if (ball.getCenterX() + ball.getRadius() > 785){
                ball.setY(421);
                ball.setSpeedX(ball.getSpeedX()-10);
                ball.reverseSpeedY();
            }
        }
    }

    /**
     * metoda sprawdza czy piłka nie wpadła do bramki po lewej stronie ekranu
     * @param ball
     * @return true or false
     */
    public boolean isLeftScored(Ball ball){
        if(ball.getCenterY() + ball.getRadius() >= 445){
            if(ball.getCenterX() + ball.getRadius() < 75){
                return true;
            }
        }
        return false;
    }

    /**
     * metoda sprawdza czy piłka nie wpadła do bramki po prawej stronie ekranu
     * @param ball
     * @return true or false
     */
    public boolean isRightScored(Ball ball){
        if(ball.getCenterY() + ball.getRadius() >= 445){
            if(ball.getCenterX() - ball.getRadius() > 785) {
                return true;
            }
        }
        return false;
    }


    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public Image getGoalImage() {
        return goalImage;
    }
}
