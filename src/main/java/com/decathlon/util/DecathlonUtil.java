package com.decathlon.util;

import com.decathlon.domain.DecathlonPlayer;
import com.decathlon.exceptions.*;

public class DecathlonUtil {

    public static int getTotalPoints(DecathlonPlayer decathlonPlayer) {
        if (decathlonPlayer == null) {
            throw new NullDecathlonPlayerException();
        }
        int totalPoint = 0;
        totalPoint += countTotalTrackEventPoints(decathlonPlayer);
        totalPoint += countTotalFieldEventsPoints(decathlonPlayer);

        return totalPoint;
    }

    public static int countTotalFieldEventsPoints(DecathlonPlayer decathlonPlayer) {
        if (decathlonPlayer == null) {
            throw new NullDecathlonPlayerException();
        }
        int longJumpPoints = countFieldEvent(DecathlonParameters.LONG_JUMP_A,
                DecathlonParameters.LONG_JUMP_B,
                DecathlonParameters.LONG_JUMP_C,
                decathlonPlayer.getLongJump());

        int shotPutPoints = countFieldEvent(DecathlonParameters.SHOT_PUT_A,
                DecathlonParameters.SHOT_PUT_B,
                DecathlonParameters.SHOT_PUT_C,
                decathlonPlayer.getShotPut());
        int highJumpPoints = countFieldEvent(DecathlonParameters.HIGH_JUMP_A,
                DecathlonParameters.HIGH_JUMP_B,
                DecathlonParameters.HIGH_JUMP_C,
                decathlonPlayer.getHighJump());

        int discusThrowPoints = countFieldEvent(DecathlonParameters.DISCUS_THROW_A,
                DecathlonParameters.DISCUS_THROW_B,
                DecathlonParameters.DISCUS_THROW_C,
                decathlonPlayer.getDiscusThrow());

        int poleVault = countFieldEvent(DecathlonParameters.POLE_VAULT_A,
                DecathlonParameters.POLE_VAULT_B,
                DecathlonParameters.POLE_VAULT_C,
                decathlonPlayer.getPoleVault());

        int javelinThrow = countFieldEvent(DecathlonParameters.JAVELIN_THROW_A,
                DecathlonParameters.JAVELIN_THROW_B,
                DecathlonParameters.JAVELIN_THROW_C,
                decathlonPlayer.getJavelinThrow());

        return longJumpPoints + shotPutPoints + highJumpPoints + discusThrowPoints + poleVault + javelinThrow;
    }

    public static int countTotalTrackEventPoints(DecathlonPlayer decathlonPlayer) throws NullDecathlonPlayerException {
        if (decathlonPlayer == null) {
            throw new NullDecathlonPlayerException();
        }
        int hundredMetresPoints = countTrackEvent(DecathlonParameters.HUNDRED_METRES_A,
                DecathlonParameters.HUNDRED_METRES_B,
                DecathlonParameters.HUNDRED_METRES_C,
                decathlonPlayer.getHundredMetres());

        int fourHundredMetresPoints = countTrackEvent(DecathlonParameters.FOUR_HUNDRED_METRES_A,
                DecathlonParameters.FOUR_HUNDRED_METRES_B,
                DecathlonParameters.FOUR_HUNDRED_METRES_C,
                decathlonPlayer.getFourHundredMetres());

        int hundredAndTenMetresPoints = countTrackEvent(DecathlonParameters.HUNDRED_AND_TEN_METRES_A,
                DecathlonParameters.HUNDRED_AND_TEN_METRES_B,
                DecathlonParameters.HUNDRED_AND_TEN_METRES_C,
                decathlonPlayer.getHundredAndTenHurdles());

        int fifteenHundredMetresPoints = countTrackEvent(DecathlonParameters.FIFTEEN_HUNDRED_METRES_A,
                DecathlonParameters.FIFTEEN_HUNDRED_METRES_B,
                DecathlonParameters.FIFTEEN_HUNDRED_METRES_C,
                decathlonPlayer.getFifteenHundredsMetres());

        return hundredMetresPoints + fourHundredMetresPoints + hundredAndTenMetresPoints + fifteenHundredMetresPoints;
    }

    public static DecathlonPlayer getPlayersFromString(String resultFileLine) throws EmptyOrNullPlayerStringException {

        if (resultFileLine == null || resultFileLine.isEmpty()) {
            throw new EmptyOrNullPlayerStringException();
        }
        DecathlonPlayer decathlonPlayer = new DecathlonPlayer();
        String[] details = resultFileLine.split(";");
        if (details.length == 11) {
            decathlonPlayer.setName(details[0]);
            decathlonPlayer.setHundredMetres(Float.parseFloat(details[1]));
            decathlonPlayer.setLongJump(DecathlonUtil.convertToCentiMetres(details[2]));
            decathlonPlayer.setShotPut(Float.parseFloat(details[3]));
            decathlonPlayer.setHighJump(DecathlonUtil.convertToCentiMetres(details[4]));
            decathlonPlayer.setFourHundredMetres(Float.parseFloat(details[5]));
            decathlonPlayer.setHundredAndTenHurdles(Float.parseFloat(details[6]));
            decathlonPlayer.setDiscusThrow(Float.parseFloat(details[7]));
            decathlonPlayer.setPoleVault(DecathlonUtil.convertToCentiMetres(details[8]));
            decathlonPlayer.setJavelinThrow(Float.parseFloat(details[9]));
            decathlonPlayer.setFifteenHundredsMetres(DecathlonUtil.convertToSeconds(details[10]));
            System.out.println(decathlonPlayer);
        } else {
            throw new InvalidPlayerStringException();
        }

        return decathlonPlayer;
    }

    public static int countTrackEvent(double a, double b, double c, float playerScore) {
        return (int) Math.floor(a * (Math.pow((b - playerScore), c)));
    }

    public static int countFieldEvent(double a, double b, double c, double playerScore) {
        return (int) Math.floor(a * (Math.pow((playerScore - b), c)));
    }

    public static float convertToSeconds(String minuteSecondStr)
            throws NumberFormatException, IllegalTimeStringException, NullTimeStringException {

        if (minuteSecondStr == null) {
            throw new NullTimeStringException();
        }

        String[] timeArr = minuteSecondStr.split(":");

        if (timeArr.length != 2) {
            System.out.println();
            throw new IllegalTimeStringException();
        }
        int minutes = Integer.parseInt(timeArr[0]);
        float seconds = Float.parseFloat(timeArr[1]);
        float totalSeconds = (minutes * 60) + seconds;

        return totalSeconds;
    }

    public static float convertToCentiMetres(String metres) {
        if (metres == null) {
            throw new NullMetresStringException();
        }
        return Float.parseFloat(metres) * 100;
    }
}