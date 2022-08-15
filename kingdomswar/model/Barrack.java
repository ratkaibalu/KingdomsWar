package kingdomswar.model;

import java.awt.*;
import kingdomswar.model.*;

import java.util.ArrayList;
import java.util.List;

public class Barrack extends Sprite{
    private int price;
    private boolean isRed;
    private int barrackID;
    private Soldier currentSoldier;


    public Barrack(int x, int y, Image image, boolean isRed, int barrackID) {
        super(x, y, image);
        this.isRed = isRed;
        this.barrackID = barrackID;
    }

    public int getID (){
        return barrackID;
    }

    public Soldier getCurrentSoldier() {
        return currentSoldier;
    }

    public void setCurrentSoldier(Soldier currentSoldier) {
        this.currentSoldier = currentSoldier;
    }
}

