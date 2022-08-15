package kingdomswar.model;

import java.awt.*;

public class ZapTower extends Tower {
    public ZapTower(int x, int y, Image image, boolean isRed) {
        super(x, y, image, isRed);
        this.setHp(40);
        this.setTowerDmg(25);
    }
}
