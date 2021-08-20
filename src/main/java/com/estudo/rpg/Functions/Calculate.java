package com.estudo.rpg.Functions;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Functions.Updates.UpdatePlayer;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class Calculate {

    UpdatePlayer updatePlayer;

    public Calculate(@Lazy UpdatePlayer updatePlayer) {
        this.updatePlayer = updatePlayer;
    }

    public Calculate() {

    }

    public int calculateXpToLevelUp(Player player) {
        int xpToLevelUp = player.getLevel() * 10;
        return xpToLevelUp;
    }

    public double calculatePlayerHp(String classe) {
        switch (classe) {
            case "Assassino":
                return 12.5;
            case "Guerreiro":
                return 20;
            case "Viking":
                return 18;
            case "Arqueiro":
                return 14;
            case "Pistoleiro":
                return 11.5;
            default:
                return 11;
        }
    }

    public double calculateLevelUpSumPlayerHp(String classe) {
        switch (classe) {
            case "Assassino":
                return 4;
            case "Guerreiro":
                return 8;
            case "Viking":
                return 7;
            case "Pistoleiro":
                return 2.5;
            default:
                return 3;
        }
    }

    public double calculatePlayerAttackSpeed(String classe) {
        switch (classe) {
            case "Assassino":
                return 1.5;
            case "Guerreiro":
                return 1;
            case "Viking":
                return 0.9;
            case "Arqueiro":
                return 1.2;
            case "Pistoleiro":
                return 2.0;
            default:
                return 1.5;
        }
    }

    public double calculateMonsterAttackSpeed(String race) {
        switch (race) {
            case "Orc":
                return 0.9;
            case "Goblin":
                return 1.1;
            default:
                return 1;
        }
    }

}
