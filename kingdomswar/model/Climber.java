package kingdomswar.model;

import javax.swing.*;
import java.awt.*;

public class Climber extends Soldier {
    public int step = 3;

    public Climber() {
        super(0,0);
    }
    public Climber(int x, int y, Image image) {
        super(x, y, image);
    }
    public Climber(int x, int y, boolean isRed, boolean redImage) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/climber.png").getImage());
        this.setHp(20);
    }
    public Climber(int x, int y, boolean isRed) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/blue_climber.png").getImage());
        this.setHp(20);
    }
}
