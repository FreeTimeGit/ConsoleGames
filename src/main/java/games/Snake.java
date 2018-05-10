package games;

import java.util.Random;
import java.util.Scanner;

public class Snake {

    private Player player;
    private String[][] plansza;
    private int[] snakeA;
    private int[] snakeB;
    private int[] food;
    private boolean loseCondition = false;
    private int score;

    private boolean isLoseCondition() {
        return loseCondition;
    }

    private void setLoseCondition() {
        this.loseCondition = true;
    }

    private int getScore() {
        return score;
    }

    private void setScore() {
        this.score++;
    }

    private int[] getSnakeA() {
        return snakeA;
    }

    private int[] getSnakeB() {
        return snakeB;
    }

    private void setSnakeA(int[] snakeA) {
        this.snakeA = new int[snakeA.length + 1];
        for (int i = 0; i < snakeA.length; i++) {
            this.snakeA[i] = snakeA[i];
        }
        this.snakeA[this.snakeA.length - 1] = snakeA[snakeA.length - 1];
    }

    private void setSnakeB(int[] snakeB) {
        this.snakeB = new int[snakeB.length + 1];
        for (int i = 0; i < snakeB.length; i++) {
            this.snakeB[i] = snakeB[i];
        }
        this.snakeB[this.snakeB.length - 1] = snakeB[snakeB.length - 1];
    }

    public Snake(Player player) {
        plansza = new String[10][10];
        this.snakeA = new int[]{4, 4, 4, 4, 4, 4};
        this.snakeB = new int[]{2, 3, 4, 5, 6, 7};
        this.food = new int[]{2, 2};
        this.score = 0;
        this.player = player;
    }

    public int playSnake() {
        System.out.println(player.getName()+", your turn!");
        System.out.println("Get ready!");
        for (int i = 5; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            System.out.println(i+"!");
        }
        while (!isLoseCondition()) {
            while (foodEaten()) {

                setSnakeA(getSnakeA());
                setSnakeB(getSnakeB());

                this.food = generateFood();
            }
            refreshBoard(plansza);
            paintBoard(plansza);
            printBoard(plansza);
            navigateSnake();
            snakeCrashed();
        }
        return getScore();
    }

    private void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }

    private boolean foodEaten() {
        if (!(getSnakeA()[0] == food[0] && getSnakeB()[0] == food[1])) {
            return false;
        } else {
            setScore();
            return true;
        }
    }

    private void paintBoard(String[][] board) {
        for (int i = 0; i < getSnakeA().length; i++) {
            board[food[0]][food[1]] = " " + (char) 164 + " ";
            board[getSnakeA()[0]][getSnakeB()[0]] = " O ";
            board[getSnakeA()[i]][getSnakeB()[i]] = " " + (char) 27 + " ";
        }
    }

    private void refreshBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = " " + (char) 183 + " ";
            }
        }
    }

    private void navigateSnake() {
        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine();
        if (move.equals("w")) {
            if (getSnakeA()[0] == 0) {
                setLoseCondition();
                return;
            }
            for (int i = getSnakeA().length - 1; i > 0; i--) {
                getSnakeA()[i] = getSnakeA()[i - 1];
                getSnakeB()[i] = getSnakeB()[i - 1];
            }
            getSnakeA()[0] = getSnakeA()[0] - 1;
        }
        if (move.equals("s")) {
            if (getSnakeA()[0] == 9) {
                setLoseCondition();
                return;
            }
            for (int i = getSnakeA().length - 1; i > 0; i--) {
                getSnakeA()[i] = getSnakeA()[i - 1];
                getSnakeB()[i] = getSnakeB()[i - 1];
            }
            getSnakeA()[0] = getSnakeA()[0] + 1;
        }
        if (move.equals("a")) {
            if (getSnakeB()[0] == 0) {
                setLoseCondition();
                return;
            }
            for (int i = getSnakeA().length - 1; i > 0; i--) {
                getSnakeA()[i] = getSnakeA()[i - 1];
                getSnakeB()[i] = getSnakeB()[i - 1];
            }
            getSnakeB()[0] = getSnakeB()[0] - 1;
        }
        if (move.equals("d")) {
            if (getSnakeB()[0] == 9) {
                setLoseCondition();
                return;
            }
            for (int i = getSnakeA().length - 1; i > 0; i--) {
                getSnakeA()[i] = getSnakeA()[i - 1];
                getSnakeB()[i] = getSnakeB()[i - 1];
            }
            getSnakeB()[0] = getSnakeB()[0] + 1;
        }
    }

    private int[] generateFood() {
        Random random = new Random();
        int foodX;
        int foodY;
        while (true) {
            foodX = random.nextInt(plansza.length);
            foodY = random.nextInt(plansza.length);
            for (int i = 0; i < getSnakeA().length; i++) {
                if ((foodX != getSnakeA()[i] && foodY != getSnakeB()[i])) {
                    food[0] = foodX;
                    food[1] = foodY;
                    return food;
                }
            }
        }
    }

    private void snakeCrashed() {
        for (int i = 1; i < getSnakeA().length; i++) {
            if (getSnakeA()[0] == getSnakeA()[i] && getSnakeB()[0] == getSnakeB()[i]) {
                setLoseCondition();
            }
        }
    }
}

