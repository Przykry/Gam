package Game.Windows;

import Game.ButtonListeners.BackButtonListener;
import Game.ButtonListeners.PlayerMoveListener;
import Game.Entities.Ball;
import Game.Entities.Clock;
import Game.Entities.Goal;
import Game.Entities.Player;
import Game.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 29.04.2017.
 * Klasa wyświetla okno z grą.
 */
public class GameWindow extends JPanel implements WindowInt, ActionListener{
    private int width, height;
    private Image backgroundImage;
    private Goal leftGoal, rightGoal;
    private static Ball ball;
    private JButton backButton = new JButton();
    private static Player player1, player2;
    private static Timer timer;
    private static Thread[] entities;
    private Image gameBar;
    private boolean gameEnd;
    private static Thread Tclock;
    private JLabel points[] = {
            new JLabel(),
            new JLabel()
    };
    private JLabel barText[] = {
            new JLabel(),
            new JLabel()
    };

    public static Timer getTimer() {
        return timer;
    }

    public static Player getPlayer(int player) {
        if(player == 1) return player1;
        else return player2;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime(){
        return this.time;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public JButton getBackButton() {
        return backButton;
    }

    private int time = 60;
    private JLabel clock;

    public GameWindow(int width, int height, ChoosePlayerWindow window){
        this.width = width;
        this.height = height;
        this.player1 = window.getPlayer1();
        player1.setPlayerHeadImage(1);
        player1.setPlayerTorsoImage(2);
        this.player2 = window.getPlayer2();
        player2.setPlayerTorsoImage(0);
        player2.setPlayerHeadImage(0);
        setPlayerKeys();
        try {
            backgroundImage = getBackgroundImage("mainBackground");
            gameBar = getBackgroundImage("gameBar");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        leftGoal = new Goal(1);
        rightGoal = new Goal(2);
        ball = new Ball(width/2, getGround()-600,0,0,this);
        setPlayerStanding();
        createPointsLabel();
        createColonLabel();
        createClockLabel();
        Tclock = new Thread(new Clock(this));
        addBackButton();
        this.addKeyListener(new PlayerMoveListener(player1,player2,this));
        this.setFocusable(true);
        this.setLayout(null);
        this.gameEnd = false;
        Main.setGameWindow(this);
        timer = new Timer(5,this);
        Tclock.start();
    }

    private static void setPlayerKeys(){
        player1.setKeys(1);
        player2.setKeys(2);
    }

    /**
     * Tworzy miejsce dla wyświetlania pozostałego czasu gry.
     */
    private void createClockLabel(){
        clock = new JLabel();
        setTextLabelForeground(clock);
        setClockLabelPosition();
        clock.setText("Time: " + Integer.toString(time) + " sec");
        this.add(clock);
    }

    /**
     * Aktualizuje wyświetlany czas.
     */
    public void tickClock(){
        clock.setText("Time: " + Integer.toString(time) + " sec");
    }

    /**
     * Umiejscawia zegar na oknie.
     */
    private void setClockLabelPosition(){
        clock.setBounds(600,15,32*12,32);
    }

    /**
     * Dodaje przycisk cofnięcia
     */
    private void addBackButton(){
        createButton(backButton,15,15,"textures/Buttons/backGameButton.png",new BackButtonListener());
        backButton.setText("Back");
        this.add(backButton);
    }

    /**
     * Ustawia graczy w pozycjach początkowych
     */
    private void setPlayerStanding(){
        player1.setX(leftGoal.getWidth()+20+15);
        player1.setY(height -15-player1.getHeightTorso()-2*player1.getRadiusHead());
        player2.setX(width - rightGoal.getWidth()-2*player2.getRadiusHead()-20);
        player2.setY(height -15-player2.getHeightTorso()-2*player2.getRadiusHead());
        player1.setCenterHeadX(player1.getX() + player1.getRadiusHead());
        player1.setCenterHeadY(player1.getY() + player1.getRadiusHead());
        player2.setCenterHeadX(player2.getX() + player2.getRadiusHead());
        player2.setCenterHeadY(player2.getY() + player2.getRadiusHead());
    }

    /**
     * Uruchamia wątki piłki i graczy
     */
    public static void runEntities(){
        List<Runnable> entity = new ArrayList<>();
        entity.add(player1);
        entity.add(player2);
        entity.add(ball);
        entities = new Thread[3];
        for(int i=0;i<3;i++){
            entities[i] = new Thread(entity.get(i));
            entities[i].start();
        }
    }

    /**
     * Wstrzymuje wątki piłki i graczy
     */
    public static void stopEntities(){
        for(Thread t : entities){
            t.suspend();
        }
    }

    /**
     * Rysuje czarny pasek z zegarem i wynikiem u góry ekranu
     * @param g
     */
    private void drawWindowBar(Graphics g){
       g.drawImage(gameBar,15,15,this);
    }

    /**
     * Ustawia piłkę w pozycji początkowej
     * @param ball
     */
    private void setBallStartPosition(Ball ball){
        ball.setX(width/2);
        ball.setY(getGround()-600);
        ball.setSpeedX(0);
        ball.setSpeedY(0);
    }

    /**
     * Ustawia dwukropek w górnej części ekranu na środku
     * @param label
     */
    private void setColonPosition(JLabel label){
        label.setBounds(width/2,20,32,32);
    }

    /**
     * Tworzy miejsce do wyświetlania dwukropka.
     */
    private void createColonLabel(){
        setColonPosition(barText[0]);
        setTextLabelForeground(barText[0]);
        barText[0].setText(":");
        this.add(barText[0]);
    }

    /**
     * Ustawia szczegóły tekstu do wyświetlania wyniku
     * @param label
     */
    private void setTextLabelForeground(JLabel label){
        label.setFont(new Font("Comic Sans", Font.BOLD, 32));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
    }

    /**
     * Ustawia pozycję wyświetlania wyniku
     * @param label
     * @param i
     */
    private void setLabelPointsPosition(JLabel label, int i){
        label.setBounds(width/2 - 20 + i,20,64,32);
    }

    /**
     * Ustawia wartość zdobytych bramek które mają być wyświetlone
     * @param label
     * @param player
     */
    private void setLabelPoints(JLabel label,Player player){
        label.setText(String.valueOf(player.getPoints()));
    }

    /**
     * Tworzy miejsca do wyświetlania punktów
     */
    private void createPointsLabel(){
        setLabelPoints(points[0],player1);
        setLabelPoints(points[1],player2);
        for(int i = 0; i< points.length; i++){
            setTextLabelForeground(points[i]);
            setLabelPointsPosition(points[i],32 * i);
            this.add(points[i]);
        }
    }

    /**
     * Wyświetla informację o zwycięscy bądź remisie na koniec gry
     * @param graphics
     */
    private void endOfGame(Graphics graphics){
        graphics.setColor(Color.decode("#2A2C2E"));
        graphics.setFont(new Font("Hobo Std", Font.BOLD, 32));
        if(player1.getPoints() > player2.getPoints()){
            graphics.drawString(player1.getName() + " won the game!",250,300);
        }else if(player1.getPoints() < player2.getPoints()){
            graphics.drawString(player2.getName() + " won the game!",250,300);
        }else graphics.drawString("It's a draw!",350,300);
        graphics.drawString("Press ENTER to return to main menu.",150,350);
    }

    /**
     * Resetuje zdobyte punkty
     */
    public static void resetPoints(){
        player1.setPoints(0);
        player2.setPoints(0);
    }

    /**
     * Sprawdza czy została strzelona bramka
     * @return
     */
    private boolean isScored(){
        if(leftGoal.isLeftScored(ball)) {
            player2.setPoints(player2.getPoints() + 1);
            setLabelPoints(points[1],player2);
            return true;
        }
        else if(rightGoal.isRightScored(ball)) {
            player1.setPoints(player1.getPoints() + 1);
            setLabelPoints(points[0],player1);
            if(Integer.parseInt(points[0].getText()) == 10) setLabelPointsPosition(points[0],-20);
            else if(Integer.parseInt(points[0].getText()) == 100) setLabelPointsPosition(points[0],-40);
            return true;
        }
        return false;
    }

    /**
     * Ustawia wszystko na pierwotne pozycje po strzeleniu bramki
     */
    private void startGameAgain(){
        if(isScored()){
            setPlayerStanding();
            setBallStartPosition(ball);
            player1.setBlockedLeft(false);
            player1.setBlockedRight(false);
            player2.setBlockedLeft(false);
            player2.setBlockedRight(false);
        }
    }

    /**
     * Rysuje bramki
     * @param graphics
     */
    private void drawGoals(Graphics graphics){
        graphics.drawImage(leftGoal.getGoalImage(),16, height -15-leftGoal.getHeigth(),this);
        graphics.drawImage(rightGoal.getGoalImage(),width-15- rightGoal.getWidth(), height -15- rightGoal.getHeigth(),this);
    }

    /**
     * Rysuje zawodników
     * @param graphics
     */
    private void drawPlayers(Graphics graphics){
        graphics.drawImage(player1.getHeadImage(player1.getPlayerHeadImage()),player1.getX(),player1.getY(),this);
        graphics.drawImage(player1.getTorsoImage(player1.getPlayerTorsoImage()),player1.getX()+3,player1.getY()+2*player1.getRadiusHead(),this);

        graphics.drawImage(player2.getHeadImage(player2.getPlayerHeadImage()),player2.getX(),player2.getY(),this);
        graphics.drawImage(player2.getTorsoImage(player2.getPlayerTorsoImage()),player2.getX()+3,player2.getY()+2*player2.getRadiusHead(),this);
    }

    /**
     * Rysuje cały obraz gry
     * @param graphics
     */
    public void paintComponent(Graphics graphics){
        drawBackground(graphics,backgroundImage,this);
        drawWindowBar(graphics);
        drawBorders(graphics,860,640);
        ball.drawBall(graphics);
        drawPlayers(graphics);
        drawGoals(graphics);
        if(gameEnd) endOfGame(graphics);
    }

    /**
     * Sprawdza czy gracze się ze sobą zderzyli
     * @return
     */
    private boolean checkIfPlayerHits(){
        if(Math.abs(player1.getX() - player2.getX()) < player1.getRadiusHead() + player2.getRadiusHead()){
            blockPlayers(false);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Sprawdza czy gracze zablokowali piłkę między sobą
     */
    private void checkIfPlayersBlockedTheBall(){
        if(ball.checkIfIntersectsBoth(player1,player2)){
            blockPlayers(true);
        }
        else{
            ball.setBlocked(false);
        }
    }

    /**
     * Uniemożliwia graczom poruszanie się (opcjonalnie wyłącza ruch piłki jeśli jest zablokowana)
     * @param ifBall
     */
    private void blockPlayers(boolean ifBall){
        if(player1.getCenterHeadX() < player2.getCenterHeadX() ){
            player1.setBlockedRight(true);
            player2.setBlockedLeft(true);
            if(ifBall) ball.setBlocked(true);
        }else{
            player1.setBlockedLeft(true);
            player2.setBlockedRight(true);
            if(ifBall) ball.setBlocked(true);
        }
    }

    /**
     * Nie pozwala graczom wyjść poza ekran
     * @param player
     */
    private void playerExitFrame(Player player){
        if(player.getCenterHeadX()+player.getRadiusHead() >= 860-15){
            player.setX(860-15-2*player.getRadiusHead());
            player.setBlockedRight(true);
            player.setExitBlocked(true);
        }else if(player.getCenterHeadX()-player.getRadiusHead() <= 15){
            player.setX(15);
            player.setBlockedLeft(true);
            player.setExitBlocked(true);
        }else{
            if(player.isBlockedRight() && player.isExitBlocked()){
                player.setBlockedRight(false);
                player.setExitBlocked(false);
            }
            else if(player.isBlockedLeft() && player.isExitBlocked()){
                player.setBlockedLeft(false);
                player.setExitBlocked(false);
            }
        }
    }

    /**
     * Odblokowuje graczy jeśli są zablokowani
     */
    private void unblockPlayers(){
        if(player1.isMovingRight() && !checkIfPlayerHits()){
            player1.setBlockedLeft(false);
            player2.setBlockedRight(false);
        }
        if(player1.isMovingLeft() && !checkIfPlayerHits()){
            player1.setBlockedRight(false);
            player2.setBlockedLeft(false);
        }
        if(player2.isMovingRight() && !checkIfPlayerHits()){
            player1.setBlockedRight(false);
            player2.setBlockedLeft(false);
        }
        if(player2.isMovingLeft() && !checkIfPlayerHits()){
            player1.setBlockedLeft(false);
            player2.setBlockedRight(false);
        }
    }

    /**
     * Zwraca poziom ziemi
     * @return
     */
    public static int getGround(){
        return 625;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ball.checkIfIntersects(player1);
        ball.checkIfIntersects(player2);
        ball.checkIfBodyIntersects(player1);
        ball.checkIfBodyIntersects(player2);
        checkIfPlayersBlockedTheBall();
        checkIfPlayerHits();
        startGameAgain();
        Goal.ballHittingGoal(ball);
        playerExitFrame(player1);
        playerExitFrame(player2);
        unblockPlayers();
        if(time == 0){
            Tclock.suspend();
            stopEntities();
            gameEnd = true;
            timer.stop();
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        this.repaint();
    }
}