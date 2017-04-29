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
        this.heigth = 180;
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
