package kingdomswar.model;

import javax.swing.*;
import java.awt.*;

public class Destroyer extends Soldier {
    public int step = 4;

    public Destroyer() {
        super(0,0);
    }
    public Destroyer(int x, int y, Image image) {
        super(x, y, image);
    }
    public Destroyer(int x, int y, boolean isRed, boolean redImage) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/destroyer.png").getImage());
        this.setHp(20);
    }
    public Destroyer(int x, int y, boolean isRed) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/blue_destroyer.png").getImage());
        this.setHp(20);
    }
}
