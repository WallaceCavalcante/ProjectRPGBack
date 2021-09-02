package com.estudo.rpg.Entity;

public class BattleResults {

    private boolean winner;
    private Double playerRolled;
    private Double opponentRolled;
    private int coinsDropped;

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Double getPlayerRolled() {
        return playerRolled;
    }

    public void setPlayerRolled(Double playerRolled) {
        this.playerRolled = playerRolled;
    }

    public Double getOpponentRolled() {
        return opponentRolled;
    }

    public void setOpponentRolled(Double opponentRolled) {
        this.opponentRolled = opponentRolled;
    }

    public int getCoinsDropped() {
        return coinsDropped;
    }

    public void setCoinsDropped(int coinsDropped) {
        this.coinsDropped = coinsDropped;
    }
}
