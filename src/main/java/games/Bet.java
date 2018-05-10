package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bet {

    private Bet bet = null;
    private String betName;
    private List<RouletteBetsType> betsTypeList;

    private static final List<String> betsList =
            Arrays.asList("(1)BLACK/(2)RED","(1)ODD/(2)EVEN","(1)FIRST18/(2)SECOND18","(1)FIRST12/(2)SECOND12/(3)THIRD12");

    private Bet(String betName) {
        this.betName = betName;
    }

    private Bet(String betName, List<RouletteBetsType> betsTypeList) {
        this.betName = betName;
        this.betsTypeList = betsTypeList;
    }

    public String getBetName() {
        return betName;
    }

    private void createOwnBetMap(Bet nextBet){
        if(bet == null){
            bet = nextBet;
        }
        else
            bet.createOwnBetMap(nextBet);
    }

    private void createBiggerBetMap(Bet bet){
        List<RouletteBetsType> list;
        for (String element: betsList){
            list = new ArrayList<>();
            bet.createOwnBetMap(new Bet(element, list));
            for(RouletteBetsType betsType: RouletteBetsType.values()){
                if(element.contains(betsType.name())){
                    list.add(betsType);
                }
            }
        }
    }

    public static void main(String[] args) {

        Bet thirdBetMap = new Bet("Bets Type",new ArrayList<>());
        thirdBetMap.createBiggerBetMap(thirdBetMap);
        System.out.println(thirdBetMap);
    }

    @Override
    public String toString() {
        if(bet==null)
        return betName + betsTypeList;
        else
            return betName + betsTypeList + bet;
    }
}
