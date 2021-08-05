package com.estudo.rpg.Functions.Comparisons;

import com.estudo.rpg.Entity.Armor;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Quest;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Functions.Rewards;
import com.estudo.rpg.Functions.Validations.ArmorValidation;
import com.estudo.rpg.Functions.Validations.WeaponValidation;
import com.estudo.rpg.Interactor;
import com.estudo.rpg.Repository.ArmorRepository;
import com.estudo.rpg.Repository.PlayerRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class RewardsComparison {

    @Autowired
    WeaponRepository weaponRepository;
    @Autowired
    ArmorRepository armorRepository;
    @Autowired
    PlayerRepository playerRepository;

    WeaponValidation weaponValidation;
    ArmorValidation armorValidation;
    Rewards rewards;
    Interactor interactor;

    public RewardsComparison(WeaponRepository weaponRepository, ArmorRepository armorRepository, PlayerRepository playerRepository, WeaponValidation weaponValidation, ArmorValidation armorValidation, Rewards rewards, @Lazy Interactor interactor) {
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.playerRepository = playerRepository;
        this.weaponValidation = weaponValidation;
        this.armorValidation = armorValidation;
        this.rewards = rewards;
        this.interactor = interactor;
    }

    public void compareMonsterQuestForReward(Quest quest, Player player) {
        System.out.println();
        if (quest.getId() == 1) {
            System.out.println("Você recebeu 50 de experiencia");
            System.out.println();
            interactor.calculatePlayerXp(50);
            playerRepository.save(player);
        } else if (quest.getId() == 2) {
            Weapon newWeapon = weaponRepository.findById(rewards.intermediateWeaponReward(player.getClasse())).get();
            System.out.println("Você recebeu 100 de experiencia");
            System.out.println();
            weaponValidation.validateIsBetterWeapon(newWeapon, player);
            interactor.calculatePlayerXp(100);
        } else if (quest.getId() == 3) {
            Armor newArmor = armorRepository.findById(3L).get();
            System.out.println("Você recebeu 150 de experiencia");
            System.out.println();
            armorValidation.validateIsBetterArmor(newArmor, player);
            interactor.calculatePlayerXp(150);
        } else if (quest.getId() == 4) {
            Weapon newWeapon = weaponRepository.findById(rewards.advancedWeaponReward(player.getClasse())).get();
            System.out.println("Você recebeu 250 de experiencia");
            System.out.println();
            weaponValidation.validateIsBetterWeapon(newWeapon, player);
            interactor.calculatePlayerXp(250);
        } else if (quest.getId() == 5) {
            Armor newArmor = armorRepository.findById(4L).get();
            System.out.println("Você recebeu 400 de experiencia");
            System.out.println();
            armorValidation.validateIsBetterArmor(newArmor, player);
            interactor.calculatePlayerXp(400);
        }
        System.out.println();
    }

    public void compareBossQuestForReward(Quest quest, Player player) {
        if (quest.getId() == 6) {
            Weapon newWeapon = weaponRepository.findById(rewards.heroicWeaponReward(player.getClasse())).get();
            System.out.println("Você recebeu 1000 de experiencia");
            System.out.println();
            weaponValidation.validateIsBetterWeapon(newWeapon, player);
            interactor.calculatePlayerXp(1000);
        } else if (quest.getId() == 13) {
            System.out.println("Você recebeu 5000 de experiencia");
            System.out.println();
            interactor.calculatePlayerXp(5000);
        }
        System.out.println();
    }
}
