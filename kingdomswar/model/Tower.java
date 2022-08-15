package kingdomswar.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tower extends Sprite {
    private int dmg;
    private int price;
    private int radius;
    private int level;
    private boolean isRed;



    public Tower(int x, int y, Image image, boolean isRed)  {
        super(x, y, image);
        this.isRed = isRed;

    }

    public int getTowerDmg() {return dmg;}
    public void upgradeTowerDmg(int d) {
        dmg = d;
        try {
            writeHP();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setTowerDmg(int d) {dmg = d;}

    public boolean isRed() {
        return isRed;
    }

    public void writeHP() throws IOException, ClassNotFoundException {
        BufferedImage bufferedImage;
        if (this.getClass().equals(ArcherTower.class)) {
            if (isRed) {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/tower_red.png"));
            } else {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/tower_blue.png"));
            }
        }
        else if (this.getClass().equals(CannonTower.class)) {
            if (isRed) {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/cannon-red.png"));
            } else {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/cannon-blue.png"));
            }
        }
        else if (this.getClass().equals(ZapTower.class)) {
            if (isRed) {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/zap-red.png"));
            } else {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/zap-blue.png"));
            }
        }
        else {
            throw new ClassNotFoundException("Katona kép fájlja nem található a wirteHP metódusban!");
        }
        Graphics g = bufferedImage.getGraphics();
        g.setFont(g.getFont().deriveFont(25f));
        g.drawString("HP: " + this.getHp(), 20, 90);
        g.drawString("DMG: " + this.getTowerDmg(), 15, 120);
        g.dispose();
        ImageIO.write(bufferedImage, "png", new File("image1.png"));
        image = bufferedImage;
    }

}
