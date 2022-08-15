package kingdomswar.view;

import kingdomswar.controller.Game;
import kingdomswar.controller.Logic;

import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  kingdomswar.controller.Logic;

public class KingdomsWarGUI {

    private JFrame frame;
    private static Game gameArea;
    JButton newGameButton = new JButton("New Game");
    private JPanel panel;
    private JPanel panelTop;
    private JPanel panelBottom;
    private JLabel backgroundImage = new JLabel();
    private static JLabel topLabel;
    //bottomPanel components
    private JPanel castlePanel;
    private JLabel castleText;
    private JPanel castleButtonPanel;
    private boolean isBarrack1Built = false;
    private JPanel barrack1Panel;
    private JLabel barrack1Text;
    private JPanel barrack1ButtonPanel;
    private boolean isBarrack2Built = false;
    private JPanel barrack2Panel;
    private JLabel barrack2Text;
    private JPanel barrack2ButtonPanel;
    private JPanel towerPanel;
    private JLabel towerText;
    private JLabel towerUpgradeText;
    private JPanel towerButtonPanel;
    private JPanel playerSwitchPanel;
    private JLabel playerSwitchText;
    private JPanel playerSwitchButtonPanel;
    private JButton barrack1Build;
    private JButton barrack2Build;
    private final int barrackPrice = 400;

    private boolean castleBuy = true;
    private boolean barrack1Buy = true;
    private boolean barrack2Buy = true;

    public KingdomsWarGUI() {
        frame = new JFrame("KingdomsWar");
        paintStartScreen();
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                //GAME AREA
                gameArea = new Game(frame);

                //PANEL TOP
                panelTop = new JPanel();
                panelTop.setPreferredSize(new Dimension(1210, 35));
                topLabel = new JLabel();
                refreshText();
                panelTop.add(topLabel);
                frame.add(panelTop, BorderLayout.NORTH);
                //--

                //PANEL BOTTOM
                //CASTLE
                panelBottom = new JPanel(new GridLayout(1,5));
                panelBottom.setPreferredSize(new Dimension(1210,150));
                castlePanel = new JPanel();
                castleText = new JLabel("Castle: 100");
                castlePanel.add(castleText, BorderLayout.NORTH);
                castleButtonPanel = new JPanel(new GridLayout(4, 2));

                JButton castleButtonKnight = new JButton("Knight");
                JButton castleButtonBerserk = new JButton("Berserk");
                JButton castleButtonDestroyer = new JButton("Destroyer");
                JButton castleButtonClimber = new JButton("Climber");

                JLabel knightPrice = new JLabel("    50     ");
                JLabel berserkPrice = new JLabel("    75     ");
                JLabel destroyerPrice = new JLabel("    120   ");
                JLabel climberPrice = new JLabel("    160   ");

                castleButtonKnight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(castleBuy){
                            castleButtonKnight.setBackground(Color.BLACK);
                            castleBuy = false;
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-50);
                            gameArea.trainSoldier("knight","castle", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                castleButtonBerserk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(castleBuy){
                            castleButtonBerserk.setBackground(Color.BLACK);
                            castleBuy = false;
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-75);
                            gameArea.trainSoldier("berzerk","castle", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                castleButtonDestroyer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(castleBuy){
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-120);
                            castleButtonDestroyer.setBackground(Color.BLACK);
                            castleBuy = false;
                            gameArea.trainSoldier("destroyer","castle", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                castleButtonClimber.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(castleBuy){
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-160);
                            castleButtonClimber.setBackground(Color.BLACK);
                            castleBuy = false;
                            gameArea.trainSoldier("climber","castle", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                if(castleBuy == false){
                    castleButtonClimber.removeActionListener(this);
                    castleButtonBerserk.removeActionListener(this);
                    castleButtonDestroyer.removeActionListener(this);
                    castleButtonKnight.removeActionListener(this);
                }
                castleButtonPanel.add(castleButtonKnight);
                castleButtonPanel.add(knightPrice);
                castleButtonPanel.add(castleButtonBerserk);
                castleButtonPanel.add(berserkPrice);
                castleButtonPanel.add(castleButtonDestroyer);
                castleButtonPanel.add(destroyerPrice);
                castleButtonPanel.add(castleButtonClimber);
                castleButtonPanel.add(climberPrice);


                castlePanel.add(castleButtonPanel);
                panelBottom.add(castlePanel);

                //BARRACK1
                barrack1Panel = new JPanel();
                barrack1Text = new JLabel("             Barrack 1            ");
                barrack1Panel.add(barrack1Text, BorderLayout.NORTH);
                barrack1ButtonPanel = new JPanel(new GridLayout(4, 2));
                barrack1Build = new JButton("Build barrack");
                JLabel knightPrice1 = new JLabel("    50");
                JLabel berserkPrice1 = new JLabel("    75");
                JLabel destroyerPrice1 = new JLabel("    120");
                JLabel climberPrice1 = new JLabel("    160");

                JButton barrack1ButtonKnight = new JButton("Knight");
                JButton barrack1ButtonBerserk = new JButton("Berserk");
                JButton barrack1ButtonDestroyer = new JButton("Destroyer");
                JButton barrack1ButtonClimber = new JButton("Climber");

                barrack1ButtonKnight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack1Buy){
                            barrack1ButtonKnight.setBackground(Color.BLACK);
                            barrack1Buy = false;
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-50);
                            gameArea.trainSoldier("knight","barrack1", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                barrack1ButtonBerserk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack1Buy){
                            barrack1ButtonBerserk.setBackground(Color.BLACK);
                            barrack1Buy = false;
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-75);
                            gameArea.trainSoldier("berzerk","barrack1", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                barrack1ButtonDestroyer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack1Buy){
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-120);
                            barrack1ButtonDestroyer.setBackground(Color.BLACK);
                            barrack1Buy = false;
                            gameArea.trainSoldier("destroyer","barrack1", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                barrack1ButtonClimber.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack1Buy){
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-160);
                            barrack1ButtonClimber.setBackground(Color.BLACK);
                            barrack1Buy = false;
                            gameArea.trainSoldier("climber","barrack1", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                if(barrack1Buy == false){
                    barrack1ButtonClimber.removeActionListener(this);
                    barrack1ButtonBerserk.removeActionListener(this);
                    barrack1ButtonDestroyer.removeActionListener(this);
                    barrack1ButtonKnight.removeActionListener(this);
                }

                barrack1ButtonPanel.add(barrack1Build);
                barrack1Build.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameArea.eventOnTowerButton("barrack1", e);
                        if (!gameArea.getPlayer().HasBarrack1()) {

                            barrack1ButtonPanel.removeAll();


                            barrack1ButtonPanel.add(barrack1ButtonKnight);
                            barrack1ButtonPanel.add(knightPrice1);
                            barrack1ButtonPanel.add(barrack1ButtonBerserk);
                            barrack1ButtonPanel.add(berserkPrice1);
                            barrack1ButtonPanel.add(barrack1ButtonDestroyer);
                            barrack1ButtonPanel.add(destroyerPrice1);
                            barrack1ButtonPanel.add(barrack1ButtonClimber);
                            barrack1ButtonPanel.add(climberPrice1);
                            gameArea.getPlayer().setHasBarrack1(true);


                        }
                    }

                });
                if(gameArea.getPlayer().HasBarrack1()){
                    barrack1Build.removeActionListener(this);
                }

                barrack1Panel.add(barrack1ButtonPanel);
                panelBottom.add(barrack1Panel);

                //BARRACK2
                barrack2Panel = new JPanel();
                barrack2Text = new JLabel("           Barrack 2             ");
                barrack2Panel.add(barrack2Text, BorderLayout.NORTH);
                barrack2ButtonPanel = new JPanel(new GridLayout(4,2 ));
                barrack2Build = new JButton("Build barrack");
                barrack2ButtonPanel.add(barrack2Build);
                JLabel knightPrice2 = new JLabel("    50");
                JLabel berserkPrice2 = new JLabel("    75");
                JLabel destroyerPrice2 = new JLabel("    120");
                JLabel climberPrice2 = new JLabel("    160");

                JButton barrack2ButtonKnight = new JButton("Knight");
                JButton barrack2ButtonBerserk = new JButton("Berserk");
                JButton barrack2ButtonDestroyer = new JButton("Destroyer");
                JButton barrack2ButtonClimber = new JButton("Climber");

                barrack2ButtonKnight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack2Buy){
                            barrack2ButtonKnight.setBackground(Color.BLACK);
                            barrack2Buy = false;
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-50);
                            gameArea.trainSoldier("knight","barrack2", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                barrack2ButtonBerserk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack2Buy){
                            barrack2ButtonBerserk.setBackground(Color.BLACK);
                            barrack2Buy = false;
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-75);
                            gameArea.trainSoldier("berzerk","barrack2", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                barrack2ButtonDestroyer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack2Buy){
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-120);
                            barrack2ButtonDestroyer.setBackground(Color.BLACK);
                            barrack2Buy = false;
                            gameArea.trainSoldier("destroyer","barrack2", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                barrack2ButtonClimber.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(barrack2Buy){
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold()-160);
                            barrack2ButtonClimber.setBackground(Color.BLACK);
                            barrack2Buy = false;
                            gameArea.trainSoldier("climber","barrack2", gameArea.getPlayer().isRedPlayer());
                            refreshText();
                        }
                    }
                });
                if(barrack2Buy == false){
                    barrack2ButtonClimber.removeActionListener(this);
                    barrack2ButtonBerserk.removeActionListener(this);
                    barrack2ButtonDestroyer.removeActionListener(this);
                    barrack2ButtonKnight.removeActionListener(this);
                }

                barrack2Build.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        gameArea.eventOnTowerButton("barrack2", e);
                        if (!gameArea.getPlayer().HasBarrack2()) {

                            barrack2ButtonPanel.remove(barrack2Build);

                            barrack2ButtonPanel.add(barrack2ButtonKnight);
                            barrack2ButtonPanel.add(knightPrice2);
                            barrack2ButtonPanel.add(barrack2ButtonBerserk);
                            barrack2ButtonPanel.add(berserkPrice2);
                            barrack2ButtonPanel.add(barrack2ButtonDestroyer);
                            barrack2ButtonPanel.add(destroyerPrice2);
                            barrack2ButtonPanel.add(barrack2ButtonClimber);
                            barrack2ButtonPanel.add(climberPrice2);

                            ;
                            gameArea.getPlayer().setHasBarrack2(true);
                        }
                    }

                });
                if(gameArea.getPlayer().HasBarrack2()){
                    barrack2Build.removeActionListener(this);
                }

                barrack2Panel.add(barrack2ButtonPanel);
                panelBottom.add(barrack2Panel);


                //TOWERS
                towerPanel = new JPanel();
                towerText = new JLabel("Towers");
                towerPanel.add(towerText, BorderLayout.NORTH);
                towerButtonPanel = new JPanel(new GridLayout(4, 2));

                JLabel archerTowerPrice = new JLabel("    140");
                JLabel cannonTowerPrice = new JLabel("    200");
                JLabel zapTowerPrice = new JLabel("    240");

                JButton archerTower = new JButton("Archer");
                JButton cannonTower = new JButton("Cannon");
                JButton zapTower = new JButton("Zap");
                JButton removeTower = new JButton("Remove");

                archerTower.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameArea.eventOnTowerButton("archer", e);
                    }
                });
                cannonTower.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameArea.eventOnTowerButton("cannon", e);
                    }
                });
                zapTower.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameArea.eventOnTowerButton("zap", e);
                    }
                });
                removeTower.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        gameArea.removeTower(e);
                    }
                });

                towerButtonPanel.add(archerTower);
                towerButtonPanel.add(archerTowerPrice);
                towerButtonPanel.add(cannonTower);
                towerButtonPanel.add(cannonTowerPrice);
                towerButtonPanel.add(zapTower);
                towerButtonPanel.add(zapTowerPrice);
                towerButtonPanel.add(removeTower);
                towerPanel.add(towerButtonPanel);
                panelBottom.add(towerPanel);

                //PLAYER_SWITCH
                playerSwitchPanel = new JPanel();
                playerSwitchText = new JLabel("Enemy Castle: " + gameArea.getEnemyPlayer().getCastle().getHp());
                playerSwitchButtonPanel = new JPanel(new GridLayout(2,1));
                JButton playerSwitchButton = new JButton("Switch");
                JPanel playerSwitchColor = new JPanel();
                playerSwitchColor.setBackground(Color.RED);
                JButton towerUpgradeButton = new JButton("Upgrade Tower");
                towerUpgradeText = new JLabel("Level : " + gameArea.getPlayer().getTowerLevel() + " Price : " + gameArea.getPlayer().getTowerUpgradePrice());

                towerUpgradeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameArea.getPlayer().getGold() >= gameArea.getPlayer().getTowerUpgradePrice()){
                            gameArea.getPlayer().setGold(gameArea.getPlayer().getGold() - gameArea.getPlayer().getTowerUpgradePrice());
                            gameArea.getPlayer().setTowerUpgradePrice();
                            gameArea.getPlayer().setTowerLevel();
                            gameArea.getPlayer().upgradeAllTowerDmg();
                        }
                        refreshUpgradeText();
                        refreshText();

                    }
                });
                //JLabel towerUpgradePrice = new JLabel("Price: 150");

                playerSwitchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(playerSwitchColor.getBackground() == Color.RED){
                            playerSwitchColor.setBackground(Color.BLUE);
                            gameArea.payBluePlayer();
                            gameArea.switchPlayer(gameArea.getPlayer());
                            castleButtonPanel.removeAll();
                            castleBuy = true;
                            barrack1Buy = true;
                            barrack2Buy = true;
                            castleButtonKnight.setBackground(null);
                            castleButtonBerserk.setBackground(null);
                            castleButtonDestroyer.setBackground(null);
                            castleButtonClimber.setBackground(null);
                            castleButtonPanel.repaint();
                            castleButtonPanel.add(castleButtonKnight);
                            castleButtonPanel.add(knightPrice);
                            castleButtonPanel.add(castleButtonBerserk);
                            castleButtonPanel.add(berserkPrice);
                            castleButtonPanel.add(castleButtonDestroyer);
                            castleButtonPanel.add(destroyerPrice);
                            castleButtonPanel.add(castleButtonClimber);
                            castleButtonPanel.add(climberPrice);

                            castlePanel.add(castleButtonPanel);
                            refreshText();
                            if(gameArea.getPlayer().HasBarrack1()){
                                barrack1ButtonPanel.removeAll();

                                barrack1ButtonPanel.add(barrack1ButtonKnight);
                                barrack1ButtonPanel.add(knightPrice1);
                                barrack1ButtonPanel.add(barrack1ButtonBerserk);
                                barrack1ButtonPanel.add(berserkPrice1);
                                barrack1ButtonPanel.add(barrack1ButtonDestroyer);
                                barrack1ButtonPanel.add(destroyerPrice1);
                                barrack1ButtonPanel.add(barrack1ButtonClimber);
                                barrack1ButtonPanel.add(climberPrice1);

                            }
                            else {
                                barrack1ButtonPanel.removeAll();
                                barrack1ButtonPanel.add(barrack1Build);
                            }
                            if(gameArea.getPlayer().HasBarrack2()){
                                barrack2ButtonPanel.removeAll();
                                barrack2ButtonPanel.add(barrack2ButtonKnight);
                                barrack2ButtonPanel.add(knightPrice2);
                                barrack2ButtonPanel.add(barrack2ButtonBerserk);
                                barrack2ButtonPanel.add(berserkPrice2);
                                barrack2ButtonPanel.add(barrack2ButtonDestroyer);
                                barrack2ButtonPanel.add(destroyerPrice2);
                                barrack2ButtonPanel.add(barrack2ButtonClimber);
                                barrack2ButtonPanel.add(climberPrice2);


                            } else {
                                barrack2ButtonPanel.removeAll();
                                barrack2ButtonPanel.add(barrack2Build);
                            }
                            barrack1ButtonPanel.repaint();
                            barrack2ButtonPanel.repaint();

                        }else if(playerSwitchColor.getBackground() == Color.BLUE){
                            gameArea.switchPlayer(gameArea.getPlayer());
                            playerSwitchColor.setBackground(Color.BLACK);

                            castleButtonPanel.removeAll();
                            castleBuy = false;

                            castleButtonKnight.setBackground(null);
                            castleButtonBerserk.setBackground(null);
                            castleButtonDestroyer.setBackground(null);
                            castleButtonClimber.setBackground(null);
                            castleButtonPanel.repaint();
                            castleButtonPanel.add(castleButtonKnight);
                            castleButtonPanel.add(knightPrice);
                            castleButtonPanel.add(castleButtonBerserk);
                            castleButtonPanel.add(berserkPrice);
                            castleButtonPanel.add(castleButtonDestroyer);
                            castleButtonPanel.add(destroyerPrice);
                            castleButtonPanel.add(castleButtonClimber);
                            castleButtonPanel.add(climberPrice);

                            //castlePanel.add(castleButtonPanel);

                            castleButtonPanel.removeAll();
                            barrack1ButtonPanel.removeAll();
                            barrack2ButtonPanel.removeAll();
                            //towerButtonPanel.removeAll();

                            barrack1ButtonPanel.repaint();
                            barrack2ButtonPanel.repaint();
                            refreshText();

                            //================Támadás logika================
                            gameArea.attack();





                        }else if(playerSwitchColor.getBackground() == Color.BLACK){
                            playerSwitchColor.setBackground(Color.RED);
                            gameArea.payRedPlayer();
                            castleButtonPanel.removeAll();
                            castleBuy = true;
                            barrack1Buy = true;
                            barrack2Buy = true;

                            castleButtonKnight.setBackground(null);
                            castleButtonBerserk.setBackground(null);
                            castleButtonDestroyer.setBackground(null);
                            castleButtonClimber.setBackground(null);
                            castleButtonPanel.repaint();
                            castleButtonPanel.add(castleButtonKnight);
                            castleButtonPanel.add(knightPrice);
                            castleButtonPanel.add(castleButtonBerserk);
                            castleButtonPanel.add(berserkPrice);
                            castleButtonPanel.add(castleButtonDestroyer);
                            castleButtonPanel.add(destroyerPrice);
                            castleButtonPanel.add(castleButtonClimber);
                            castleButtonPanel.add(climberPrice);

                            castlePanel.add(castleButtonPanel);
                            if(gameArea.getPlayer().HasBarrack1()){
                                barrack1ButtonPanel.removeAll();

                                barrack1ButtonPanel.add(barrack1ButtonKnight);
                                barrack1ButtonPanel.add(knightPrice1);
                                barrack1ButtonPanel.add(barrack1ButtonBerserk);
                                barrack1ButtonPanel.add(berserkPrice1);
                                barrack1ButtonPanel.add(barrack1ButtonDestroyer);
                                barrack1ButtonPanel.add(destroyerPrice1);
                                barrack1ButtonPanel.add(barrack1ButtonClimber);
                                barrack1ButtonPanel.add(climberPrice1);

                            }
                            else {
                                barrack1ButtonPanel.removeAll();
                                barrack1ButtonPanel.add(barrack1Build);
                            }
                            if(gameArea.getPlayer().HasBarrack2()){
                                barrack2ButtonPanel.removeAll();
                                barrack2ButtonPanel.add(barrack2ButtonKnight);
                                barrack2ButtonPanel.add(knightPrice2);
                                barrack2ButtonPanel.add(barrack2ButtonBerserk);
                                barrack2ButtonPanel.add(berserkPrice2);
                                barrack2ButtonPanel.add(barrack2ButtonDestroyer);
                                barrack2ButtonPanel.add(destroyerPrice2);
                                barrack2ButtonPanel.add(barrack2ButtonClimber);
                                barrack2ButtonPanel.add(climberPrice2);



                            } else {
                                barrack2ButtonPanel.removeAll();
                                barrack2ButtonPanel.add(barrack2Build);
                            }
                            barrack1ButtonPanel.repaint();
                            barrack2ButtonPanel.repaint();


                        }
                        refreshText();
                        refreshUpgradeText();

                    }
                });



                playerSwitchPanel.add(playerSwitchText, BorderLayout.NORTH);
                playerSwitchButtonPanel.add(playerSwitchButton);
                playerSwitchButtonPanel.add(playerSwitchColor);
                playerSwitchButtonPanel.add(towerUpgradeButton);
                playerSwitchButtonPanel.add(towerUpgradeText);
                playerSwitchPanel.add(playerSwitchButtonPanel);
                panelBottom.add(playerSwitchPanel);

                frame.add(panelBottom, BorderLayout.SOUTH);


                /////---

                //GAME AREA

                frame.getContentPane().add(gameArea);

                frame.setPreferredSize(new Dimension(1210, 1120));
                frame.setResizable(false);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    public static void refreshText(){
        topLabel.setText("GOLD: "+ gameArea.getPlayerRed().getGold() +"   SOLDIERS: "+ gameArea.getPlayerRed().getSoldiersCount()  +"/"+ gameArea.getPlayerRed().getMaxSoldier()+"     TOWERS: " + gameArea.getPlayerRed().getTowers().size() +"/"+gameArea.getPlayerRed().getMaxTower()+ "            |            TOWERS: " + gameArea.getPlayerBlue().getTowers().size() + "/"+  gameArea.getPlayerBlue().getMaxTower()  +"     SOLDIERS: "+gameArea.getPlayerBlue().getSoldiersCount() +"/"+ gameArea.getPlayerBlue().getMaxSoldier()+"     GOLD: "+ gameArea.getPlayerBlue().getGold());
    }

    public void refreshUpgradeText(){
        towerUpgradeText.setText("Level : " + gameArea.getPlayer().getTowerLevel() + " Price : " + gameArea.getPlayer().getTowerUpgradePrice());
    }

    private void paintStartScreen() {
        frame.setSize(800,600);
        backgroundImage.setIcon(new ImageIcon("src/main/resources/bg.jpg"));
        backgroundImage.setLayout(new BorderLayout());
        frame.add(backgroundImage);

        newGameButton.setPreferredSize(new Dimension(120,60));
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(290,0,10,0));
        panel.setOpaque(false);
        panel.add(newGameButton);

        backgroundImage.add(panel, BorderLayout.CENTER);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void barrackButtonPlayerSwitch() {

        barrack1ButtonPanel.remove(barrack1Build);
        JLabel knightPrice1 = new JLabel("    50");
        JLabel berserkPrice1 = new JLabel("    75");
        JLabel destroyerPrice1 = new JLabel("    120");
        JLabel climberPrice1 = new JLabel("    160");

        JButton barrack1ButtonKnight = new JButton("Knight");
        JButton barrack1ButtonBerserk = new JButton("Berserk");
        JButton barrack1ButtonDestroyer = new JButton("Destroyer");
        JButton barrack1ButtonClimber = new JButton("Climber");

        barrack1ButtonPanel.add(barrack1ButtonKnight);
        barrack1ButtonPanel.add(knightPrice1);
        barrack1ButtonPanel.add(barrack1ButtonBerserk);
        barrack1ButtonPanel.add(berserkPrice1);
        barrack1ButtonPanel.add(barrack1ButtonDestroyer);
        barrack1ButtonPanel.add(destroyerPrice1);
        barrack1ButtonPanel.add(barrack1ButtonClimber);
        barrack1ButtonPanel.add(climberPrice1);


        //barrack2ButtonPanel.removeAll();
        barrack2ButtonPanel.remove(barrack2Build);
        JLabel knightPrice2 = new JLabel("    50");
        JLabel berserkPrice2 = new JLabel("    75");
        JLabel destroyerPrice2 = new JLabel("    120");
        JLabel climberPrice2 = new JLabel("    160");

        JButton barrack2ButtonKnight = new JButton("Knight");
        JButton barrack2ButtonBerserk = new JButton("Berserk");
        JButton barrack2ButtonDestroyer = new JButton("Destroyer");
        JButton barrack2ButtonClimber = new JButton("Climber");

        barrack2ButtonPanel.add(barrack2ButtonKnight);
        barrack2ButtonPanel.add(knightPrice2);
        barrack2ButtonPanel.add(barrack2ButtonBerserk);
        barrack2ButtonPanel.add(berserkPrice2);
        barrack2ButtonPanel.add(barrack2ButtonDestroyer);
        barrack2ButtonPanel.add(destroyerPrice2);
        barrack2ButtonPanel.add(barrack2ButtonClimber);
        barrack2ButtonPanel.add(climberPrice2);



    }

    public void removeActionListener(ActionEvent e){

    }

}
