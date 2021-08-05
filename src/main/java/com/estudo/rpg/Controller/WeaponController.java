package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weapon")
public class WeaponController {

    @Autowired
    WeaponRepository weaponRepository;

    @GetMapping
    public List<Weapon> getAllWeaponsOrFilterByRarity(String rarity){
        if (rarity == null) {
            List<Weapon> listWeapon = weaponRepository.findAll();
            System.out.println("Todos as armas foram encontradas!");
            return listWeapon;
        }
        List<Weapon> listWeapon = weaponRepository.findByRarity(rarity);
        System.out.println("Todos as armas da raridade " + rarity + " foram encontradas!");
        return listWeapon;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weapon> getWeaponById(@PathVariable Long id) {
        Optional<Weapon> weapon = weaponRepository.findById(id);
        if (weapon.isPresent()) {
            System.out.println("Arma do Id: " + id + " encontrado!");
            return ResponseEntity.ok(weapon.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Weapon> insertWeapon(@RequestBody Weapon weapon, UriComponentsBuilder uriBuilder){
        weaponRepository.save(weapon);

        URI uri = uriBuilder.path("/weapon/{id}").buildAndExpand(weapon.getId()).toUri();
        System.out.println("Arma adicionada com sucesso");
        return ResponseEntity.created(uri).body(weapon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWeapon(@PathVariable Long id){
        weaponRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
