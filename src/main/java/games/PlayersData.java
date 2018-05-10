package games;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PlayersData {

    private List<Player> playerList;
    private static PlayersData instance = new PlayersData();

    private PlayersData() {
        this.playerList = importPlayersData();
    }

    public static PlayersData getInstance() {
        return instance;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    private List<Player> importPlayersData() {
        List<Player> listToReturn = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("players.csv"))){
            String line = reader.readLine();
            while (line != null) {
                String[] lineToken = line.split(";");
                Player player = new Player(
                        lineToken[1],
                        Integer.parseInt(lineToken[2]),
                        Integer.parseInt(lineToken[3]),
                        Integer.parseInt(lineToken[4]));
                listToReturn.add(player);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Loading data error");
        }
        return listToReturn;
    }

    public void exportPlayersData(List<Player> playerList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("players.csv"))) {
            List<String> collect = playerList.stream()
                    .map(player -> {
                        StringJoiner sj = new StringJoiner(";");
                        sj.add(Integer.toString(player.getPlayerID()))
                                .add(player.getName())
                                .add(Integer.toString(player.getRouletteBalance()))
                                .add(Integer.toString(player.getSnakeRecord()))
                                .add(Integer.toString(player.getBlackJackBalance()));
                        return sj.toString();
                    }).collect(Collectors.toList());
            for (String player : collect) {
                writer.write(player);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Saving data error");
        }
    }

    public void deletePlayer(int id){
//        Iterator<Player> iterator = playerList.iterator();
//        while (iterator.hasNext()){
//            Player player = iterator.next();
//            if(player.getPlayerID()==id){
//                iterator.remove();
//            }
//            else throw new NoSuchElementException();
//        }
        playerList.removeIf(player -> player.getPlayerID()==id);
    }

    public void editPlayerName(int id, String name){
            playerList.stream()
                    .filter(player -> player.getPlayerID()==id)
                    .forEach(player -> player.setName(name));
    }

    public boolean checkIfCorrectId(int id){
        return playerList.stream().anyMatch(player -> player.getPlayerID()==id);
    }

    public boolean checkForUniqueName(String name){
        return playerList.stream().noneMatch(player -> player.getName().equalsIgnoreCase(name));
    }

    public void showPlayersData() {
        if(playerList.isEmpty()){
            System.out.println("There is no active players.");
        }
        playerList.forEach(System.out::println);
//        for (Player player : playerList) {
//            System.out.println(player);
//        }
    }

    public void showPlayers(){
        if(playerList.isEmpty()){
            System.out.println("There is no active players.");
        }
        playerList.stream().map(player -> {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getPlayerID());
            sb.append(". ");
            sb.append(player.getName());
            return sb.toString();
        }).forEach(System.out::println);
    }

    public void addNewPlayer(String name) {
        playerList.add(new Player(name));
    }

}
