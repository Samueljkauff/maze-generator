import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("--------------- Welcome to Maze Runner ---------------\n");
        System.out.println("Enter the number of how big you want your maze:\n");

        int boardSize = scan.nextInt();
        GameBoard board = new GameBoard(boardSize);
        
        scan.close();

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                System.out.print(board.maze[i][j] + " ");
            }
            System.out.println(); 
        }
    }
}