package com.decathlon.util;

import com.decathlon.domain.DecathlonPlayer;

import java.util.Comparator;

public class PlayerSortByPoints implements Comparator<DecathlonPlayer> {

    @Override
    public int compare(DecathlonPlayer player1, DecathlonPlayer player2) {
        return player2.getTotalPoints() - player1.getTotalPoints();
    }

}
