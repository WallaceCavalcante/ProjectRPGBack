package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Functions.Rewards;
import com.estudo.rpg.Functions.Validations.WeaponValidation;
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

    Rewards rewards = new Rewards();

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

    @PostMapping("/add")
    public ResponseEntity<Weapon> insertWeapon(@RequestBody Weapon weapon, UriComponentsBuilder uriBuilder){
        weapon.setPrice(0.0);
        weaponRepository.save(weapon);

        URI uri = uriBuilder.path("/weapon/{id}").buildAndExpand(weapon.getId()).toUri();
        System.out.println("Arma adicionada com sucesso");
        return ResponseEntity.created(uri).body(weapon);
    }

    @PostMapping("/gacha/add/{weaponLevel}/{weaponType}")
    public ResponseEntity<Weapon> insertGachaWeapon(@PathVariable int weaponLevel, @PathVariable String weaponType, UriComponentsBuilder uriBuilder){
        Weapon weapon = new Weapon();
        Weapon newWeapon = weapon.setNewWeapon(weaponRepository.getOne(rewards.validationGachaWeaponReward(weaponLevel, weaponType)));
        newWeapon.setPrice(0.0);

        weaponRepository.save(newWeapon);

        URI uri = uriBuilder.path("/weapon/{id}").buildAndExpand(newWeapon.getId()).toUri();
        System.out.println("Arma adicionada com sucesso");
        return ResponseEntity.created(uri).body(newWeapon);
    }

    @PutMapping("/sell/{weaponId}/{value}")
    public ResponseEntity<Weapon> sellWeapon(@PathVariable Long weaponId, @PathVariable Double value){
        Optional<Weapon> optionalWeapon = weaponRepository.findById(weaponId);
        if(optionalWeapon.isPresent()) {
            Weapon weapon = optionalWeapon.get();
            weapon.setSelling(true);
            weapon.setPrice(value);
            weaponRepository.save(weapon);
            return ResponseEntity.ok(weapon);
        }
        System.out.println("A arma foi colocada à venda com sucesso!");
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/sell/changePrice/{weaponId}/{newPrice}")
    public ResponseEntity<Weapon> changeWeaponPrice(@PathVariable Long weaponId, @PathVariable Double newPrice){
        Optional<Weapon> optionalWeapon = weaponRepository.findById(weaponId);
        if(optionalWeapon.isPresent()) {
            Weapon weapon = optionalWeapon.get();
            weapon.setPrice(newPrice);
            weaponRepository.save(weapon);
            return ResponseEntity.ok(weapon);
        }
        System.out.println("Foi alterado o valor da arma com sucesso!");
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/cancelSelling/{weaponId}")
    public ResponseEntity<Weapon> cancelSellingWeapon(@PathVariable Long weaponId){
        Optional<Weapon> optionalWeapon = weaponRepository.findById(weaponId);
        if(optionalWeapon.isPresent()) {
            Weapon weapon = optionalWeapon.get();
            weapon.setSelling(false);
            weapon.setPrice(0.0);
            weaponRepository.save(weapon);
            return ResponseEntity.ok(weapon);
        }
        System.out.println("A arma foi tirada da venda com sucesso!");
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWeapon(@PathVariable Long id){
        weaponRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
