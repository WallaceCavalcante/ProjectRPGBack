package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Update.NewWeaponForPlayer;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Repository.ArmorRepository;
import com.estudo.rpg.Repository.PlayerRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    NewWeaponForPlayer newWeaponForPlayer = new NewWeaponForPlayer();

    @GetMapping("/{id}")
    public List<Weapon> getAllWeaponsOfAPlayer(@PathVariable Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(value -> value.getInventory().getWeapons()).orElse(null);
    }

    @PutMapping("/addWeapon/{weaponId}/{playerId}")
    @Transactional
    public ResponseEntity<Player> addWeaponToPlayer(@PathVariable Long playerId, @PathVariable Long weaponId) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        Optional<Weapon> optionalWeapon = weaponRepository.findById(weaponId);
        if (optionalPlayer.isPresent() && optionalWeapon.isPresent()) {
            Player player = optionalPlayer.get();
            player.getInventory().addWeapon(optionalWeapon.get());
            playerRepository.save(player);
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }
}
