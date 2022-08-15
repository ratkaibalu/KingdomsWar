package kingdomswar.model;
import static kingdomswar.controller.Game.*;
import kingdomswar.model.Player;

import javax.swing.*;
import java.awt.*;

public class Berzerk extends Soldier {
    public int step = 3;
    
    public Berzerk() {
        super(0,0);
    }
    public Berzerk(int x, int y, Image image) {
        super(x, y, image);
    }
    public Berzerk(int x, int y, boolean isRed, boolean redImage) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/berzerk.png").getImage());
        this.setHp(20);
    }
    public Berzerk(int x, int y, boolean isRed) {
        super(x,y, isRed, new ImageIcon("src/main/resources/images/blue_berzerk.png").getImage());
        this.setHp(20);
    }

    public void berzerkAttack(Player actualpl,Soldier soldier){


    }
}
