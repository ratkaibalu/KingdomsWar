package kingdomswar.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArcherTower extends Tower {

    public ArcherTower(int x, int y, Image image, boolean isRed) {
        super(x, y, image, isRed);
        this.setHp(20);
        this.setTowerDmg(5);
    }
}
