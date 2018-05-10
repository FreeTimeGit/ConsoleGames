package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnakeApp implements GamesApp {

    private List<Player> playerList;
    private List<Player> chosenPlayers = new ArrayList<>();


    public SnakeApp(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public void startPlaying() {
        if(playerList.isEmpty()){
            System.out.println("There is no active players. Back to Main Menu to add.");
        }
        int checkingNumber = setPlayersForGame();
        if (checkingNumber >= playerList.size()) {
            managePlayersDuringGame();
        } else if(checkingNumber == 0){
            System.out.println("You didn't choose any player");
        }
    }

    @Override
    public int setPlayersForGame() {
        int countForCorrectChoices;
        int countForAddedPlayers;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose playerList to play snake");
        System.out.println("Press Y if you want use that player/Press N if don't");
        do {
            chosenPlayers.clear();
            countForAddedPlayers = 0;
            countForCorrectChoices = 0;
            for (Player player : playerList) {
                System.out.println(player.getName() + ": (Y/N) ; (Q)back to Game Menu ; (S)skip the rest of players");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    chosenPlayers.add(player);
                    countForCorrectChoices++;
                    countForAddedPlayers++;
                } else if (choice.equalsIgnoreCase("n")) {
                    countForCorrectChoices++;
                } else if (choice.equalsIgnoreCase("q")) {
                    return -1;
                } else if (choice.equalsIgnoreCase("s")) {
                    if(countForAddedPlayers == 0){
                        return 0;
                    }else {
                        return playerList.size();
                    }
                } else {
                    System.out.println("It wasn't expected choice. Start again!");
                    break;
                }
            }
        } while (countForCorrectChoices < playerList.size());
        return countForCorrectChoices;
    }

    @Override
    public void managePlayersDuringGame() {
        for (Player player : chosenPlayers) {
            int result = new Snake(player).playSnake();
            System.out.println("Game over! You scored " + result + " points");
            if (player.getSnakeRecord() < result) {
                System.out.println("NEW RECORD: " + result);
                player.setSnakeRecord(result);
            }
        }
    }
}
