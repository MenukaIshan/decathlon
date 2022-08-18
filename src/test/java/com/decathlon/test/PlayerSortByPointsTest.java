package com.decathlon.test;

import com.decathlon.domain.DecathlonPlayer;
import static org.junit.jupiter.api.Assertions.*;

import com.decathlon.util.DecathlonUtil;
import com.decathlon.util.PlayerSortByPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test PlayerSortByPoints Comparator")
public class PlayerSortByPointsTest {

    PlayerSortByPoints playerSortByPoints = new PlayerSortByPoints();

    @Test
    @DisplayName("Test equal valued object comparison")
    public void testEqualDecathlonPlayers(){

        DecathlonPlayer player1 = DecathlonUtil.getPlayersFromString("Menuka Ishan;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76");
        player1.setTotalPoints(DecathlonUtil.getTotalPoints(player1));

        DecathlonPlayer player2 = DecathlonUtil.getPlayersFromString("Jane Doe;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76");
        player2.setTotalPoints(DecathlonUtil.getTotalPoints(player2));

        assertTrue(playerSortByPoints.compare(player1, player2) == 0);
    }

    @Test
    @DisplayName("Test when Player 1 has higher total points")
    public void testPlayerOneLarger(){

        DecathlonPlayer player1 = DecathlonUtil.getPlayersFromString("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72");
        player1.setTotalPoints(DecathlonUtil.getTotalPoints(player1));

        DecathlonPlayer player2 = DecathlonUtil.getPlayersFromString("Jane Doe;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76");
        player2.setTotalPoints(DecathlonUtil.getTotalPoints(player2));

        assertTrue(playerSortByPoints.compare(player1, player2) < 0);
    }

    @Test
    @DisplayName("Test when Player 2 has higher total points")
    public void testPlayerTwoLarger(){

        DecathlonPlayer player1 = DecathlonUtil.getPlayersFromString("Jane Doe;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76");
        player1.setTotalPoints(DecathlonUtil.getTotalPoints(player1));

        DecathlonPlayer player2 = DecathlonUtil.getPlayersFromString("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72");
        player2.setTotalPoints(DecathlonUtil.getTotalPoints(player2));

        assertTrue(playerSortByPoints.compare(player1, player2) > 0);
    }
}
