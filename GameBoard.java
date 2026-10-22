import java.util.Random;

public class GameBoard {
    int mazeSize;
    char maze[][];
    int[] startCord;
    int[] endCord;

    public GameBoard(int mazeSize) {
        this.mazeSize = mazeSize;
        this.startCord = generateCords();
        this.endCord = generateCords();
        this.maze = generateMaze(mazeSize); 
    }

    public char[][] generateMaze(int size) {
        char[][] board = new char[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i == startCord[0] && j == startCord[1] || i == endCord[0] && j == endCord[1]) {
                    board[i][j] = ' ';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
        return board;
    }

    public int[] generateCords() {
        Random ran = new Random();
        int[] cord = new int[2];
        int orientation = ran.nextInt(0,4);
        int location = ran.nextInt(3, mazeSize-4);
        
        switch (orientation) {
            case 0:
            // TOP
                cord[0] = 0;
                cord[1] = location;
              break;
            case 1:
            // RIGHT
                cord[0] = location;
                cord[1] = mazeSize-1;
                break;
            case 2:
            // BOTTOM
                cord[0] = mazeSize-1;
                cord[1] = location;
                break;
            case 3:
            // LEFT
                cord[0] = location;
                cord[1] = 0;
                break;  
        }
        if(startCord != null && !isValidCord(cord)) {
            cord = generateCords();
            return cord;
        }

        return cord;
    }

    public boolean isValidCord(int[] cord) {
        int invalidCord[] = new int[2];
        for(int i = cord[0]-1; i <= cord[0]+1; i++) {
            for(int j = cord[1]-1; j <= cord[1]+1; j++) {
                invalidCord[0] = i;
                invalidCord[1] = j;
                if(startCord[0] == invalidCord[0] && startCord[1] == invalidCord[1]) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return true;
    }

}