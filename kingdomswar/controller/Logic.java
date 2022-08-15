package kingdomswar.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import  java.util.Queue;

import kingdomswar.model.*;
import kingdomswar.model.LevelItem.*;

import static kingdomswar.controller.Game.*;

public class Logic {
    static int ROW = 11;
    static int COL = 15;
    static List l;

    // To store matrix cell coordinates
    public static class Point
    {
        int x;
        int y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    };

    // A Data Structure for queue used in BFS
    static class queueNode
    {
        Point pt; // The coordinates of a cell
        queueNode parent; // cell's distance of from the source

        public queueNode(Point pt, queueNode parent)
        {
            this.pt = pt;
            this.parent = parent;
        }
        public Point getPoints() {
            return pt;
        }
        @Override
        public String toString() {
            return "(" + pt.x + ", " + pt.y + ')';
        }
    };

    // check whether given cell (row, col)
// is a valid cell or not.
    static boolean isValid(int row, int col)
    {
        // return true if row number and
        // column number is in range
        return (row >= 0) && (row < ROW) &&
                (col >= 0) && (col < COL);
    }

    // These arrays are used to get row and column
// numbers of 4 neighbours of a given cell
    static int rowNum[] = {-1, 0, 0, 1};
    static int colNum[] = {0, -1, 1, 0};

    static void findPath(queueNode node, List<Point> path)
    {
        if (node != null) {
            findPath(node.parent, path);
            path.add(node.getPoints());
        }
    }

    // function to find the shortest path between
// a given source cell to a destination cell.

    static List<Point> BFS(List<ArrayList<Sprite>> mat, Soldier soldier)
    {
        Point src = new Point(soldier.getY()/80, soldier.getX()/80);
        Point dest;

        if(soldier.isRed()){
            dest = new Point(5,14);

        } else {
            dest = new Point(5,0);
        }
        List<Point> path = new ArrayList<>();
        // check source and destination cell
        // of the matrix have value 1
//        if (mat[src.x][src.y] != 1 ||
//                mat[dest.x][dest.y] != 1)
//            return -1;

        boolean [][]visited = new boolean[30][30];

        // Mark the source cell as visited
        if(soldier.isRed()){
            visited[src.y][src.x] = true;
        } else {
            visited[src.x][src.y] = true;
        }

        // Create a queue for BFS
        Queue<queueNode> q = new LinkedList<>();

        // Distance of source cell is 0
        queueNode s = new queueNode(src, null);
        q.add(s); // Enqueue source cell

        // Do a BFS starting from source cell
        int counter = 0;
        while (!q.isEmpty())
        {
            queueNode curr = q.peek();
            Point pt = curr.pt;
            // If we have reached the destination cell,
            // we are done
            if (pt.x == dest.x && pt.y == dest.y) {
                findPath(curr, path);
                return path;
            }

            //System.out.println("x: " + curr.pt.x + " y: " + curr.pt.y +" dist: " + curr.dist);
            // Otherwise dequeue the front cell
            // in the queue and enqueue
            // its adjacent cells
            q.remove();

            for (int i = 0; i < 4; i++)
            {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                // if adjacent cell is valid, has path
                // and not visited yet, enqueue it.
                if (isValid(row, col) &&
                        (mat.get(row).get(col) instanceof Grass || mat.get(row).get(col) instanceof Castle || (counter > 1 && mat.get(row).get(col) instanceof Soldier) || (counter > 1 && mat.get(row).get(col) instanceof Tower) || (soldier instanceof Destroyer && mat.get(row).get(col) instanceof Tower && mat.get(row).get(col).isRed() != soldier.isRed())) &&
                        !visited[row][col])
                {
                    // mark cell as visited and enqueue it
                    visited[row][col] = true;
                    queueNode Adjcell = new queueNode
                            (new Point(row, col),
                                    curr);
                    q.add(Adjcell);
                    //System.out.println("x: " + Adjcell.getPoints().x + "  y: " + Adjcell.getPoints().y + "  dist: " + curr.dist);
                }
            }
            counter++;
        }
        // Return -1 if destination cannot be reached
        return path;
    }
}
