package kingdomswar.model;
import static kingdomswar.model.LevelItem.*;
import static kingdomswar.controller.Logic.*;
import javax.swing.*;
import java.awt.Image;
import java.util.List;


public class Knight extends Soldier {
    public int step = 2;
    Image image = new ImageIcon("src/main/resources/images/knight.png").getImage();

    public Knight() {
        super(0,0);
    }
    public Knight(int x, int y, Image image) {
        super(x, y, image);
    }
    public Knight(int x, int y, boolean isRed, boolean redImage) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/knight.png").getImage());
        this.setHp(20);
    }
    public Knight(int x, int y, boolean isRed) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/blue_knight.png").getImage());
        this.setHp(20);
    }


}
