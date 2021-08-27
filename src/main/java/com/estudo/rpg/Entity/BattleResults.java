package com.estudo.rpg.Entity;

public class BattleResults {

    private boolean winner;
    private Double playerRolled;
    private Double opponentRolled;

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
}
