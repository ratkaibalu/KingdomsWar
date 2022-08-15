package kingdomswar.model;

public class GameLevel {
    //public final int rows;
    //public final int cols;
    public Sprite[][] level;

/*
    public GameLevel(ArrayList<String> gameLevelRows ){

        int c = 0;
        for (String s : gameLevelRows) if (s.length() > c) c = s.length();
        rows = gameLevelRows.size();
        cols = c;
        level = new LevelItem[rows][cols];

        for (int i = 0; i < rows; i++){
            String s = gameLevelRows.get(i);
            for (int j = 0; j < s.length(); j++){
                switch (s.charAt(j)){
                    case '@': player = new Position(j, i);
                        level[i][j] = LevelItem.EMPTY; break;
                    case '#': level[i][j] = LevelItem.WALL; break;
                    case '.': level[i][j] = LevelItem.DESTINATION; break;
                    case '*': level[i][j] = LevelItem.EMPTY;
                        dragon = new Position(j,i); break;
                    default:  level[i][j] = LevelItem.EMPTY; break;
                }
            }
            for (int j = s.length(); j < cols; j++){
                level[i][j] = LevelItem.EMPTY;
            }
        }
    }

 */



}
