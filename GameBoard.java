public class GameBoard {
    int mazeSize;
    char maze[][];
    int[] startCord;
    int[] endCord;

    public GameBoard(int mazeSize) {
        this.mazeSize = mazeSize;
        this.maze = generateMaze(mazeSize); 
        this.startCord = new int[]{1, 1};
        this.endCord = new int[]{mazeSize - 2, mazeSize - 2};
    }

    public char[][] generateMaze(int size) {
        char[][] board = new char[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = ' ';
                }
            }
        }
        return board;
    }

}