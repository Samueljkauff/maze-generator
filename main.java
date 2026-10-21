public class Main {
    public static void main(String[] args) {
        int boardSize = 20;   
        GameBoard board = new GameBoard(boardSize);

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                System.out.print(board.maze[i][j] + " ");
            }
            System.out.println(); 
        }
    }
}