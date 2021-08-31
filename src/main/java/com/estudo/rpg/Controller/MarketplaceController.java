package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Repository.ArmorRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marketplace")
public class MarketplaceController {

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    ArmorRepository armorRepository;

    @GetMapping("/weapons")
    public List<Weapon> getAllWeaponsToSell(){
        List<Weapon> listWeapon = weaponRepository.findWeaponsToSell();
        System.out.println("Todos as armas para vender foram encontradas!");
        return listWeapon;
    }
}
