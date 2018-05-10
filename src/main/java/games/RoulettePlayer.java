package games;

import java.util.HashMap;
import java.util.Map;

public class RoulettePlayer {

    private int id;
    private String name;
    private int rouletteBalance;
    private Map<RouletteBetsType, Integer> betsMap;

    public int getRouletteBalance() {
        return rouletteBalance;
    }

    public void setRouletteBalance(int rouletteBalance) {
        this.rouletteBalance = rouletteBalance;
    }

    public RoulettePlayer(int id, String name, int rouletteBalance) {
        this.id = id;
        this.name = name;
        this.rouletteBalance = rouletteBalance;
        this.betsMap = new HashMap<>();
    }

    public Map<RouletteBetsType, Integer> getBetsMap() {
        return betsMap;
    }

    public void setBetsMap(Map<RouletteBetsType, Integer> betsMap) {
        this.betsMap = betsMap;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
