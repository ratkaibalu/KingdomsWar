package kingdomswar.model;

import javax.swing.*;
import java.awt.*;

public class Grass extends Sprite {

    public Grass(int x, int y, Image image) {
        super(x,y, image);
    }

    public Grass(int x, int y) {
        super(x,y, new ImageIcon("src/main/resources/images/grass.png").getImage());
        isRed = false;
    }


}
