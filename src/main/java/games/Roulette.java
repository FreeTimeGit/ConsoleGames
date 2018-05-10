package games;

import java.util.*;

public class Roulette {

    private List<RoulettePlayer> roulettePlayers;
    private static int spinResult;

    public Roulette(List<RoulettePlayer> playerList) {
        this.roulettePlayers = playerList;
    }

    private int selectRandomInt() {
        Random random = new Random();
        return random.nextInt(37);
    }

    public void betsInformation() {
        System.out.println("Available bets:");
        RouletteBetsType.betsListMenu.forEach(e -> System.out.println(e));
        System.out.println("Press matching number to bet or (S) to skip bet or (L) if it is your last bet in game");
    }

    public void getBetsFromPlayers2() {
        RouletteBetsType.createBetsMap();
        String choice;
        for (RoulettePlayer roulettePlayer : roulettePlayers) {
            int tempBalance = roulettePlayer.getRouletteBalance();
            System.out.println();
            System.out.println(roulettePlayer.getName() + ", your cash  balance: "+roulettePlayer.getRouletteBalance()+"$");
            System.out.println();
            if(tempBalance<10){
                System.out.println("It is not enough money! You can't play this game!!");
                continue;
            }
            for (Map.Entry<String, List<RouletteBetsType>> element : RouletteBetsType.betsMap.entrySet()) {
                Scanner scanner = new Scanner(System.in);
                boolean betCorrect = false;
                int betMoney;
                boolean end = false;
                System.out.println("You got "+ tempBalance + " for betting!");
                if(tempBalance<10){
                    System.out.println("It is not enough money! Can't bet more!");
                    break;
                }
                while (!betCorrect) {
                    System.out.println(element.getKey());
                    choice = scanner.next();
                    if (choice.equalsIgnoreCase("s")) {
                        betCorrect = true;
                    }
                    else if(choice.equalsIgnoreCase("l")) {
                        end = true;
                        betCorrect = true;
                    }
                    else if(element.getKey().contains(choice)){
                        System.out.println("How much do you want to bet");
                        betMoney = scanner.nextInt();
                        roulettePlayer.getBetsMap().put(element.getValue().get(Integer.parseInt(choice)-1),betMoney);
                        tempBalance = tempBalance - betMoney;
                        betCorrect = true;
                    }
                    else
                        System.out.println("Zły wybór");
                }
                if(end) {
                    break;
                }
            }
        }
    }
}