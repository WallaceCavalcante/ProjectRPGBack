package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Player;
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
@RequestMapping("/marketplace")
public class MarketplaceController {

    @Autowired
    PlayerRepository playerRepository;

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

    @PutMapping("/transfer/weapon/{weaponId}/{buyerId}")
    @Transactional
    public ResponseEntity<Player> removePlayerWeapon(@PathVariable Long weaponId, @PathVariable Long buyerId) {
        Optional<Weapon> optionalWeapon = weaponRepository.findById(weaponId);
        Optional<Player> optionalBuyer = playerRepository.findById(buyerId);
        if (optionalBuyer.isPresent() && optionalWeapon.isPresent()) {
            Optional<Player> optionalSeller = playerRepository.findById(optionalWeapon.get().getOwner());
            Player seller = optionalSeller.get();
            Player buyer = optionalBuyer.get();
            Weapon weapon = optionalWeapon.get();
            seller.getInventory().removeWeapon(weapon.getId());
            seller.setCoins(seller.getCoins() + weapon.getPrice());
            buyer.getInventory().addWeapon(weapon);
            buyer.setCoins(buyer.getCoins() - weapon.getPrice());
            weapon.setPrice(0.0);
            weapon.setOwner(buyer.getId());
            weapon.setSelling(false);
            playerRepository.save(seller);
            playerRepository.save(buyer);
            System.out.println("Arma comprada com sucesso pelo player: " + buyer.getNickname());
            return ResponseEntity.ok(buyer);
        }
        return ResponseEntity.badRequest().build();
    }
}
