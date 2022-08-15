package kingdomswar.model;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LevelItem {
    /* 1 - red castle
       2 - blue castle
       L - lake
       M - mountain
       T - tree
     */

    private final int cols = 15;
    public final int rows = 11;
    private ArrayList<ArrayList<Sprite>> levelStructure = new ArrayList<ArrayList<Sprite>>();
    private final int sizeOfObjects = 80;



    public LevelItem(String levelPath) throws IOException {
        loadLevel(levelPath);
    }

    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        String line;
        int y = 0;

        while((line = br.readLine()) != null) {
            ArrayList<Sprite> tmpRow = new ArrayList<>();
            tmpRow.clear();
            for (int i = 0; i < cols; i++) {
                switch (line.charAt(i)) {
                    case '1':
                        Image castleImgRed = new ImageIcon("src/main/resources/images/castle_red.png").getImage();
                        tmpRow.add(new Castle(i * sizeOfObjects, y * sizeOfObjects, castleImgRed, true));
                        break;
                    case '2':
                        Image castleImgBlue = new ImageIcon("src/main/resources/images/castle_blue.png").getImage();
                        tmpRow.add(new Castle(i * sizeOfObjects, y * sizeOfObjects, castleImgBlue, false));
                        break;
                    case 'L':
                        Image lakeImg = new ImageIcon(("src/main/resources/images/lake.png")).getImage();
                        tmpRow.add(new Lake(i * sizeOfObjects, y * sizeOfObjects, lakeImg));
                        break;
                    case 'M':
                        Image mountainImg = new ImageIcon(("src/main/resources/images/mountain.png")).getImage();
                        tmpRow.add(new Mountain(i * sizeOfObjects, y * sizeOfObjects, mountainImg));
                        break;
                    case 'T':
                        Image treeImg = new ImageIcon(("src/main/resources/images/tree.png")).getImage();
                        tmpRow.add(new Tree(i * sizeOfObjects, y * sizeOfObjects, treeImg));
                        break;
                    default:
                        Image grassImg = new ImageIcon("src/main/resources/images/grass.png").getImage();
                        tmpRow.add(new Grass(i * sizeOfObjects, y * sizeOfObjects));
                }
            }
            levelStructure.add(y, tmpRow);
            y++;
        }
    }

    public List<ArrayList<Sprite>> getLevelStructure() {
        return levelStructure;
    }

    public void setLevelStructure(ArrayList<ArrayList<Sprite>> levelStructure) {
        this.levelStructure = levelStructure;
    }

}
