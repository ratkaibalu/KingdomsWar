package kingdomswar.model;
import java.awt.*;
import java.io.IOException;

public class Sprite {
    protected int x;
    protected int y;
    private int hp;
    protected int width;
    protected int height;
    protected Image image;
    protected boolean isRed;

    public Sprite(){};

    public Sprite(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Sprite(int x, int y,Image image) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 80;
        /*
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
         */
        this.image = image;
        isRed = false;

    }
    public void draw(Graphics g) throws IOException, ClassNotFoundException {
        g.drawImage(image, x, y, width, height, null);
        this.writeHP();
    }
    public boolean collides(Sprite other) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);
        return rect.intersects(otherRect);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void writeHP() throws IOException, ClassNotFoundException {}
/*
    public boolean collides2(Sprite other) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(other.x -10, other.y-10, other.width, other.height);
        return rect.intersects(otherRect);
    }
    */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public boolean isRed(){
        return this.isRed;
    }
}


