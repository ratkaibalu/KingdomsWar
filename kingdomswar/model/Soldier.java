package kingdomswar.model;

import javax.imageio.ImageIO;

import javax.swing.*;

import static kingdomswar.controller.Logic.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import  static kingdomswar.controller.Logic.*;

public class Soldier extends Sprite{
    private int move;
    private int dmg;
    private int price;
    private boolean isRed;

    public Soldier(int x, int y) {
        super(x, y);
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public Soldier(int x, int y, boolean isRed, Image image) {
        super(x, y, image);
        this.isRed = isRed;
    }
    public Soldier(int x, int y, boolean isRed) {
        super(x, y);
        this.isRed = isRed;
    }

    public Soldier(int x, int y, Image image) {
        super(x, y, image);
    }

    public boolean isRed() {
        return isRed;
    }
    public void move(Point pt, LevelItem level, Player player, Player enemy) throws IOException {

            if ((level.getLevelStructure().get(pt.getX()).get(pt.getY()) instanceof Tower && this instanceof Destroyer) && ((level.getLevelStructure().get(pt.getX()).get(pt.getY()).isRed() && !this.isRed()) || (!level.getLevelStructure().get(pt.getX()).get(pt.getY()).isRed() && this.isRed()))) {
                player.getSoldiers().remove(level.getLevelStructure().get(this.getY()/80).get(this.getX()/80));
                player.getTowers().remove(level.getLevelStructure().get(pt.getX()).get(pt.getY()));
                level.getLevelStructure().get(pt.getX()).set(pt.getY(), new Grass(pt.getY()*80,pt.getX()*80));
                level.getLevelStructure().get(this.getY()/80).set(this.getX()/80,new Grass(this.getX(),this.getY()));
            }

            if (!(level.getLevelStructure().get(pt.getX()).get(pt.getY()) instanceof Soldier)) {
                Sprite knight = level.getLevelStructure().get(this.getY() / 80).get(this.getX() / 80);
                Sprite grass = level.getLevelStructure().get(pt.getX()).get(pt.getY());
                level.getLevelStructure().get(this.getY() / 80).set(this.getX() / 80, grass);
                grass.setX(this.getX());
                grass.setY(this.getY());

                level.getLevelStructure().get(pt.getX()).set(pt.getY(), knight);
                knight.setX(pt.getY() * 80);
                knight.setY(pt.getX() * 80);
                if (pt.getX() < 13 && pt.getY() > 1 && pt.getX() > 1 && pt.getY() < 10) {
                    radius1Attack(level,player,enemy);
                }
            }


    }

    public void writeHP() throws IOException, ClassNotFoundException {
        BufferedImage bufferedImage;
        if (this.getClass().equals(Knight.class)) {
            if (isRed) {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/knight.png"));
            } else {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/blue_knight.png"));
            }
        }
        else if (this.getClass().equals(Berzerk.class)) {
            if (isRed) {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/berzerk.png"));
            } else {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/blue_berzerk.png"));
            }
        }
        else if (this.getClass().equals(Destroyer.class)) {
            if (isRed) {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/destroyer.png"));
            } else {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/blue_destroyer.png"));
            }
        }
        else if (this.getClass().equals(Climber.class)) {
            if (isRed) {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/climber.png"));
            } else {
                bufferedImage = ImageIO.read(new File("src/main/resources/images/blue_climber.png"));
            }
        }
        else {
            throw new ClassNotFoundException("Katona kép fájlja nem található a wirteHP metódusban!");
        }

        Graphics g = bufferedImage.getGraphics();
        g.setFont(g.getFont().deriveFont(25f));
        g.drawString("HP: " + this.getHp(), 15, 30);
        g.dispose();
        ImageIO.write(bufferedImage, "png", new File("image1.png"));
        image = bufferedImage;
    }

    public void radius1Attack( LevelItem level ,Player player,Player enemy){
        int j = this.getX()/80;
        int i = this.getY()/80;
        try{
            if(this.isRed) {
                if (level.getLevelStructure().get(i).get(j + 1) instanceof Berzerk && !(level.getLevelStructure().get(i).get(j+1).isRed()) ){
                    this.setHp(0);
                }
                if(this instanceof Berzerk && level.getLevelStructure().get(i).get(j+1) instanceof Soldier){
                    level.getLevelStructure().get(i).get(j+1).setHp(0);
                }
            }


        } catch(ArrayIndexOutOfBoundsException e){

        }
        try {
            if(level.getLevelStructure().get(i + 1).get(j) instanceof ArcherTower ||
                    level.getLevelStructure().get(i + 1).get(j + 1) instanceof ArcherTower ||
                    level.getLevelStructure().get(i + 1).get(j - 1) instanceof ArcherTower ||
                    level.getLevelStructure().get(i - 1).get(j) instanceof ArcherTower ||
                    level.getLevelStructure().get(i - 1).get(j + 1) instanceof ArcherTower ||
                    level.getLevelStructure().get(i - 1).get(j - 1) instanceof ArcherTower ||
                    level.getLevelStructure().get(i).get(j + 1) instanceof ArcherTower ||
                    level.getLevelStructure().get(i).get(j - 1) instanceof ArcherTower
            ){
                if(this.isRed && !(
                        level.getLevelStructure().get(i + 1).get(j).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j - 1).isRed())){

                    this.setHp(this.getHp() - enemy.getArcherDmg());
                }

                if(!this.isRed &&
                        (level.getLevelStructure().get(i + 1).get(j).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j - 1).isRed())){
                            this.setHp(this.getHp() - enemy.getArcherDmg());
                }

            }
            if(level.getLevelStructure().get(i + 1).get(j) instanceof ZapTower ||
                    level.getLevelStructure().get(i - 1).get(j) instanceof ZapTower ||
                    level.getLevelStructure().get(i + 1).get(j + 1) instanceof ZapTower ||
                    level.getLevelStructure().get(i).get(j + 1) instanceof ZapTower ||
                    level.getLevelStructure().get(i - 1).get(j + 1) instanceof ZapTower ||
                    level.getLevelStructure().get(i - 1).get(j - 1) instanceof ZapTower ||
                    level.getLevelStructure().get(i).get(j - 1) instanceof ZapTower ||
                    level.getLevelStructure().get(i + 1).get(j - 1) instanceof ZapTower)
            {
                if(this.isRed && !(
                        level.getLevelStructure().get(i + 1).get(j).isRed() ||
                                level.getLevelStructure().get(i - 1).get(j).isRed() ||
                                level.getLevelStructure().get(i + 1).get(j + 1).isRed() ||
                                level.getLevelStructure().get(i).get(j + 1).isRed() ||
                                level.getLevelStructure().get(i - 1).get(j + 1).isRed() ||
                                level.getLevelStructure().get(i - 1).get(j - 1).isRed() ||
                                level.getLevelStructure().get(i).get(j - 1).isRed() ||
                                level.getLevelStructure().get(i + 1).get(j - 1).isRed())){

                    this.setHp(this.getHp() - enemy.getZapDmg());
                }

                if(!this.isRed &&
                        (level.getLevelStructure().get(i + 1).get(j).isRed() ||
                                level.getLevelStructure().get(i - 1).get(j).isRed() ||
                                level.getLevelStructure().get(i + 1).get(j + 1).isRed() ||
                                level.getLevelStructure().get(i).get(j + 1).isRed() ||
                                level.getLevelStructure().get(i - 1).get(j + 1).isRed() ||
                                level.getLevelStructure().get(i - 1).get(j - 1).isRed() ||
                                level.getLevelStructure().get(i).get(j - 1).isRed() ||
                                level.getLevelStructure().get(i + 1).get(j - 1).isRed())){
                    this.setHp(this.getHp() - enemy.getZapDmg());
                }
            }
            if(
                    (level.getLevelStructure().get(i + 1).get(j) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 1).get(j) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 1).get(j + 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i).get(j + 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 1).get(j + 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 1).get(j - 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i).get(j - 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 1).get(j - 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 2).get(j + 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 2).get(j + 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 2).get(j) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 2).get(j - 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 2).get(j - 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 1).get(j - 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i + 1).get(j + 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i).get(j - 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i).get(j + 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 1).get(j - 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 1).get(j + 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 2).get(j - 2) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 2).get(j - 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 2).get(j) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 2).get(j + 1) instanceof CannonTower ||
                    level.getLevelStructure().get(i - 2).get(j + 2) instanceof CannonTower)
                    )
            {
                if(this.isRed && !(
                        level.getLevelStructure().get(i + 1).get(j).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j + 2).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j + 2).isRed() ||
                        level.getLevelStructure().get(i).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i).get(j + 2).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j + 2).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j + 2).isRed())){
                    this.setHp(this.getHp() - enemy.getCannonDmg());
                }

                if(!this.isRed &&
                        (
                        level.getLevelStructure().get(i + 1).get(j).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j + 2).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i + 2).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i + 1).get(j + 2).isRed() ||
                        level.getLevelStructure().get(i).get(j - 2).isRed() ||
                        level.getLevelStructure().get(i).get(j + 2).isRed() ||
                        level.getLevelStructure().get(i - 1).get(j - 2).isRed()  ||
                        level.getLevelStructure().get(i - 1).get(j + 2).isRed()  ||
                        level.getLevelStructure().get(i - 2).get(j - 2).isRed()  ||
                        level.getLevelStructure().get(i - 2).get(j - 1).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j + 1).isRed() ||
                        level.getLevelStructure().get(i - 2).get(j + 2).isRed())){
                    this.setHp(this.getHp() - enemy.getCannonDmg());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
        }
    }
}
