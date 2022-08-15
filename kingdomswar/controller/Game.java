package kingdomswar.controller;

import kingdomswar.model.*;
import kingdomswar.view.*;


import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static kingdomswar.controller.Logic.*;
import java.util.List;
import java.awt.Graphics;

import static java.lang.Math.floor;

public class Game extends JPanel {
    private Player playerBlue;
    private Player playerRed;
    private Player actualPlayer;
    private JMenuBar menuBar;
    private JFrame window;
    private JLabel statusText;

    private Image background;
    private LevelItem level;
    private final int rows = 11;
    private final int cols = 15;
    private final int sizeOfObjects = 80;
    //Game engine
    private long started;
    private final int FPS = 1;
    private Timer newFrameTimer;
    private int counter;
    private int blueKill = 0;
    private int redKill = 0;

    public LevelItem getLevel() {
        return level;
    }

    public Game(JFrame window) {
        super();
        newGame();
        repaint();
        playerBlue = new Player(false, level.getLevelStructure().get(5).get(14));
        playerRed = new Player(true, level.getLevelStructure().get(5).get(0));
        this.actualPlayer = playerRed;

        this.window = window;
        this.menuBar = window.getJMenuBar();
        this.statusText = new JLabel();

    }
    public Game() {
        newGame();
        playerBlue = new Player(false, (Castle) level.getLevelStructure().get(5).get(14));
        playerRed = new Player(true, (Castle) level.getLevelStructure().get(5).get(0));
        this.actualPlayer = playerRed;
    }

    public void payRedPlayer(){
        playerRed.setGold(playerRed.getGold() + playerRed.getSoldiers().size() * 60);
        playerRed.setGold(playerRed.getGold() + redKill * 150);
        redKill = 0;
    }
    public void payBluePlayer(){
        playerBlue.setGold(playerBlue.getGold() + playerBlue.getSoldiers().size() * 60);
        playerBlue.setGold(playerBlue.getGold() + blueKill * 150);
        blueKill = 0;
    }


    public void trainSoldier(String soldier, String obj, boolean isRed){
        if(soldier == "knight"){
            Knight knight = new Knight();
            if (obj== "castle") {
                if (isRed) {
                    knight = new Knight(1*sizeOfObjects,5*sizeOfObjects, true,true);
                } else {
                    knight = new Knight(13*sizeOfObjects,5*sizeOfObjects, false);
                }
                getPlayer().getCastle().setCurrentSoldier(knight);
            }
            else if(obj == "barrack1"){
                if (isRed) {
                    knight = new Knight(getPlayerRed().getBarrack1().getX()+1*sizeOfObjects,getPlayerRed().getBarrack1().getY(), true,true);
                } else {
                    knight = new Knight(getPlayerBlue().getBarrack1().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack1().getY(), false);
                }
                getPlayer().getBarrack1().setCurrentSoldier(knight);
            }
            else if(obj == "barrack2"){
                if (isRed) {
                    knight = new Knight(getPlayerRed().getBarrack2().getX()+1*sizeOfObjects,getPlayerRed().getBarrack2().getY(), true,true);
                } else {
                    knight = new Knight(getPlayerBlue().getBarrack2().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack2().getY(), false);
                }
                getPlayer().getBarrack2().setCurrentSoldier(knight);
            }
            getPlayer().getSoldiers().add(knight);
        }
        else if(soldier == "berzerk"){
            Berzerk berzerk = new Berzerk();
            if (obj== "castle") {
                if (isRed) {
                    berzerk = new Berzerk(1*sizeOfObjects,5*sizeOfObjects, true,true);
                } else {
                    berzerk = new Berzerk(13*sizeOfObjects,5*sizeOfObjects, false);
                }
                getPlayer().getCastle().setCurrentSoldier(berzerk);
            }
            else if(obj == "barrack1"){
                if (isRed) {
                    berzerk = new Berzerk(getPlayerRed().getBarrack1().getX()+1*sizeOfObjects,getPlayerRed().getBarrack1().getY(), true,true);
                } else {
                    berzerk = new Berzerk(getPlayerBlue().getBarrack1().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack1().getY(), false);
                }
                getPlayer().getBarrack1().setCurrentSoldier(berzerk);
            }
            else if(obj == "barrack2"){
                if (isRed) {
                    berzerk = new Berzerk(getPlayerRed().getBarrack2().getX()+1*sizeOfObjects,getPlayerRed().getBarrack2().getY(), true,true);
                } else {
                    berzerk = new Berzerk(getPlayerBlue().getBarrack2().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack2().getY(), false);
                }
                getPlayer().getBarrack2().setCurrentSoldier(berzerk);
            }
            getPlayer().getSoldiers().add(berzerk);
        }
        else if(soldier == "destroyer"){
            Destroyer destroyer = new Destroyer();
            if (obj== "castle") {
                if (isRed) {
                    destroyer = new Destroyer(1*sizeOfObjects,5*sizeOfObjects, true,true);
                } else {
                    destroyer = new Destroyer(13*sizeOfObjects,5*sizeOfObjects, false);
                }
                getPlayer().getCastle().setCurrentSoldier(destroyer);
            }
            else if(obj == "barrack1"){
                if (isRed) {
                    destroyer = new Destroyer(getPlayerRed().getBarrack1().getX()+1*sizeOfObjects,getPlayerRed().getBarrack1().getY(), true,true);
                } else {
                    destroyer = new Destroyer(getPlayerBlue().getBarrack1().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack1().getY(), false);
                }
                getPlayer().getBarrack1().setCurrentSoldier(destroyer);
            }
            else if(obj == "barrack2"){
                if (isRed) {
                    destroyer = new Destroyer(getPlayerRed().getBarrack2().getX()+1*sizeOfObjects,getPlayerRed().getBarrack2().getY(), true,true);
                } else {
                    destroyer = new Destroyer(getPlayerBlue().getBarrack2().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack2().getY(), false);
                }
                getPlayer().getBarrack2().setCurrentSoldier(destroyer);
            }
            getPlayer().getSoldiers().add(destroyer);
        }
        else if(soldier == "climber"){
            Climber climber = new Climber();
            if (obj== "castle") {
                if (isRed) {
                    climber = new Climber(1*sizeOfObjects,5*sizeOfObjects, true,true);
                } else {
                    climber = new Climber(13*sizeOfObjects,5*sizeOfObjects, false);
                }
                getPlayer().getCastle().setCurrentSoldier(climber);
            }
            else if(obj == "barrack1"){
                if (isRed) {
                    climber = new Climber(getPlayerRed().getBarrack1().getX()+1*sizeOfObjects,getPlayerRed().getBarrack1().getY(), true,true);
                } else {
                    climber = new Climber(getPlayerBlue().getBarrack1().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack1().getY(), false);
                }
                getPlayer().getBarrack1().setCurrentSoldier(climber);
            }
            else if(obj == "barrack2"){
                if (isRed) {
                    climber = new Climber(getPlayerRed().getBarrack2().getX()+1*sizeOfObjects,getPlayerRed().getBarrack2().getY(), true,true);
                } else {
                    climber = new Climber(getPlayerBlue().getBarrack2().getX()-1*sizeOfObjects,getPlayerBlue().getBarrack2().getY(), false);
                }
                getPlayer().getBarrack2().setCurrentSoldier(climber);
            }
            getPlayer().getSoldiers().add(climber);
        }
    }

    public void switchPlayer(Player actPlayer){
        if(actPlayer == playerRed){
            actualPlayer = playerBlue;
            //KingdomsWarGUI.refreshText();
        }else if(actPlayer == playerBlue){
            actualPlayer = playerRed;
            //KingdomsWarGUI.refreshText();
        }
    }
    public Player getPlayerBlue(){return playerBlue;}
    public Player getPlayerRed(){return playerRed;}
    public Player getEnemyPlayer() {
        if (actualPlayer == playerRed) {
            return playerBlue;
        } else {
            return playerRed;
        }
    }
    public Player getPlayer(){return actualPlayer;}

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                try {
                    level.getLevelStructure().get(i).get(j).draw(grphcs);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void eventOnTowerButton(String towerType, ActionEvent e) {
        Image grassImg = new ImageIcon("src/main/resources/images/grass_border.png").getImage();
        //if has enough gold
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (level.getLevelStructure().get(i).get(j) instanceof Grass) {
                    level.getLevelStructure().get(i).get(j).setImage(grassImg); //négyzethálós lesz a pálya

                }
            }
        }
        window.repaint();
        boardEvent(towerType, e);
    }

    public void boardEvent(String type, ActionEvent e) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedX = (int) (floor(e.getX() / sizeOfObjects)); //x y koordináta alapján beméri, hogy hova kattint a felhasználó, és kikeresi a listából az adott objectet az indexek alapján
                int clickedY = (int) (floor(e.getY() / sizeOfObjects));
                boolean towerIsPlaced = false;
                boolean barrackIsPlaced = false;


                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (clickedX == level.getLevelStructure().get(i).get(j).getX() / sizeOfObjects && clickedY == level.getLevelStructure().get(i).get(j).getY() / sizeOfObjects) {
                            if (level.getLevelStructure().get(i).get(j) instanceof Grass) {
                                //if (towerType == "archer" && player == red)   //ilyen és ehhez hasonló if ágak a képekkel majd kellenek
                                Image towerImg = new ImageIcon("src/main/resources/images/tower_red.png").getImage();
                                Image barrackImg;
                                if (type == "archer") {
                                    if (actualPlayer.buyTower(140)) {
                                        if (actualPlayer == playerRed && getPlayerRed().towerIsNotMax()) {
                                            towerImg = new ImageIcon("src/main/resources/images/tower_red.png").getImage();
                                            level.getLevelStructure().get(i).remove(j);
                                            Tower newTower = new ArcherTower(j * sizeOfObjects, i * sizeOfObjects, towerImg, true);
                                            level.getLevelStructure().get(i).add(j, newTower);
                                            actualPlayer.getTowers().add(newTower);
                                        } else if (getPlayerBlue().towerIsNotMax()){
                                            towerImg = new ImageIcon("src/main/resources/images/tower_blue.png").getImage();
                                            level.getLevelStructure().get(i).remove(j);
                                            Tower newTower = new ArcherTower(j * sizeOfObjects, i * sizeOfObjects, towerImg, false);
                                            level.getLevelStructure().get(i).add(j, newTower);
                                            actualPlayer.getTowers().add(newTower);
                                        }
                                        towerIsPlaced = true;
                                    }
                                } else if (type == "cannon") {
                                    if (actualPlayer.buyTower(200)) {
                                        if (actualPlayer == playerRed && getPlayerRed().towerIsNotMax()) {
                                            towerImg = new ImageIcon("src/main/resources/images/cannon-red.png").getImage();
                                            level.getLevelStructure().get(i).remove(j);
                                            Tower newTower = new CannonTower(j * sizeOfObjects, i * sizeOfObjects, towerImg, true);
                                            level.getLevelStructure().get(i).add(j, newTower);
                                            actualPlayer.getTowers().add(newTower);
                                        } else if (getPlayerBlue().towerIsNotMax()) {
                                            towerImg = new ImageIcon("src/main/resources/images/cannon-blue.png").getImage();
                                            level.getLevelStructure().get(i).remove(j);
                                            Tower newTower = new CannonTower(j * sizeOfObjects, i * sizeOfObjects, towerImg, false);
                                            level.getLevelStructure().get(i).add(j, newTower);
                                            actualPlayer.getTowers().add(newTower);
                                        }
                                        towerIsPlaced = true;
                                    }
                                } else if (type == "zap") {
                                    if (actualPlayer.buyTower(240)) {
                                        if (actualPlayer == playerRed && getPlayerRed().towerIsNotMax()) {
                                            towerImg = new ImageIcon("src/main/resources/images/zap-red.png").getImage();
                                            level.getLevelStructure().get(i).remove(j);
                                            Tower newTower = new ZapTower(j * sizeOfObjects, i * sizeOfObjects, towerImg, true);
                                            level.getLevelStructure().get(i).add(j, newTower);
                                            actualPlayer.getTowers().add(newTower);
                                        } else if (getPlayerBlue().towerIsNotMax()) {
                                            towerImg = new ImageIcon("src/main/resources/images/zap-blue.png").getImage();
                                            level.getLevelStructure().get(i).remove(j);
                                            Tower newTower = new ZapTower(j * sizeOfObjects, i * sizeOfObjects, towerImg, false);
                                            level.getLevelStructure().get(i).add(j, newTower);
                                            actualPlayer.getTowers().add(newTower);
                                        }
                                        towerIsPlaced = true;
                                    }
                                } else if(type == "barrack1") {
                                    if (actualPlayer.buyBarrack(420)) {
                                        if (actualPlayer == playerRed) {
                                            if(actualPlayer.getBarrack1() == null || actualPlayer.getBarrack2() == null) {
                                                barrackImg = new ImageIcon("src/main/resources/images/barrack_red.png").getImage();
                                                level.getLevelStructure().get(i).remove(j);
                                                Barrack newBarrack = new Barrack(j * sizeOfObjects, i * sizeOfObjects, barrackImg, true, 1);
                                                level.getLevelStructure().get(i).add(j, newBarrack);
                                                actualPlayer.setBarrack1(newBarrack);

                                                barrackIsPlaced = true;
                                            }

                                        } else if (actualPlayer == playerBlue) {
                                            if (actualPlayer.getBarrack1() == null || actualPlayer.getBarrack2() == null) {
                                                barrackImg = new ImageIcon("src/main/resources/images/barrack_blue.png").getImage();
                                                level.getLevelStructure().get(i).remove(j);
                                                Barrack newBarrack = new Barrack(j * sizeOfObjects, i * sizeOfObjects, barrackImg, false, 1);
                                                level.getLevelStructure().get(i).add(j, newBarrack);
                                                actualPlayer.setBarrack1(newBarrack);
                                                barrackIsPlaced = true;

                                            }
                                        }

                                    }
                                } else if(type == "barrack2") {
                                    if (actualPlayer.buyBarrack(420)) {
                                        if (actualPlayer == playerRed) {
                                            if(actualPlayer.getBarrack1() == null || actualPlayer.getBarrack2() == null) {
                                                barrackImg = new ImageIcon("src/main/resources/images/barrack_red.png").getImage();
                                                level.getLevelStructure().get(i).remove(j);
                                                Barrack newBarrack = new Barrack(j * sizeOfObjects, i * sizeOfObjects, barrackImg, true, 2);
                                                level.getLevelStructure().get(i).add(j, newBarrack);
                                                actualPlayer.setBarrack2(newBarrack);

                                                barrackIsPlaced = true;
                                            }

                                        } else if (actualPlayer == playerBlue) {
                                            if (actualPlayer.getBarrack1() == null || actualPlayer.getBarrack2() == null) {
                                                barrackImg = new ImageIcon("src/main/resources/images/barrack_blue.png").getImage();
                                                level.getLevelStructure().get(i).remove(j);
                                                Barrack newBarrack = new Barrack(j * sizeOfObjects, i * sizeOfObjects, barrackImg, false, 2);
                                                level.getLevelStructure().get(i).add(j, newBarrack);
                                                actualPlayer.setBarrack2(newBarrack);
                                                barrackIsPlaced = true;
                                            }
                                        }

                                    }
                                }
                                repaint();
                                KingdomsWarGUI.refreshText();
                                //gold levonódik
                            } else {
                                //some kind of error
                            }
                        }
                    }
                }
                if(towerIsPlaced) {
                    Image grassImg = new ImageIcon("src/main/resources/images/grass.png").getImage();
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            if (level.getLevelStructure().get(i).get(j) instanceof Grass) {
                                level.getLevelStructure().get(i).get(j).setImage(grassImg);
                            }
                        }
                    }
                    removeMouseListener(this);
                }
                if(barrackIsPlaced) {
                    Image grassImg = new ImageIcon("src/main/resources/images/grass.png").getImage();
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            if (level.getLevelStructure().get(i).get(j) instanceof Grass) {
                                level.getLevelStructure().get(i).get(j).setImage(grassImg);
                            }
                        }
                    }
                    removeMouseListener(this);
                }
                window.repaint();
            }
        });
    }
    public void removeTower(ActionEvent e){

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedX = (int) (floor(e.getX() / sizeOfObjects));
                int clickedY = (int) (floor(e.getY() / sizeOfObjects));

                for(int i = 0; i < rows; i++){
                    for(int j = 0; j < cols; j++){
                        if(clickedX == level.getLevelStructure().get(i).get(j).getX() / sizeOfObjects && clickedY == level.getLevelStructure().get(i).get(j).getY() / sizeOfObjects){
                            if(level.getLevelStructure().get(i).get(j) instanceof Tower){
                                //csak a sajat tornyat torolhesse
                                if (actualPlayer == playerRed && ((Tower) level.getLevelStructure().get(i).get(j)).isRed() || actualPlayer == playerBlue && !((Tower) level.getLevelStructure().get(i).get(j)).isRed()) {
                                    actualPlayer.getTowers().remove(level.getLevelStructure().get(i).get(j));
                                    Image grassImg = new ImageIcon("src/main/resources/images/grass.png").getImage();
                                    level.getLevelStructure().get(i).remove(j);
                                    KingdomsWarGUI.refreshText();
                                    level.getLevelStructure().get(i).add(j, new Grass(j * sizeOfObjects, i * sizeOfObjects, grassImg));
                                    //torony árának a felét visszakapja az adott player
                                }
                            }
                        }
                    }
                }
                removeMouseListener(this);
                window.repaint();
            }
        });
    }

    public void newGame() {
        try {
            level = new LevelItem("src/main/resources/level00.txt");
            this.repaint();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void readLevels() {
        InputStream is;
        is = ResourceLoader.loadResource("resource/level00.txt");
        try (Scanner sc = new Scanner(is)){
            String line = readNextLine(sc);
            ArrayList<String> gameLevelRows = new ArrayList<>();
            while (!line.isEmpty()){

                gameLevelRows.clear();
                line = readNextLine(sc);
                while (!line.isEmpty() && line.trim().charAt(0) != ';'){
                    gameLevelRows.add(line);
                    line = readNextLine(sc);
                }
                // addNewGameLevel(new GameLevel(gameLevelRows, id));
            }
        } catch (Exception e){
            System.out.println("Ajaj");
        }
    }

    private String readNextLine(Scanner sc){
        String line = "";
        while (sc.hasNextLine() && line.trim().isEmpty()){
            line = sc.nextLine();
        }
        return line;
    }

    public void attack() {
        Logic logic = new Logic();

        //System.out.println(logic.BFS(level.getLevelStructure(),new Soldier(5*80,1*80, true)));

        //====== Kastelyokban és Barrakkokban kepzett katonak letrehozasa
        //castles
        //player red
        if (getPlayerRed().getCastle().getCurrentSoldier() != null) {
            level.getLevelStructure().get(5).remove(1);
            level.getLevelStructure().get(5).add(1, getPlayerRed().getCastle().getCurrentSoldier());
            getPlayerRed().getCastle().setCurrentSoldier(null);
        }
        //player blue
        if (getPlayerBlue().getCastle().getCurrentSoldier() != null) {
            level.getLevelStructure().get(5).remove(13);
            level.getLevelStructure().get(5).add(13, getPlayerBlue().getCastle().getCurrentSoldier());
            getPlayerBlue().getCastle().setCurrentSoldier(null);
        }
        //barrack1
        //player red
        if (getPlayerRed().getBarrack1() != null) {
            Point redBarrack1 = new Point(getPlayerRed().getBarrack1().getX() / sizeOfObjects, getPlayerRed().getBarrack1().getY() / sizeOfObjects);
            if (getPlayerRed().getBarrack1().getCurrentSoldier() != null) {
                level.getLevelStructure().get(redBarrack1.getY()).remove(redBarrack1.getX()+1);
                level.getLevelStructure().get(redBarrack1.getY()).add(redBarrack1.getX()+1, getPlayerRed().getBarrack1().getCurrentSoldier());
                getPlayerRed().getBarrack1().setCurrentSoldier(null);
            }
        }
        //player blue
        if (getPlayerBlue().getBarrack1() != null) {
            Point blueBarrack1 = new Point(getPlayerBlue().getBarrack1().getX() / sizeOfObjects, getPlayerBlue().getBarrack1().getY() / sizeOfObjects);
            if (getPlayerBlue().getBarrack1().getCurrentSoldier() != null) {
                level.getLevelStructure().get(blueBarrack1.getY()).remove(blueBarrack1.getX()-1);
                level.getLevelStructure().get(blueBarrack1.getY()).add(blueBarrack1.getX()-1, getPlayerBlue().getBarrack1().getCurrentSoldier());
                getPlayerBlue().getBarrack1().setCurrentSoldier(null);
            }
        }
        //barrack2
        //player red
        if (getPlayerRed().getBarrack2() != null){
            Point redBarrack2 = new Point(getPlayerRed().getBarrack2().getX() / sizeOfObjects, getPlayerRed().getBarrack2().getY() / sizeOfObjects);
            if (getPlayerRed().getBarrack2().getCurrentSoldier() != null) {
                level.getLevelStructure().get(redBarrack2.getY()).remove(redBarrack2.getX()+1);
                level.getLevelStructure().get(redBarrack2.getY()).add(redBarrack2.getX()+1, getPlayerRed().getBarrack2().getCurrentSoldier());
                getPlayerRed().getBarrack2().setCurrentSoldier(null);
            }
        }
        //player blue
        if (getPlayerBlue().getBarrack2() != null) {
            Point blueBarrack2 = new Point(getPlayerBlue().getBarrack2().getX() / sizeOfObjects, getPlayerBlue().getBarrack2().getY() / sizeOfObjects);
            if (getPlayerBlue().getBarrack2().getCurrentSoldier() != null) {
                level.getLevelStructure().get(blueBarrack2.getY()).remove(blueBarrack2.getX()-1);
                level.getLevelStructure().get(blueBarrack2.getY()).add(blueBarrack2.getX()-1, getPlayerBlue().getBarrack2().getCurrentSoldier());
                getPlayerBlue().getBarrack2().setCurrentSoldier(null);
            }
        }



        //DEMO egységek
//        level.getLevelStructure().get(5).remove(1);
//        Knight knight = new Knight(1*sizeOfObjects,5*sizeOfObjects, true,true);
//        level.getLevelStructure().get(5).add(1, knight);
//        getPlayerRed().getSoldiers().add(knight);
//
//        level.getLevelStructure().get(5).remove(13);
//        Knight knight2 = new Knight(13*sizeOfObjects,5*sizeOfObjects, false);
//        level.getLevelStructure().get(5).add(13, knight2);
//        getPlayerBlue().getSoldiers().add(knight2);


        repaint();
        newFrameTimer = new Timer(1000 , new NewFrameListener());
        newFrameTimer.start();
        counter = 0;
        this.started = System.currentTimeMillis();
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - started;
            Time time = new Time(elapsed);
            time.setHours(time.getHours() - 1);
            try {
                step(counter);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            counter++;
            repaint();
            if(counter == 4){
                newFrameTimer.stop();
            }
        }
    }

    private void step(int counter) throws IOException, ClassNotFoundException {

        if (getPlayerRed().getCastle().getHp() <= 0 || getPlayerBlue().getCastle().getHp() <=0) {
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Játék vége!");
        }

        if(getPlayerRed().getSoldiers().size() > 0) {
            for (int i = 0; i < getPlayerRed().getSoldiers().size(); i++) {
                int j = playerRed.getSoldiers().get(i).getX()/80;
                int y = playerRed.getSoldiers().get(i).getY()/80;
                Logic logic = new Logic();
                List<Point> ptList = logic.BFS(level.getLevelStructure(), getPlayerRed().getSoldiers().get(i));
                ptList.remove(0);
                if (ptList.get(0).getY() < j) {
                    ptList.remove(0);
                }
                if (ptList.size() > 0) {
                    if (ptList.size() == 1) {
                        getPlayerBlue().getCastle().setHp(getPlayerBlue().getCastle().getHp() - 5);
                        getPlayerBlue().getCastle().writeHP();
                        level.getLevelStructure().get(getPlayerRed().getSoldiers().get(i).getY() / 80).set(getPlayerRed().getSoldiers().get(i).getX() / 80, new Grass(getPlayerRed().getSoldiers().get(i).getX(), getPlayerRed().getSoldiers().get(i).getY()));
                        getPlayerRed().getSoldiers().remove(i);
                        System.out.println("HP: " + getPlayerRed().getCastle().getHp());
                    } else {
                        getPlayerRed().getSoldiers().get(i).move(ptList.get(0), level, getPlayerRed(), getPlayerBlue());
                        repaint();
                        if (getPlayerRed().getSoldiers().size() > 0) {
                            if (getPlayerRed().getSoldiers().get(i).getHp() <= 0 && getPlayerRed().getSoldiers().size() > 0 ) {
                                level.getLevelStructure().get(getPlayerRed().getSoldiers().get(i).getY() / 80).set(getPlayerRed().getSoldiers().get(i).getX() / 80, new Grass(getPlayerRed().getSoldiers().get(i).getX(), getPlayerRed().getSoldiers().get(i).getY()));
                                getPlayerRed().getSoldiers().remove(i);
                                blueKill++;
                            }
                        }
                        if(getPlayerRed().getSoldiers().size() > 0) {
                            getPlayerRed().getSoldiers().get(i).writeHP();
                        }
                    }
                }


                repaint();
            }
            repaint();
        }

        if(getPlayerBlue().getSoldiers().size() > 0) {
            for (int i = 0; i < getPlayerBlue().getSoldiers().size(); i++) {
                int j = playerBlue.getSoldiers().get(i).getX()/80;
                int y = playerBlue.getSoldiers().get(i).getY()/80;

                Logic logic = new Logic();
                List<Point> ptList = logic.BFS(level.getLevelStructure(), getPlayerBlue().getSoldiers().get(i));
                ptList.remove(0);
                if (ptList.get(0).getY() > j) {
                    ptList.remove(0);
                }
                if (ptList.size() > 0) {
                    if (ptList.size() == 1) {
                        getPlayerRed().getCastle().setHp(getPlayerRed().getCastle().getHp() - 5);
                        getPlayerRed().getCastle().writeHP();
                        level.getLevelStructure().get(getPlayerBlue().getSoldiers().get(i).getY() / 80)
                                .set(getPlayerBlue().getSoldiers().get(i).getX() / 80, new Grass(getPlayerBlue().getSoldiers().get(i).getX(), getPlayerBlue().getSoldiers().get(i).getY()));
                        getPlayerBlue().getSoldiers().remove(i);
                    } else {
                        getPlayerBlue().getSoldiers().get(i).move(ptList.get(0), level, getPlayerBlue(), getPlayerRed());
                        repaint();
                        if (getPlayerBlue().getSoldiers().size() > 0) {
                            if (getPlayerBlue().getSoldiers().get(i).getHp() <= 0 && getPlayerBlue().getSoldiers().size() > 0 ) {
                                level.getLevelStructure().get(getPlayerBlue().getSoldiers().get(i).getY() / 80)
                                        .set(getPlayerBlue().getSoldiers().get(i).getX() / 80, new Grass(getPlayerBlue().getSoldiers().get(i).getX(), getPlayerBlue().getSoldiers().get(i).getY()));
                                getPlayerBlue().getSoldiers().remove(i);
                                redKill++;
                            }
                        }
                        if(getPlayerBlue().getSoldiers().size() > 0) {
                            getPlayerBlue().getSoldiers().get(i).writeHP();
                        }
                    }
                }
                repaint();
            }
            repaint();
        }

    }

}


