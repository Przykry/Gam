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

/**
 *
 * Klasa rozszerza klasę JPanel i implenetuje interfejs WindowInt, na tym oknie wyświetlane jest wybieranie zawodników do meczu
 */
public class ChoosePlayerWindow extends JPanel implements WindowInt {
    private int width;
    private int heigth;
    private static int firstPlayerChoose, secondPlayerChoose;

    public Player[] player1Models = new Player[]{
            new Player(50,50,"Messi",10,7,55),
            new Player(50,50,"Kuchy",8,5,85),
            new Player(50,50,"Lewy",10,6,90),
            new Player(50,50,"Ronaldo",9,8,95)
    };

    public Player[] player2Models = new Player[]{
            new Player(50,50,"Messi",10,7,55),
            new Player(50,50,"Kuchy",8,5,85),
            new Player(50,50,"Lewy",10,6,90),
            new Player(50,50,"Ronaldo",9,8,95)
    };

    private static Player player1;
    private static Player player2;

    private JButton [] buttons = new JButton[]{
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton(),
            new JButton()
    };
    private String []  filepaths = new String[]{
            "textures/Buttons/chooseButtons.png",
            "textures/Buttons/acceptButton.png",
            "textures/Buttons/swipeLeftButton.png",
            "textures/Buttons/swipeRightButton.png"
    };
    private ActionListener [] listeners = new ActionListener[]{
            new BackButtonListener(),
            new AcceptButtonListener(this),
            new LeftSwipeButtonListener(this,1),
            new RightSwipeButtonListener(this,1),
            new LeftSwipeButtonListener(this,2),
            new RightSwipeButtonListener(this,2)
    };

    private Image resizedHead[] = new Image[2];
    private Image resizedTorso[] = new Image[2];
    private Image backgroundImage;

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

    public static Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public static Player getPlayer2() {
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



    public ChoosePlayerWindow(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        try {
            this.backgroundImage = getBackgroundImage("mainBackground");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.firstPlayerChoose = 0;
        this.secondPlayerChoose = 0;

        this.player1 = player1Models[firstPlayerChoose];
        this.player2 = player2Models[secondPlayerChoose];
        player1.setX(60);
        player2.setX(80);
        this.resizedHead[0] = resizePlayerHead(this.player1,1);
        this.resizedTorso[0] = resizePlayerTorso(this.player1,3);
        this.resizedHead[1] = resizePlayerHead(this.player2,0);
        this.resizedTorso[1] = resizePlayerTorso(this.player2,0);
        for(int i = 0; i < 2; i++){
            createButton(buttons[i],120+440*i,550,filepaths[0],listeners[i]);
            if(i ==0) buttons[i].setText("Back");
            else buttons[i].setText("Accept");
            this.add(buttons[i]);
        }
        createButton(buttons[2],50,260,filepaths[2],listeners[2]);
        this.add(buttons[2]);
        createButton(buttons[3], 50+player1.getRadiusHead()*4+90,260,filepaths[3],listeners[3]);
        this.add(buttons[3]);
        createButton(buttons[4],530,260,filepaths[2],listeners[4]);
        this.add(buttons[4]);
        createButton(buttons[5], 530+player1.getRadiusHead()*4+90,260,filepaths[3],listeners[5]);
        this.add(buttons[5]);

        this.setFocusable(true);
        this.setLayout(null);
    }

    /**
     * Powiększa głowę piłkarza w celu wyświetlenia jej podczas wyboru (w grze jest nieco mniejsza)
     * @param play
     * @param i
     * @return
     */
    private Image resizePlayerHead(Player play, int i){
        return play.getHeadImage(i).getScaledInstance(play.getRadiusHead()*4, play.getRadiusHead()*4,  Image.SCALE_SMOOTH);
    }

    /**
     * Powiększa tułów piłkarza w celu wyświetlenia podczas wyboru (w grze jest nieco mniejszy)
     * @param play
     * @param i
     * @return
     */
    private Image resizePlayerTorso(Player play, int i){
        return play.getTorsoImage(i).getScaledInstance(play.getWidthTorso()*2, play.getHeightTorso()*2,  Image.SCALE_SMOOTH);
    }

    /**
     * Metoda rysuje zawodników na ekranie
     * @param graphics
     */
    private void drawPlayers(Graphics graphics){
        graphics.drawImage(resizedHead[0],120,200 ,this);
        graphics.drawImage(resizedTorso[0],127, 200+player1.getRadiusHead()*4,this  );
        graphics.drawImage(resizedHead[1],600,200 ,this);
        graphics.drawImage(resizedTorso[1],607, 200+player2.getRadiusHead()*4,this  );
    }

    /**
     * Metoda wyświetla atrybuty aktualnie zaznaczonych piłkarzy
     * @param graphics
     */
    private void drawAttributes(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("MyFont",Font.BOLD,25));
        graphics.drawString("Name: "+player1.getName(),120,80);
        graphics.drawString("Max Jump: "+player1.getMaxJump(),120,105);
        graphics.drawString("Speed: "+ player1.getSpeed(),120,130);
        graphics.drawString("Shot Strength: "+player1.getShotStrength(),120,155);
        graphics.drawString("Name: "+player2.getName(),580,80);
        graphics.drawString("Max Jump: "+player2.getMaxJump(),580,105);
        graphics.drawString("Speed: "+ player2.getSpeed(),580,130);
        graphics.drawString("Shot Strength: "+player2.getShotStrength(),580,155);
    }

    /**
     * Metoda rysuje wszystkie elementy na oknie
     * @param graphics
     */
    public void paintComponent(Graphics graphics){
        graphics.drawImage(backgroundImage,0,0,this);
        drawBorders(graphics,width,heigth);
        drawPlayers(graphics);
        drawAttributes(graphics);
    }


}