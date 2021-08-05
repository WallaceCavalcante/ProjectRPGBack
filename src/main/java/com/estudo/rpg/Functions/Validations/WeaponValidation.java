package com.estudo.rpg.Functions.Validations;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Functions.Loading;
import com.estudo.rpg.Functions.Updates.UpdateEquipment;
import com.estudo.rpg.Interactor;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class WeaponValidation {

    @Autowired
    WeaponRepository weaponRepository;

    Scanner sc;
    Loading loading;
    Interactor interactor;
    UpdateEquipment updateEquipment;

    public WeaponValidation(WeaponRepository weaponRepository, Loading loading, @Lazy Interactor interactor, UpdateEquipment updateEquipment) {
        this.weaponRepository = weaponRepository;
        this.sc = new Scanner(System.in);
        this.loading = loading;
        this.interactor = interactor;
        this.updateEquipment = updateEquipment;
    }

    public Weapon validateWeaponExists(String id) {
        while (!id.matches("\\d") && !id.matches("\\d\\d")) {
            System.out.println("Por favor, digite um numero válido");
            id = sc.nextLine();
        }

        while (!weaponRepository.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Essa arma não existe, por favor, digite um numero válido");
            id = sc.nextLine();
            while (!id.matches("\\d") && !id.matches("\\d\\d")) {
                System.out.println("Por favor, digite um numero válido");
                id = sc.nextLine();
            }
        }
        return weaponRepository.findById(Long.valueOf(id)).get();
    }

    public void validateIsBetterWeapon(Weapon newWeapon, Player player) {
        System.out.println("Você recebeu uma " + newWeapon.getName() + ", que possui: " + newWeapon.getAttack() + " de ataque.");
        if (newWeapon.getAttack() > player.getWeapon().getAttack()) {
            System.out.println();
            System.out.println("Por ser mais forte que a sua " + player.getWeapon().getName() + " a " + newWeapon.getName() + " será equipada.");
            System.out.println();
            loading.loading(2000);
            updateEquipment.updateWeapon(newWeapon, player);
        } else {
            System.out.println("Sua arma é mais forte do que a arma recebida, portanto não será equipada");
        }
    }
}
