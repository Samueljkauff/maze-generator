import java.util.Scanner;

public class Controller {
    GameBoard gameBoard;
    int currentX;
    int currentY;

    public Controller(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.currentX = gameBoard.startCord[0];
        this.currentY = gameBoard.startCord[1];
    }

    public void startGame() {
        boolean hasWon = false;
        String direction;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("You are \"O\". Use W/A/S/D to move around! Make it to the \"*\" to win!");
            displayBoard();
            direction = input.nextLine().toLowerCase();
            updatePosition(direction);
            if(currentX == gameBoard.endCord[0] && currentY == gameBoard.endCord[1]) {
                hasWon = true;
            }
        } while(!hasWon);

        System.out.println("YOU WON!!");
    }

    public void updatePosition(String direction) {
        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case "w":
                newX--;
                break;
            case "a":
                newY--;
                break;
            case "s":
                newX++;
                break;
            case "d":
                newY++;
                break;
            default:
                return;
        }

        if (newX >= 0 && newX < gameBoard.mazeSize
                && newY >= 0 && newY < gameBoard.mazeSize
                && gameBoard.maze[newX][newY] != 'X') {

            gameBoard.maze[currentX][currentY] = ' ';
            currentX = newX;
            currentY = newY;
            gameBoard.maze[currentX][currentY] = 'O';
        }
    }

    public void displayBoard() {
        for(int i = 0; i < gameBoard.mazeSize; i++) {
            for(int j = 0; j < gameBoard.mazeSize; j++) {
                System.out.print(gameBoard.maze[i][j] + " ");
            }
            System.out.println(); 
        }
    }
}