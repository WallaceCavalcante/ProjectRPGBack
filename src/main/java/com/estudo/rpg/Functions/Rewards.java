package com.estudo.rpg.Functions;

import com.estudo.rpg.Entity.Armor;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Repository.ArmorRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Rewards {

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    ArmorRepository armorRepository;

    public Rewards() {
    }

    public long basicArmorReward() {
        return 2L;
    }

    public long basicWeaponReward(String classe) {
        switch (classe) {
            case "Assassino":
                return 3;
            case "Guerreiro":
                return 1;
            case "Viking":
                return 2;
            case "Arqueiro":
                return 4;
            case "Pistoleiro":
                return 20;
            default:
                return 5;
        }
    }

    public long intermediateWeaponReward(String classe) {
        switch (classe) {
            case "Assassino":
                return 23;
            case "Guerreiro":
                return 21;
            case "Viking":
                return 22;
            case "Arqueiro":
                return 24;
            case "Pistoleiro":
                return 26;
            default:
                return 25;
        }
    }

    public long advancedWeaponReward(String classe) {
        switch (classe) {
            case "Assassino":
                return 30;
            case "Guerreiro":
                return 28;
            case "Viking":
                return 29;
            case "Arqueiro":
                return 31;
            case "Pistoleiro":
                return 33;
            default:
                return 32;
        }
    }

    public long heroicWeaponReward(String classe) {
        switch (classe) {
            case "Assassino":
                return 37;
            case "Guerreiro":
                return 35;
            case "Viking":
                return 36;
            case "Arqueiro":
                return 38;
            case "Pistoleiro":
                return 40;
            default:
                return 39;
        }
    }


    public Long validationGachaWeaponReward(int weaponLevel, String weaponType) {
        switch (weaponLevel) {
            case 0:
                return basicWeaponReward(weaponType);
            case 1:
                return intermediateWeaponReward(weaponType);
            case 2:
                return advancedWeaponReward(weaponType);
            case 3:
                return heroicWeaponReward(weaponType);
        }
        return 7L;
    }
}
