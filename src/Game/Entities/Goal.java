package Game.Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniel on 29.04.2017.
 */
public class Goal {
    private int width, heigth;
    private Image goalImage;

    public Goal(int leftOrRight){
        this.width = 60;
        this.heigth = 210;
        loadGoalImage(leftOrRight);
    }

    private void loadGoalImage(int leftOrRight){
        if(leftOrRight == 1) {
            try {
                this.goalImage = ImageIO.read(new File("textures\\leftGoal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                this.goalImage = ImageIO.read(new File("textures\\rightGoal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

    public boolean isLeftScored(Ball ball){
        if(ball.getCenterY() + ball.getRadius() >= 445){
            if(ball.getCenterX() + ball.getRadius() < 75){
                return true;
            }
        }
        return false;
    }

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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public Image getGoalImage() {
        return goalImage;
    }
}
