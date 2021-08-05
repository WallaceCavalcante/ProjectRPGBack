package com.estudo.rpg.Functions.Validations;

import com.estudo.rpg.Entity.Monster;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MonsterValidation {

    @Autowired
    MonsterRepository monsterRepository;

    Scanner sc;

    public MonsterValidation(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
        this.sc = new Scanner(System.in);
    }

    public Monster validateMonsterExists(String id) {
        while (!id.matches("\\d") && !id.matches("\\d\\d")) {
            System.out.println("Por favor, digite um numero válido");
            id = sc.nextLine();
        }

        while (!monsterRepository.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Esse monstro não existe, por favor, digite um numero válido");
            id = sc.nextLine();
            while (!id.matches("\\d") && !id.matches("\\d\\d")) {
                System.out.println("Por favor, digite um numero válido");
                id = sc.nextLine();
            }
        }
        Monster monster = monsterRepository.findById(Long.valueOf(id)).get();
        return monster;
    }

    public boolean validateMonsterLevel(Player player, Monster monster) {
        if (player.getLevel() >= monster.getLevel() - 5) {
            return true;
        }
        return false;
    }
}
