package com.estudo.rpg.Functions.Updates;

import com.estudo.rpg.Entity.Armor;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UpdateEquipment {

    @Autowired
    PlayerRepository playerRepository;

    Scanner sc;

    public UpdateEquipment(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.sc = new Scanner(System.in);
    }

    public void updateWeapon(Weapon weapon, Player player) {
        System.out.println("Arma alterada com sucesso!");
        System.out.println("Você acaba de trocar sua " + player.getWeapon().getName() + " que possui " + player.getWeapon().getAttack() + " de ataque pela: ");
        System.out.println(weapon.getName() + " que possui " + weapon.getAttack() + " de ataque");
        System.out.println();
        player.setWeapon(weapon);
        playerRepository.save(player);
        System.out.println("Enter para continuar...");
        sc.nextLine();
    }

    public void updateArmor(Armor armor, Player player) {
        System.out.println("Armadura alterada com sucesso!");
        System.out.println("Você acaba de trocar sua " + player.getArmor().getName() + " que possui " + player.getArmor().getDefense() + " de defesa pela: ");
        System.out.println(armor.getName() + " que possui " + armor.getDefense() + " de defesa");
        System.out.println();
        player.setArmor(armor);
        playerRepository.save(player);
        System.out.println("Enter para continuar...");
        sc.nextLine();
    }
}
