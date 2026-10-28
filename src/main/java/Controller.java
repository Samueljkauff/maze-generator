import java.io.IOException;

import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Controller {
    GameBoard gameBoard;
    int currentX;
    int currentY;

    public Controller(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.currentX = gameBoard.startCord[0];
        this.currentY = gameBoard.startCord[1];
    }

public void startGame() throws IOException {
    try (Terminal terminal = TerminalBuilder.terminal()) {

        Attributes originalAttributes = terminal.getAttributes();
        try {
            terminal.enterRawMode();
            boolean hasWon = false;

            while (!hasWon) {
                System.out.print("\033[H\033[2J");
                System.out.println("\nUse W/A/S/D to move. Press Q to quit.\n");
                displayBoard();
                System.out.flush();

                int key = terminal.reader().read();

                if (key == -1) {
                    break;
                }

                String direction = String.valueOf((char) key).toLowerCase();

                if (direction.equals("q")) {
                    break;
                }

                updatePosition(direction);

                hasWon = currentX == gameBoard.endCord[0] && currentY == gameBoard.endCord[1];
            }
        } finally {
            terminal.setAttributes(originalAttributes);
        }
    }
    System.out.println("\nYOU WON!");
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