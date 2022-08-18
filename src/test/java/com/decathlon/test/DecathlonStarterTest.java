package com.decathlon.test;

import com.decathlon.DecathlonStarter;
import com.decathlon.domain.DecathlonPlayer;
import com.decathlon.exceptions.NullFilePathException;
import com.decathlon.util.PlayerSortByPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test DecathlonStarter class")
public class DecathlonStarterTest {

    @Test
    @DisplayName("Test correct decathlon result")
    public void testDecathlonResultRead() {
        try {
            List<DecathlonPlayer> playerList = DecathlonStarter
                    .getPlayersFromFile("src/main/resources/results.csv");
            assertTrue(playerList.size() == 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test decathlon file read with wrong file path")
    public void testDecathlonResultReadWrongPath() {
        assertThrows(NoSuchFileException.class,
                () -> DecathlonStarter.getPlayersFromFile("src/main/resources/resultsnot.csv"));
    }

    @Test
    @DisplayName("Test decathlon file read with empty file path")
    public void testDecathlonResultReadEmptyPath() {
        assertThrows(AccessDeniedException.class,
                () -> DecathlonStarter.getPlayersFromFile(""));
    }

    @Test
    @DisplayName("Test decathlon file read with empty file path")
    public void testDecathlonResultReadNullPath() {
        assertThrows(NullFilePathException.class,
                () -> DecathlonStarter.getPlayersFromFile(null));
    }

    @Test
    @DisplayName("Test Rank assign success")
    public void testRankAssignMethod(){
        try {
            List<DecathlonPlayer> decathlonPlayers = DecathlonStarter
                    .getPlayersFromFile("src/main/resources/results.csv");
            Collections.sort(decathlonPlayers, new PlayerSortByPoints());
            DecathlonStarter.assignRanksToPlayers(decathlonPlayers);
            assertAll("Check each player rank",
                    () -> assertEquals("1", decathlonPlayers.get(0).getPlayerRank()),
                    () -> assertEquals("2", decathlonPlayers.get(1).getPlayerRank()),
                    () -> assertEquals("3", decathlonPlayers.get(2).getPlayerRank()),
                    () -> assertEquals("4", decathlonPlayers.get(3).getPlayerRank()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
