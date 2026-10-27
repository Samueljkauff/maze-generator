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
        int maxOddCoordinate = size - 2;
        int coordinateCount = (maxOddCoordinate + 1) / 2;

        int X = ran.nextInt(coordinateCount) * 2 + 1;
        int Y = ran.nextInt(coordinateCount) * 2 + 1;
        traveledMaze[X][Y] = true;
        initialMaze[X][Y] = ' ';

        generateMazeRecusive(initialMaze, traveledMaze, size, X, Y);
        connectEntranceAndExit(initialMaze);

        return initialMaze;
    }

    private void generateMazeRecusive(char[][] maze, boolean[][] traveledMaze, int size, int X, int Y) {
        List<Integer> directions = new ArrayList<>(List.of(0, 1, 2, 3));
        Collections.shuffle(directions);
        int move;
        int newX = X;
        int newY = Y;

        for (int direction : directions) {
        switch (direction) {
            case 0:
                // UP
                move = -2;
                newX = X + move;

                if (newX > 0 && newX < size - 1 && !traveledMaze[newX][Y] && maze[newX][Y] == 'X') {
                    traveledMaze[newX][Y] = true;
                    maze[newX][Y] = ' ';
                    maze[newX+1][Y] = ' ';
                    generateMazeRecusive(maze, traveledMaze, size, newX, Y);
                }
                break;

            case 1:
                // RIGHT
                move = 2;
                newY = Y + move;

                if (newY > 0 && newY < size - 1 && !traveledMaze[X][newY] && maze[X][newY] == 'X') {
                    traveledMaze[X][newY] = true;
                    maze[X][newY] = ' ';
                    maze[X][newY-1] = ' ';
                    generateMazeRecusive(maze, traveledMaze, size, X, newY);
                }
                break;

            case 2:
                // DOWN
                move = 2;
                newX = X + move;

                if (newX > 0 && newX < size - 1 && !traveledMaze[newX][Y] && maze[newX][Y] == 'X') {
                    traveledMaze[newX][Y] = true;
                    maze[newX][Y] = ' ';
                    maze[newX-1][Y] = ' ';
                    generateMazeRecusive(maze, traveledMaze, size, newX, Y);
                }
                break;

            case 3:
                // LEFT
                move = -2;
                newY = Y + move;

                if (newY > 0 && newY < size - 1 && !traveledMaze[X][newY] && maze[X][newY] == 'X') {
                    traveledMaze[X][newY] = true;
                    maze[X][newY] = ' ';
                    maze[X][newY+1] = ' ';
                    generateMazeRecusive(maze, traveledMaze, size, X, newY);
                }
                break;
        }
        }
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

    private void connectEntranceAndExit(char[][] maze) {
        // Entrance
        if (startCord[0] == 0) {
            maze[1][startCord[1]] = ' ';
        } else if (startCord[0] == mazeSize - 1) {
            maze[mazeSize - 2][startCord[1]] = ' ';
        } else if (startCord[1] == 0) {
            maze[startCord[0]][1] = ' ';
        } else {
            maze[startCord[0]][mazeSize - 2] = ' ';
        }

        // Exit
        if (endCord[0] == 0) {
            maze[1][endCord[1]] = ' ';
        } else if (endCord[0] == mazeSize - 1) {
            maze[mazeSize - 2][endCord[1]] = ' ';
        } else if (endCord[1] == 0) {
            maze[endCord[0]][1] = ' ';
        } else {
            maze[endCord[0]][mazeSize - 2] = ' ';
        }
    }

}