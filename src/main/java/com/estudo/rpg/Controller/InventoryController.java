package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Repository.ArmorRepository;
import com.estudo.rpg.Repository.PlayerRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player/inventory")
public class InventoryController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    ArmorRepository armorRepository;

    @GetMapping("/{id}")
    public List<Weapon> getAllWeaponsOfAPlayer(@PathVariable Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(value -> value.getInventory().getWeapons()).orElse(null);
    }
}
