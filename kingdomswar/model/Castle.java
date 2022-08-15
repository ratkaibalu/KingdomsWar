package kingdomswar.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Castle extends Sprite {

    private boolean isRed;
    private Soldier currentSoldier;

    public Castle(int x, int y, Image image, boolean isRed) {
        super(x, y, image);
        this.isRed = isRed;
    }
    public void setCurrentSoldier(Soldier soldier){ currentSoldier = soldier;}

    public Soldier getCurrentSoldier() {
        return currentSoldier;
    }

    public void writeHP() throws IOException {
        BufferedImage bufferedImage;
        if (isRed) {
            bufferedImage = ImageIO.read(new File("src/main/resources/images/castle_red.png"));
        } else {
            bufferedImage = ImageIO.read(new File("src/main/resources/images/castle_blue.png"));
        }
        Graphics g = bufferedImage.getGraphics();
        g.setFont(g.getFont().deriveFont(25f));
        g.drawString("HP: " + this.getHp(), 15, 30);
        g.dispose();
        ImageIO.write(bufferedImage, "png", new File("image1.png"));
        image = bufferedImage;
    }
}
