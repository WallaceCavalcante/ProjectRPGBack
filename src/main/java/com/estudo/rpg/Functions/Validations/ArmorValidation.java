package com.estudo.rpg.Functions.Validations;

import com.estudo.rpg.Entity.Armor;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Functions.Loading;
import com.estudo.rpg.Functions.Updates.UpdateEquipment;
import com.estudo.rpg.Interactor;
import com.estudo.rpg.Repository.ArmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ArmorValidation {

    @Autowired
    ArmorRepository armorRepository;

    Scanner sc;
    Loading loading;
    Interactor interactor;
    UpdateEquipment updateEquipment;

    public ArmorValidation(ArmorRepository armorRepository, Loading loading, @Lazy Interactor interactor, UpdateEquipment updateEquipment) {
        this.armorRepository = armorRepository;
        this.sc = new Scanner(System.in);
        this.loading = loading;
        this.interactor = interactor;
        this.updateEquipment = updateEquipment;
    }

    public Armor validateArmorExists(String id) {
        while (!id.matches("\\d") && !id.matches("\\d\\d")) {
            System.out.println("Por favor, digite um numero válido");
            id = sc.nextLine();
        }

        while (!armorRepository.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Essa armadura não existe, por favor, digite um numero válido");
            id = sc.nextLine();
            while (!id.matches("\\d") && !id.matches("\\d\\d")) {
                System.out.println("Por favor, digite um numero válido");
                id = sc.nextLine();
            }
        }
        return armorRepository.findById(Long.valueOf(id)).get();
    }

    public void validateIsBetterArmor(Armor newArmor, Player player) {
        System.out.println("Você recebeu uma " + newArmor.getName() + ", que possui: " + newArmor.getDefense() + " de defesa.");
        if (newArmor.getDefense() > player.getArmor().getDefense()) {
            System.out.println();
            System.out.println("Por ser mais forte que a sua " + player.getArmor().getName() + " a " + newArmor.getName() + " será equipada.");
            System.out.println();
            loading.loading(2000);
            updateEquipment.updateArmor(newArmor, player);
        } else {
            System.out.println("Sua armadura é mais forte do que a armadura recebida, portanto não será equipada");
        }
    }
}
