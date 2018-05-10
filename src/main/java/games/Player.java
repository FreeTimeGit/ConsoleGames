package games;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String name;
    private int rouletteBalance;
    private int snakeRecord;
    private int blackJackBalance;
    private int playerID;
    private static int id = 0;

    Player(String name) {
        id++;
        this.name = name;
        this.playerID = id;
    }

    Player(String name, int rouletteBalance, int snakeRecord, int blackJackBalance) {
        id++;
        this.name = name;
        this.rouletteBalance = rouletteBalance;
        this.snakeRecord = snakeRecord;
        this.blackJackBalance = blackJackBalance;
        this.playerID = id;
    }

    @Override
    public String toString() {
        return playerID+". " + name +
                ", rouletteBalance=" + rouletteBalance +
                ", snakeRecord=" + snakeRecord +
                ", blackJackBalance=" + blackJackBalance;
    }
}
