package kingdomswar.model;

import kingdomswar.view.KingdomsWarGUI;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int gold;
    private boolean redPlayer;
    private Castle castle;
    private List<Tower> towers;
    private Barrack barrack1;
    private Barrack barrack2;
    private List<Soldier> soldiers;
    private int TowerLevel;
    private int TowerUpgradePrice;
    private int maxTower;
    private int maxSoldier;
    //temp
    private boolean hasBarrack1;
    private boolean HasBarrack2;

    public int getArcherDmg() {
        return archerDmg;
    }

    public int getCannonDmg() {
        return cannonDmg;
    }

    public int getZapDmg() {
        return zapDmg;
    }

    private int archerDmg;
    private int cannonDmg;
    private int zapDmg;


    public Player(boolean redPlayer, Sprite castle) {
        this.castle = (Castle) castle;
        this.gold = 10000;
        this.castle.setHp(100);
        this.redPlayer = redPlayer;
        //this.castle = new Castle;
        this.towers = new ArrayList<Tower>();
        this.soldiers = new ArrayList<Soldier>();
        this.maxTower = 10;
        this.maxSoldier = 12;
        this.hasBarrack1 = false;
        this.HasBarrack2 = false;
        this.TowerLevel = 1;
        this.TowerUpgradePrice = 150;
        this.archerDmg = 5;
        this.cannonDmg = 10;
        this.zapDmg = 25;
        this.barrack1 = null;
        this.barrack2 = null;
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public int getTowerLevel(){ return TowerLevel;}
    public int getTowerUpgradePrice() { return TowerUpgradePrice;}

    public void setTowerLevel() {
        TowerLevel = TowerLevel + 1;
    }
    public void setTowerUpgradePrice() {
        TowerUpgradePrice = TowerUpgradePrice + 50;
    }

    public void upgradeAllTowerDmg(){
        archerDmg = archerDmg + 1;
        cannonDmg = cannonDmg + 1;
        zapDmg = zapDmg + 1;
        for( Tower t : towers){
            if(t instanceof ArcherTower){
                t.upgradeTowerDmg(archerDmg);
            }else if(t instanceof CannonTower){
                t.upgradeTowerDmg(cannonDmg);
            }else if(t instanceof  ZapTower){
                t.upgradeTowerDmg(zapDmg);
            }
            System.out.println(t.getTowerDmg());
        }
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public boolean HasBarrack1() {
        return hasBarrack1;
    }

    public boolean HasBarrack2() {
        return HasBarrack2;
    }

    public void setHasBarrack1(boolean hasBarrack1) {
        this.hasBarrack1 = hasBarrack1;
    }

    public void setHasBarrack2(boolean hasBarrack2) {
        HasBarrack2 = hasBarrack2;
    }

    public int getGold() {
        return gold;
    }

    public boolean isRedPlayer() {
        return redPlayer;
    }

    public int getTowersCount() {
        return towers.size();
    }

    public boolean towerIsNotMax() {
        if (towers.size() < maxTower) {
            return true;
        }
        return false;
    }

    public Barrack getBarrack1() {
        return barrack1;
    }

    public void setBarrack1(Barrack barrack1) {
        this.barrack1 = barrack1;
    }

    public void setBarrack2(Barrack barrack2) {
        this.barrack2 = barrack2;
    }

    public Barrack getBarrack2() {
        return barrack2;
    }

    public int getSoldiersCount() {
        return soldiers.size();
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setRedPlayer(boolean redPlayer) {this.redPlayer = redPlayer;}

    public boolean buyTower(int price) {
        if (this.gold >= price && towers.size() < maxTower) {
            this.gold -= price;
            return true;
        }
        return false;
    }


    public boolean buyBarrack(int price){
        if(this.gold >= price){
            this.gold -= price;
            return true;
        }
        return false;
    }

    public int getMaxTower() {
        return maxTower;
    }




    public int getMaxSoldier() {
        return maxSoldier;
    }
}
