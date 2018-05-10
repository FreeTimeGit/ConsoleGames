package games;

import java.util.*;
import java.util.stream.Stream;

public class StartApp {

    public static void main(String[] args) {
        new StartApp().startPlaying();
    }

    private static final String WRONGCHOICE = "Wrong choice, please select again";

    private void startPlaying() {
        PlayersData playersData = PlayersData.getInstance();
        System.out.println("----Do you wanna play a game?----");
        boolean keepPlaying = true;
        while (keepPlaying) {
            showMainMenu();
            int playerChoice = playerChoice();
            switch (playerChoice) {
                case 1:
                    playersDataManaging(playersData);
                    break;
                case 2:
                    gameChoosing(playersData);
                    break;
                case 3:
                    keepPlaying = false;
                    break;
                default:
                    System.out.println(WRONGCHOICE);
            }
        }
    }

    private void gameChoosing(PlayersData playersData) {
        GamesApp gamesApp;
        GameTypeFactory gameTypeFactory = GameTypeFactory.getInstance();
        boolean keepPlayingGames = true;
        while (keepPlayingGames) {
            showGameMenu();
            int choice = playerChoice();
            Optional optional = GameType.gameMenuByNumber(choice);
            if (optional.isPresent()) {
                GameType gameType = (GameType) optional.get();
                switch (gameType) {
                    case SNAKE:
                        gamesApp = gameTypeFactory.createGame(GameType.SNAKE);
                        gamesApp.startPlaying();
                        playersData.exportPlayersData(playersData.getPlayerList());
                        break;
                    case ROULETTE:
                        gamesApp = gameTypeFactory.createGame(GameType.ROULETTE);
                        gamesApp.startPlaying();
                        break;
                    case BLACKJACK:
                        gamesApp = gameTypeFactory.createGame(GameType.BLACKJACK);
                        gamesApp.startPlaying();
                        break;
                    case BACK_TO_MAIN_MENU:
                        keepPlayingGames = false;
                        break;
                }
            } else System.out.println(WRONGCHOICE);
        }
    }

    private void showGameMenu() {
        System.out.println("GamesApp:");
        Stream.of(GameType.values()).forEach(System.out::println);
    }

    private void playersDataManaging(PlayersData playersData) {
        boolean keepManagingPlayers = true;
        while (keepManagingPlayers) {
            showPlayersMenu();
            int choice = playerChoice();
            switch (choice) {
                case 1:
                    playersData.showPlayers();
                    break;
                case 2:
                    addingPlayerProcess(playersData);
                    break;
                case 3:
                    deletingPlayerProcess(playersData);
                    break;
                case 4:
                    editingPlayerProcess(playersData);
                    break;
                case 5:
                    playersData.showPlayersData();
                    break;
                case 6:
                    keepManagingPlayers = false;
                    break;
                default:
                    System.out.println(WRONGCHOICE);
            }
        }
    }

    private void editingPlayerProcess(PlayersData playersData) {
        int choice;
        String newName = "";
        Scanner scanner = new Scanner(System.in);
        do {
            playersData.showPlayers();
            System.out.println("To edit player name, please write player ID:");
            choice = playerChoice();
            if (playersData.checkIfCorrectId(choice)) {
                do {
                    System.out.println("Write new name:");
                    newName = scanner.nextLine();
                    if(!playersData.checkForUniqueName(newName)) {
                        System.out.println("Player with this name is on the list");
                    }
                }while (!playersData.checkForUniqueName(newName));
            } else {
                System.out.println("There is no player with this ID.");
                System.out.println();
            }
        }
        while (!playersData.checkIfCorrectId(choice));
        playersData.editPlayerName(choice, newName);
        playersData.exportPlayersData(playersData.getPlayerList());
    }

    private void deletingPlayerProcess(PlayersData playersData) {
        int choice;
        System.out.println("To delete player, please write player ID:");
        choice = playerChoice();
        if (playersData.checkIfCorrectId(choice)) {
            playersData.deletePlayer(choice);
            playersData.exportPlayersData(playersData.getPlayerList());
        } else System.out.println("There is no player with this ID.");
    }

    private void addingPlayerProcess(PlayersData playersData) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write your name:");
        String name = scanner.nextLine();
        if (playersData.checkForUniqueName(name)) {
            playersData.addNewPlayer(name);
            playersData.exportPlayersData(playersData.getPlayerList());
        }else {
            System.out.println("Player with this name is on the list");
        }
    }

    private void showMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Player menu");
        System.out.println("2. Game menu");
        System.out.println("3. Exit");
    }

    private void showPlayersMenu() {
        System.out.println("Player Menu:");
        System.out.println("1. Show players");
        System.out.println("2. Add player");
        System.out.println("3. Delete player");
        System.out.println("4. Edit name");
        System.out.println("5. Show records");
        System.out.println("6. Back to Main Menu");
    }

    private int playerChoice() {
        int playerChoice = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            playerChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            e.getMessage();
        }
        return playerChoice;
    }
    private boolean checkForUniqueName(List<Player> playerList, String name){
        return playerList.stream().noneMatch(player -> player.getName().equalsIgnoreCase(name));
    }
}
