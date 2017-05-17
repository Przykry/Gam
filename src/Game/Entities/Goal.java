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
    private boolean scored;

    public Goal(int leftOrRight){
        this.width = 60;
        this.heigth = 210;
        this.scored = false;
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


    static void ballHittingGoal(Ball ball){
        if(ball.getCenterY() + ball.getRadius() <= 430 && ball.getCenterY() + ball.getRadius() > 400){
            if(ball.getCenterX() - ball.getRadius() < 75){
                ball.reverseSpeedX();
                ball.setSpeedX(ball.getSpeedX()+10);
                ball.reverseSpeedY();
            }
            else if (ball.getCenterX() + ball.getRadius() > 785){
                ball.reverseSpeedX();
                ball.setSpeedX(ball.getSpeedX()-10);
                ball.reverseSpeedY();
            }
        }
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

    public void setGoalImage(Image goalImage) {
        this.goalImage = goalImage;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
