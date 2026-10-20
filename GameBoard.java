public class GameBoard {
    int mazeSize;
    int maze[][];
    int[] startCord;
    int[] endCord;

    public GameBoard(int mazeSize, int maze[][], int[] startCord, int[] endCord) {
        this.mazeSize = mazeSize;
        this.maze = maze;
        this.startCord = startCord;
        this.endCord = endCord;
    }

    public int[][] generateBoard(int mazeSize) {
        return new int[mazeSize][mazeSize];
    }

}