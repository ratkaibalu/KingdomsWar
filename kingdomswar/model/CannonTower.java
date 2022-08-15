package kingdomswar.model;

import java.awt.*;

public class CannonTower extends Tower {
    public CannonTower(int x, int y, Image image, boolean isRed) {
        super(x, y, image, isRed);
        this.setHp(10);
        this.setTowerDmg(10);
    }
}
