import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameBoard {
    int mazeSize;
    char maze[][];
    int[] startCord;
    int[] endCord;
    Random ran = new Random();

    public GameBoard(int mazeSize) {
        this.mazeSize = mazeSize;
        this.startCord = generateCords();
        this.endCord = generateCords();
        this.maze = generateMaze(mazeSize); 
    }

    private char[][] initializeMaze(int size) {
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

    public char[][] generateMaze(int size) {
        char[][] initialMaze = initializeMaze(size);
        boolean[][] traveledMaze = new boolean[size][size];
        int X = ran.nextInt(0, size);
        int Y = ran.nextInt(0, size);
        int move;
        int newX;
        int newY;

        traveledMaze[X][Y] = true;
        initialMaze[X][Y] = ' ';

        List<Integer> directions = new ArrayList<>(List.of(0, 1, 2, 3));
        Collections.shuffle(directions);

        for (int direction : directions) {
        switch (direction) {
            case 0:
                // UP
                move = -2;

                if (X + move > 0 && X + move < size - 1 && !traveledMaze[X + move][Y] && initialMaze[X + move][Y] == 'X') {
                    newX = X + move;
                    traveledMaze[newX][Y] = true;
                    initialMaze[newX][Y] = ' ';
                }
                break;

            case 1:
                // RIGHT
                move = 2;

                if (Y + move > 0 && Y + move < size - 1 && !traveledMaze[X][Y + move] && initialMaze[X][Y + move] == 'X') {
                    newY = Y + move;
                    traveledMaze[X][newY] = true;
                    initialMaze[X][newY] = ' ';
                }
                break;

            case 2:
                // DOWN
                move = 2;

                if (X + move > 0 && X + move < size - 1 && !traveledMaze[X + move][Y] && initialMaze[X + move][Y] == 'X') {
                    newX = X + move;
                    traveledMaze[newX][Y] = true;
                    initialMaze[newX][Y] = ' ';
                }
                break;

            case 3:
                // LEFT
                move = -2;

                if (Y + move > 0 && Y + move < size - 1 && !traveledMaze[X][Y + move] && initialMaze[X][Y + move] == 'X') {
                    newY = Y + move;
                    traveledMaze[X][newY] = true;
                    initialMaze[X][newY] = ' ';
                }
                break;
        }
        }

        return initialMaze;
    }

    private int[] generateCords() {
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

    private boolean isValidCord(int[] cord) {
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