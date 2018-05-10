package games;

import java.util.List;

public class GameTypeFactory {

    private List<Player> playerList;
    private static GameTypeFactory instance = new GameTypeFactory();

    private GameTypeFactory() {
        this.playerList = PlayersData.getInstance().getPlayerList();
    }

    public static GameTypeFactory getInstance(){
        return instance;
    }

    public GamesApp createGame(GameType gameType){
        if(gameType.equals(GameType.SNAKE)){
            return new SnakeApp(playerList);
        }
        else if (gameType.equals(GameType.ROULETTE)){
            return new RouletteApp(playerList);
        }
        else{
            return new BlackJackApp(playerList);
        }
    }
}
