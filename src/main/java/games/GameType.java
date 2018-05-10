package games;

import java.util.Optional;
import java.util.stream.Stream;

public enum GameType {

    SNAKE("Snake", 1), ROULETTE("Roulette", 2), BLACKJACK("BlackJack", 3), BACK_TO_MAIN_MENU("Back to main menu", 4);

    private String name;
    private int ordinalNumber;

    GameType(String name, int ordinalNumber) {
        this.name = name;
        this.ordinalNumber = ordinalNumber;
    }

    public static Optional gameMenuByNumber(int number) {
        return Stream.of(GameType.values()).filter(gameType -> gameType.ordinalNumber == number).findFirst();
    }


    @Override
    public String toString() {
        return ordinalNumber + ". " + name;
    }
}
