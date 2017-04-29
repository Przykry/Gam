package Game.Windows;

import Game.ButtonListeners.AcceptButtonListener;
import Game.ButtonListeners.BackButtonListener;
import Game.ButtonListeners.LeftSwipeButtonListener;
import Game.ButtonListeners.RightSwipeButtonListener;
import Game.Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Daniel on 26.04.2017.
 */
public class ChoosePlayerWindow extends JPanel implements Window{
    int width;
    int heigth;
    static int firstPlayerChoose, secondPlayerChoose;

    public Player[] playerModels = new Player[]{
            new Player("Messi",10,7,55),
            new Player("Arbuz",2,0,0)
    };

    private Player player1;
    private Player player2;

    JButton [] buttons = new JButton[]{
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton()
    };
    String [] filepaths = new String[]{
            "textures\\backButton.png",
            "textures\\acceptButton.png",
            "textures\\swipeLeftButton.png",
            "textures\\swipeRightButton.png"
    };
    ActionListener [] listeners = new ActionListener[]{
            new BackButtonListener(),
            new AcceptButtonListener(),
            new LeftSwipeButtonListener(this,1),
            new RightSwipeButtonListener(this,1),
            new LeftSwipeButtonListener(this,2),
            new RightSwipeButtonListener(this,2)
    };

    Image resizedHead[] = new Image[2];
    Image resizedTorso[] = new Image[2];

    Image backgroundImage;


    public int getFirstPlayerChoose() {
        return firstPlayerChoose;
    }

    public void setFirstPlayerChoose(int firstPlayerChoose) {
        this.firstPlayerChoose = firstPlayerChoose;
    }

    public int getSecondPlayerChoose() {
        return secondPlayerChoose;
    }

    public void setSecondPlayerChoose(int secondPlayerChoose) {
        this.secondPlayerChoose = secondPlayerChoose;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setResizedHead(Player play, int i, int img) {
        this.resizedHead[i] = resizePlayerHead(play,img);
    }

    public void setResizedTorso(Player play, int i, int img) {
        this.resizedTorso[i] = resizePlayerTorso(play,img);
    }

    public ChoosePlayerWindow(int width, int heigth) throws IOException{
        this.width = width;
        this.heigth = heigth;
        this.backgroundImage = getBackgroundImage("mainBackground");
        this.firstPlayerChoose = 0;
        this.secondPlayerChoose = 0;


        this.player1 = playerModels[firstPlayerChoose];
        this.player2 = playerModels[secondPlayerChoose];
        this.resizedHead[0] = resizePlayerHead(this.player1,2);
        this.resizedTorso[0] = resizePlayerTorso(this.player1,3);
        this.resizedHead[1] = resizePlayerHead(this.player2,0);
        this.resizedTorso[1] = resizePlayerTorso(this.player2,0);
        for(int i = 0; i < 2; i++){
            createButton(buttons[i],80+520*i,500,filepaths[i],listeners[i]);
            this.add(buttons[i]);
        }
        createButton(buttons[2],50,260,filepaths[2],listeners[2]);
        this.add(buttons[2]);
        createButton(buttons[3], 50+player1.getWidthHead()*2+90,260,filepaths[3],listeners[3]);
        this.add(buttons[3]);
        createButton(buttons[4],530,260,filepaths[2],listeners[4]);
        this.add(buttons[4]);
        createButton(buttons[5], 530+player1.getWidthHead()*2+90,260,filepaths[3],listeners[5]);
        this.add(buttons[5]);

        this.setFocusable(true);
        this.setLayout(null);
    }

    public Image resizePlayerHead(Player play, int i){
        Image scaledImg = play.getHeadImage(i).getScaledInstance(play.getWidthHead()*2, play.getHeigthHead()*2,  Image.SCALE_SMOOTH);
        return scaledImg;
    }

    public Image resizePlayerTorso(Player play, int i){
        Image scaledImg = play.getTorsoImage(i).getScaledInstance(play.getWidthTorso()*2, play.getHeigthTorso()*2,  Image.SCALE_SMOOTH);
        return scaledImg;
    }

    private void drawPlayers(Graphics graphics){
        graphics.drawImage(resizedHead[0],120,200 ,this);
        graphics.drawImage(resizedTorso[0],127, 200+player1.getHeigthHead()*2,this  );
        graphics.drawImage(resizedHead[1],600,200 ,this);
        graphics.drawImage(resizedTorso[1],607, 200+player2.getHeigthHead()*2,this  );
    }


    private void drawAtributes(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MyFont",Font.BOLD,25));
        graphics.drawString("Name: "+player1.getName(),120,80);
        graphics.drawString("Max Jump: "+player1.getMaxJump(),120,105);
        graphics.drawString("Speed: "+ player1.getSpeed(),120,130);
        graphics.drawString("Shot Strength: "+player1.getShotStrength(),120,155);
        graphics.drawString("Name: "+player2.getName(),600,80);
        graphics.drawString("Max Jump: "+player2.getMaxJump(),600,105);
        graphics.drawString("Speed: "+ player2.getSpeed(),600,130);
        graphics.drawString("Shot Strength: "+player2.getShotStrength(),600,155);
    }


    public void paintComponent(Graphics graphics){
        graphics.drawImage(backgroundImage,0,0,this);
        drawBorders(graphics,width,heigth);
        drawPlayers(graphics);
        drawAtributes(graphics);
    }
}