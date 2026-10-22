import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("--------------- Welcome to Maze Runner ---------------\n");
        System.out.println("Enter the number of how big you want your maze:\n Options: {small, medium, large, xl, 2xl, 3xl, 4xl}");
        
    
        String choice = scan.nextLine();
        int size;
        switch (choice.toLowerCase()) {
            case "small" -> size = 10;
            case "medium" -> size = 15;
            case "large" -> size = 20;
            case "xl" -> size = 30;
            case "2xl" -> size = 40;
            case "3xl" -> size = 50;
            case "4xl" -> size = 60;
            default -> size = -1;
        }
        GameBoard board = new GameBoard(size);

        scan.close();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(board.maze[i][j] + " ");
            }
            System.out.println(); 
        }
    }
}