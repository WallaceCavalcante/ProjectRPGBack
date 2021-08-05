package com.estudo.rpg.Functions.Updates;

import com.estudo.rpg.Entity.Boss;
import com.estudo.rpg.Entity.Monster;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Functions.Print;
import com.estudo.rpg.Functions.Calculate;
import com.estudo.rpg.Interactor;
import com.estudo.rpg.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UpdatePlayer {

    @Autowired
    PlayerRepository playerRepository;

    Print print;
    Calculate calculate;
    Scanner sc;
    Interactor interactor;

    public UpdatePlayer(PlayerRepository playerRepository, Print print, Calculate calculate, @Lazy Interactor interactor) {
        this.playerRepository = playerRepository;
        this.print = print;
        this.calculate = calculate;
        this.sc = new Scanner(System.in);
        this.interactor = interactor;
    }


    public void updateLevelUpVerify(Player player) {
        while (player.getXp() >= interactor.getXpToLevelUp()) {
            player.setXp(player.getXp() - interactor.getXpToLevelUp());
            player.setLevel(player.getLevel() + 1);
            player.setHp(player.getHp() + 5);
            print.printLevelUp();
            interactor.setXpToLevelUp(calculate.calculateXpToLevelUp(player));
            System.out.println("Parabéns, agora seu level é " + player.getLevel());
            System.out.println();
            System.out.println("Você ganhou + 5 de HP, seu HP atual é: " + player.getHp());
            System.out.println("Enter para continuar...");
            playerRepository.save(player);
            sc.nextLine();
        }
    }

    public void updatePlayerXpMonster(Player player, Monster monster) {
        player.setXp(player.getXp() + monster.getXpWhenKilled());
        updateLevelUpVerify(player);
    }

    public void updatePlayerXpBoss(Player player, Boss boss) {
        player.setXp(player.getXp() + boss.getXpWhenKilled());
        updateLevelUpVerify(player);
    }
}
