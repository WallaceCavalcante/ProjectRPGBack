package com.estudo.rpg.Functions.Validations;

import com.estudo.rpg.Entity.Boss;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Repository.BossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class BossValidation {

    @Autowired
    BossRepository bossRepository;

    Scanner sc;

    public BossValidation(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
        this.sc = new Scanner(System.in);
    }

    public Boss validateBossExists(String id) {
        while (!id.matches("\\d") && !id.matches("\\d\\d")) {
            System.out.println("Por favor, digite um numero válido");
            id = sc.nextLine();
        }

        while (!bossRepository.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Esse chefão não existe, por favor, digite um numero válido");
            id = sc.nextLine();
            while (!id.matches("\\d") && !id.matches("\\d\\d")) {
                System.out.println("Por favor, digite um numero válido");
                id = sc.nextLine();
            }
        }
        Boss boss = bossRepository.findById(Long.valueOf(id)).get();
        return boss;
    }

    public boolean validateLevelDiff(Player player, Boss boss) {
        if (player.getLevel() >= boss.getLevel() - 5) {
            return true;
        }
        return false;
    }
}
