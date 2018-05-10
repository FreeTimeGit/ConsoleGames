package games;

import java.util.*;

public enum RouletteBetsType {
    ZERO(0), BLACK(1), RED(2), ODD(1), EVEN(2), FIRST12(1), SECOND12(2), THIRD12(1), FIRST18(2), SECOND18(3);

    private int betNumber;

    RouletteBetsType(int betNumber) {
        this.betNumber = betNumber;
    }

    public int getBetNumber() {
        return betNumber;
    }

    protected static final List<String> betsListMenu =
            Arrays.asList("BLACK/RED", "ODD/EVEN", "SMALL NUMBERS/BIG NUMBERS", "DOZENS");

    protected static final List<String> betsList =
            Arrays.asList("(1)BLACK/(2)RED", "(1)ODD/(2)EVEN", "(1)FIRST18/(2)SECOND18", "(1)FIRST12/(2)SECOND12/(3)THIRD12");

    protected static final LinkedHashMap<String, List<RouletteBetsType>> betsMap = new LinkedHashMap<>();

    private static List<RouletteBetsType> createList(RouletteBetsType type, List<RouletteBetsType> list) {
        list.add(type);
        return list;
    }

    protected static void createBetsMap() {
        List<RouletteBetsType> lista;
        for (String betMenu : betsList) {
            lista = new ArrayList<>();
            for (RouletteBetsType bet : RouletteBetsType.values()) {
                if (betMenu.contains(bet.name()))
                    betsMap.put(betMenu, createList(bet, lista));
            }
        }
    }

    private static final List<Integer> redColourNumbers =
            Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);

    public static RouletteBetsType setSpinForParity(int number) {
        if (number == 0) {
            return ZERO;
        } else if (number % 2 == 0) {
            return EVEN;
        } else
            return ODD;
    }

    public static RouletteBetsType setSpinForDozen(int number) {
        if (number == 0) {
            return ZERO;
        } else if (number <= 12) {
            return FIRST12;
        } else if (number <= 24)
            return SECOND12;
        else {
            return THIRD12;
        }
    }

    public static RouletteBetsType setSpinForHalf(int number) {
        if (number == 0)
            return ZERO;
        else if (number <= 18)
            return FIRST18;
        else
            return SECOND18;
    }

    public static RouletteBetsType setSpinForColor(int number) {
        if (number == 0)
            return ZERO;
        else if (redColourNumbers.contains(number))
            return RED;
        else
            return BLACK;
    }
}
