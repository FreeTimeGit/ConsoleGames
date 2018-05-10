package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RouletteApp implements GamesApp {

    private List<Player> playerList;
    private List<Player> chosenPlayers = new ArrayList<>();
    private List<RoulettePlayer> roulettePlayers = new ArrayList<>();

    public RouletteApp(List<Player> players) {
        this.playerList = players;
    }

    @Override
    public int setPlayersForGame() {
        int countForCorrectChoices;
        int countForAddedPlayers;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose players to play roulette");
        System.out.println("Press Y if you want use that player/Press N if don't");
        do {
            chosenPlayers.clear();
            countForAddedPlayers = 0;
            countForCorrectChoices = 0;
            for (Player player : playerList) {
                System.out.println(player.getName() + ": (Y/N) ; (Q)back to Game Menu ; (S)skip the rest of playerList");
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
    public void startPlaying() {
        setPlayersForGame();
        setPlayersForRoulettePlayers();
        Roulette roulette = new Roulette(roulettePlayers);
        roulette.betsInformation();
        roulette.getBetsFromPlayers2();
    }

    @Override
    public void managePlayersDuringGame() {

    }

    private void setPlayersForRoulettePlayers(){
        for (Player player : chosenPlayers) {
            roulettePlayers.add(new RoulettePlayer(player.getPlayerID(),player.getName(),player.getRouletteBalance()));
        }
    }
}
