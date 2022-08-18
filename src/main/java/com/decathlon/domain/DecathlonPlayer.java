package com.decathlon.domain;

public class DecathlonPlayer extends Person {
    private float hundredMetres;
    private float longJump;
    private float shotPut;
    private float highJump;
    private float fourHundredMetres;
    private float hundredAndTenHurdles;
    private float discusThrow;
    private float poleVault;
    private float javelinThrow;
    private float fifteenHundredsMetres;
    private int totalPoints;
    private String playerRank;

    public String getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }

    public float getHundredMetres() {
        return hundredMetres;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setHundredMetres(float hundredMetres) {
        this.hundredMetres = hundredMetres;
    }

    public float getLongJump() {
        return longJump;
    }

    public void setLongJump(float longJump) {
        this.longJump = longJump;
    }

    public float getShotPut() {
        return shotPut;
    }

    public void setShotPut(float shotPut) {
        this.shotPut = shotPut;
    }

    public float getHighJump() {
        return highJump;
    }

    public void setHighJump(float highJump) {
        this.highJump = highJump;
    }

    public float getFourHundredMetres() {
        return fourHundredMetres;
    }

    public void setFourHundredMetres(float fourHundredMetres) {
        this.fourHundredMetres = fourHundredMetres;
    }

    public float getHundredAndTenHurdles() {
        return hundredAndTenHurdles;
    }

    public void setHundredAndTenHurdles(float hundredAndTenHurdles) {
        this.hundredAndTenHurdles = hundredAndTenHurdles;
    }

    public float getDiscusThrow() {
        return discusThrow;
    }

    public void setDiscusThrow(float discusThrow) {
        this.discusThrow = discusThrow;
    }

    public float getPoleVault() {
        return poleVault;
    }

    public void setPoleVault(float poleVault) {
        this.poleVault = poleVault;
    }

    public float getJavelinThrow() {
        return javelinThrow;
    }

    public void setJavelinThrow(float javelinThrow) {
        this.javelinThrow = javelinThrow;
    }

    public float getFifteenHundredsMetres() {
        return fifteenHundredsMetres;
    }

    public void setFifteenHundredsMetres(float fifteenHundredsMetres) {
        this.fifteenHundredsMetres = fifteenHundredsMetres;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Name:").append(getName())
                .append(", 100m:").append(hundredMetres)
                .append(", Long jump:").append(longJump)
                .append(", Shot put:").append(shotPut)
                .append(", High jump:").append(highJump)
                .append(", 400m:").append(fourHundredMetres)
                .append(", 110m hurdles:").append(hundredAndTenHurdles)
                .append(", Discus throw:").append(discusThrow)
                .append(", Pole vault:").append(poleVault)
                .append(", Javelin throw:").append(javelinThrow)
                .append(", 1500m:").append(fifteenHundredsMetres)
                .append(", Total Points=").append(totalPoints)
                .append(", Rank=").append(playerRank)
                .toString();

    }
}
