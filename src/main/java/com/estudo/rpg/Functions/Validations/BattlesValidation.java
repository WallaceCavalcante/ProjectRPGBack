package com.estudo.rpg.Functions.Validations;


import com.estudo.rpg.Entity.Boss;
import com.estudo.rpg.Entity.Monster;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Functions.Calculate;
import com.estudo.rpg.Functions.Loading;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class BattlesValidation {

    Loading loading;
    Calculate calculate;

    DecimalFormat decimalFormat;


    public BattlesValidation(Calculate calculate) {
        this.calculate = calculate;
        this.decimalFormat = new DecimalFormat();
    }

    public boolean validateKillMonster(Player player, Monster monster) {
        double playerHp = player.getHp();
        double playerAttack = (player.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(player.getClasse())) - monster.getArmor().getDefense();
        double monsterHp = monster.getHp();
        double monsterAttack = (monster.getWeapon().getAttack() * calculate.calculateMonsterAttackSpeed(monster.getRace())) - player.getArmor().getDefense();
        if (playerAttack <= 0) playerAttack = 1;
        if (monsterAttack <= 0) monsterAttack = 1;

        while (playerHp > 0 && monsterHp > 0) {
            monsterHp -= playerAttack;
            System.out.println("Você tirou " + decimalFormat.format(playerAttack) + " de dano");
            if (monsterHp < 0) monsterHp = 0;
            System.out.println("Hp restante do monstro: " + decimalFormat.format(monsterHp));
            System.out.println();
            loading.loading(2000);
            if (monsterHp <= 0) {
                return true;
            }
            playerHp -= monsterAttack;
            System.out.println("Você recebeu " + decimalFormat.format(monsterAttack) + " de dano");
            if (playerHp < 0) playerHp = 0;
            System.out.println("Seu hp restante: " + decimalFormat.format(playerHp));
            System.out.println();
            loading.loading(2000);
        }
        if (playerHp <= 0 && monsterHp > 0) {
            return false;
        } else if (playerHp > 0 && monsterHp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateKillBoss(Player player, Boss boss) {
        double playerHp = player.getHp();
        double playerAttack = (player.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(player.getClasse())) - boss.getArmor().getDefense();
        double bossHp = boss.getHp();
        double bossAttack = (boss.getWeapon().getAttack() * calculate.calculateMonsterAttackSpeed(boss.getRace())) - player.getArmor().getDefense();
        if (playerAttack <= 0) playerAttack = 1;
        if (bossAttack <= 0) bossAttack = 1;

        while (playerHp > 0 && bossHp > 0) {
            bossHp -= playerAttack;
            System.out.println("Você tirou " + decimalFormat.format(playerAttack) + " de dano");
            if (bossHp < 0) bossHp = 0;
            System.out.println("Hp restante do chefão: " + decimalFormat.format(bossHp));
            System.out.println();
            loading.loading(2000);
            if (bossHp <= 0) {
                return true;
            }
            playerHp -= bossAttack;
            System.out.println("Você recebeu " + decimalFormat.format(bossAttack) + " de dano");
            if (playerHp < 0) playerHp = 0;
            System.out.println("Seu hp restante: " + decimalFormat.format(playerHp));
            System.out.println();
            loading.loading(2000);
        }
        if (playerHp <= 0 && bossHp > 0) {
            return false;
        } else if (playerHp > 0 && bossHp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateKillOpponent(Player player, Player opponent) {
        double playerHp = player.getHp();
        double playerAttack = (player.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(player.getClasse())) - opponent.getArmor().getDefense();
        double opponentHp = opponent.getHp();
        double opponentAttack = (opponent.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(opponent.getClasse())) - player.getArmor().getDefense();
        if (playerAttack <= 0) playerAttack = 1;
        if (opponentAttack <= 0) opponentAttack = 1;

        while (playerHp > 0 && opponentHp > 0) {
            opponentHp -= playerAttack;
            System.out.println("Você tirou " + decimalFormat.format(playerAttack) + " de dano");
            if (opponentHp < 0) opponentHp = 0;
            System.out.println("Hp restante do oponente " + opponent.getNickname() + ": " + decimalFormat.format(opponentHp));
            System.out.println();
            loading.loading(2000);
            if (opponentHp <= 0) {
                return true;
            }
            playerHp -= opponentAttack;
            System.out.println("Você recebeu " + decimalFormat.format(opponentAttack) + " de dano");
            if (playerHp < 0) playerHp = 0;
            System.out.println("Seu hp restante: " + decimalFormat.format(playerHp));
            System.out.println();
            loading.loading(2000);
        }
        if (playerHp <= 0 && opponentHp > 0) {
            return false;
        } else if (playerHp > 0 && opponentHp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateKillOpponentNoLoading(Player player, Player opponent) {
        double playerHp = player.getHp();
        double playerAttack = (player.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(player.getClasse())) - opponent.getArmor().getDefense();
        double opponentHp = opponent.getHp();
        double opponentAttack = (opponent.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(opponent.getClasse())) - player.getArmor().getDefense();
        int leftMoves = 100;
        if (playerAttack <= 0) playerAttack = 1;
        if (opponentAttack <= 0) opponentAttack = 1;

        while (playerHp > 0 && opponentHp > 0 && leftMoves > 0) {
            opponentHp -= playerAttack;
            if (opponentHp < 0) opponentHp = 0;
            if (opponentHp <= 0) {
                return true;
            }
            playerHp -= opponentAttack;
            if (playerHp < 0) playerHp = 0;
            leftMoves--;
        }
        if(leftMoves == 0){
            return playerHp > opponentHp;
        }
        return playerHp > 0;
    }

    public boolean validateKillOpponentRandomValue(Player player, Player opponent) {
        double playerMaxAttack = player.getHp() * player.getWeapon().getAttack() * player.getArmor().getDefense() * calculate.calculatePlayerAttackSpeed(player.getClasse());
        double playerMinAttack = (player.getHp() * player.getWeapon().getAttack() * player.getArmor().getDefense() * calculate.calculatePlayerAttackSpeed(player.getClasse())) / 2;

        double opponentMaxAttack = opponent.getHp() * opponent.getWeapon().getAttack() * opponent.getArmor().getDefense() * calculate.calculatePlayerAttackSpeed(opponent.getClasse());
        double opponentMinAttack = (opponent.getHp() * opponent.getWeapon().getAttack() * opponent.getArmor().getDefense() * calculate.calculatePlayerAttackSpeed(opponent.getClasse())) / 2;

        double playerAttackRolled = (Math.random() * ((playerMaxAttack - playerMinAttack) + 1)) + playerMinAttack;
        System.out.println("Player atacou com: " + decimalFormat.format(playerAttackRolled) + " de dano!");
        double opponentAttackRolled = (Math.random() * ((opponentMaxAttack - opponentMinAttack) + 1)) + opponentMinAttack;
        System.out.println("Oponente atacou com: " + decimalFormat.format(opponentAttackRolled) + " de dano!\n\n");

        return playerAttackRolled > opponentAttackRolled;
    }


    public boolean validateKillMonsterNoLoading(Player player, Monster monster) {
        double playerHp = player.getHp();
        double playerAttack = (player.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(player.getClasse())) - monster.getArmor().getDefense();
        double monsterHp = monster.getHp();
        double monsterAttack = (monster.getWeapon().getAttack() * calculate.calculateMonsterAttackSpeed(monster.getRace())) - player.getArmor().getDefense();
        int leftMoves = 100;
        if (playerAttack <= 0) playerAttack = 1;
        if (monsterAttack <= 0) monsterAttack = 1;

        while (playerHp > 0 && monsterHp > 0 && leftMoves > 0) {
            monsterHp -= playerAttack;
            if (monsterHp < 0) monsterHp = 0;
            if (monsterHp <= 0) {
                return true;
            }
            playerHp -= monsterAttack;
            if (playerHp < 0) playerHp = 0;
            leftMoves--;
        }
        if(leftMoves == 0){
            return playerHp > monsterHp;
        }
        return playerHp > 0;
    }

    public boolean validateKillBossNoLoading(Player player, Boss boss) {
        double playerHp = player.getHp();
        double playerAttack = (player.getWeapon().getAttack() * calculate.calculatePlayerAttackSpeed(player.getClasse())) - boss.getArmor().getDefense();
        double bossHp = boss.getHp();
        double bossAttack = (boss.getWeapon().getAttack() * calculate.calculateMonsterAttackSpeed(boss.getRace())) - player.getArmor().getDefense();
        int leftMoves = 100;
        if (playerAttack <= 0) playerAttack = 1;
        if (bossAttack <= 0) bossAttack = 1;

        while (playerHp > 0 && bossHp > 0 && leftMoves > 0) {
            bossHp -= playerAttack;
            if (bossHp < 0) bossHp = 0;
            if (bossHp <= 0) {
                return true;
            }
            playerHp -= bossAttack;
            if (playerHp < 0) playerHp = 0;
            leftMoves--;
        }
        if(leftMoves == 0){
            return playerHp > bossHp;
        }
        return playerHp > 0;
    }
}
