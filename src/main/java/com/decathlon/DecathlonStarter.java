package com.decathlon;

import com.decathlon.domain.DecathlonPlayer;
import com.decathlon.exceptions.*;
import com.decathlon.util.DecathlonUtil;
import com.decathlon.util.PlayerSortByPoints;
import com.decathlon.util.XMLGeneratorUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecathlonStarter {

    private static String RESULT_FILE_PATH = "src/main/resources/results.csv";
    private static String PLAYER_RANK_OUTPUT_XML_FILE_PATH = "output.xml";

    /*
     * We don't calculate ranks if there is an issue in results.csv
     * Because it wouldn't be fair for the players who had corrupted data
     * */
    public static void main(String[] args) throws IOException {
        List<DecathlonPlayer> decathlonPlayers = new ArrayList<>();

        try {
            //Convert file into DecathlonPlayer objects
            decathlonPlayers = getPlayersFromFile(RESULT_FILE_PATH);

            //Sorting decathlon players according to total points
            Collections.sort(decathlonPlayers, new PlayerSortByPoints());

            //Rank assign according to total points
            assignRanksToPlayers(decathlonPlayers);

            //Writing to output file
            XMLGeneratorUtil.xmlFileWriter(PLAYER_RANK_OUTPUT_XML_FILE_PATH, decathlonPlayers);
            System.out.println("---------------------------");
            System.out.println("Decathlon Results");
            for (DecathlonPlayer player : decathlonPlayers) {
                System.out.println("Name: " + player.getName() + " Total Points: " + player.getTotalPoints() + " Rank: " + player.getPlayerRank());
            }
            System.out.println("---------------------------");


        } catch (FileWriteException e) {
            System.out.println("File writing error ->" + e.getMessage());
            e.printStackTrace();
        } catch (EmptyOrNullPlayerStringException e) {
            System.out.println("Null or empty player String error error -> " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalTimeStringException e) {
            System.out.println("File reading error -> " + e.getMessage());
            e.printStackTrace();
        } catch (InvalidPlayerStringException e) {
            System.out.println("Invalid player string error -> " + e.getMessage());
            e.printStackTrace();
        } catch (NullDecathlonPlayerException e) {
            System.out.println("Null Decathlon player error -> " + e.getMessage());
            e.printStackTrace();
        } catch (NullMetresStringException e) {
            System.out.println("Metres String error -> " + e.getMessage());
            e.printStackTrace();
        } catch (NullTimeStringException e) {
            System.out.println("Time String error -> " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File reading error -> " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Program error occurred -> " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<DecathlonPlayer> assignRanksToPlayers(List<DecathlonPlayer> decathlonPlayers) {
        for (int index = 0; index < decathlonPlayers.size(); index++) {
            if (index == 0) {
                decathlonPlayers.get(index).setPlayerRank("1");
            } else {
                DecathlonPlayer currentPlayer = decathlonPlayers.get(index);
                DecathlonPlayer previousPlayer = decathlonPlayers.get(index - 1);

                if (currentPlayer.getTotalPoints() == previousPlayer.getTotalPoints()) {
                    String previousPlayerRank = previousPlayer.getPlayerRank();
                    previousPlayer.setPlayerRank(previousPlayerRank + "-" + (index + 1));
                    currentPlayer.setPlayerRank(previousPlayerRank + "-" + (index + 1));
                } else {
                    currentPlayer.setPlayerRank("" + (index + 1));
                }
            }
        }
        return decathlonPlayers;
    }

    public static List<DecathlonPlayer> getPlayersFromFile(String playerResultsFilePath)
            throws IOException {
        if (playerResultsFilePath == null) {
            System.out.println("Null file path received!");
            throw new NullFilePathException();
        }
        List<DecathlonPlayer> decathlonPlayers = new ArrayList<>();
        Path path = Paths.get(playerResultsFilePath);
        BufferedReader reader = Files.newBufferedReader(path);
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (!currentLine.isEmpty()) {
                decathlonPlayers.add(DecathlonUtil.getPlayersFromString(currentLine));
            }
        }

        for (DecathlonPlayer player : decathlonPlayers) {
            player.setTotalPoints(DecathlonUtil.getTotalPoints(player));
        }

        if (reader != null) {
            reader.close();
        }
        return decathlonPlayers;
    }

}
