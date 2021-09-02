package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Update.NewWeaponForPlayer;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Functions.Rewards;
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

    Rewards rewards = new Rewards();
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
            Weapon weapon = optionalWeapon.get();
            weapon.setOwner(player.getId());
            player.getInventory().addWeapon(weapon);
            playerRepository.save(player);
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/addWeapon/gacha/{weaponId}/{playerId}")
    @Transactional
    public ResponseEntity<Player> addGachaWeaponToPlayer(@PathVariable Long playerId, @PathVariable Long weaponId) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        Optional<Weapon> optionalWeapon = weaponRepository.findById(weaponId);
        if (optionalPlayer.isPresent() && optionalWeapon.isPresent()) {
            Player player = optionalPlayer.get();
            Weapon weapon = optionalWeapon.get();
            weapon.setOwner(player.getId());
            player.getInventory().addWeapon(weapon);
            player.setCoins(player.getCoins() - 100);
            playerRepository.save(player);
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/createWeapon/addWeapon/{playerId}")
    @Transactional
    public ResponseEntity<Player> addWeaponToNewPlayer(@PathVariable Long playerId) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            Weapon weapon = new Weapon();
            Weapon newWeapon = weapon.setNewWeapon(weaponRepository.getOne(rewards.basicWeaponReward(player.getClasse())));
            newWeapon.setOwner(player.getId());
            weaponRepository.save(newWeapon);
            player.getInventory().addWeapon(newWeapon);
            playerRepository.save(player);
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

}
