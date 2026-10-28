import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("--------------- Welcome to Maze Runner ---------------\n");
        System.out.println("Enter the number of how big you want your maze:\n Options: {small, medium, large, xl, 2xl, 3xl, 4xl}");
        
    
        String choice = scan.nextLine();
        int size;
        switch (choice.toLowerCase()) {
            case "small" -> size = 11;
            case "medium" -> size = 15;
            case "large" -> size = 21;
            case "xl" -> size = 31;
            case "2xl" -> size = 41;
            case "3xl" -> size = 51;
            case "4xl" -> size = 61;
            default -> size = -1;
        }
        GameBoard board = new GameBoard(size);
        Controller controller = new Controller(board);

        controller.startGame();

        scan.close();
    }
}