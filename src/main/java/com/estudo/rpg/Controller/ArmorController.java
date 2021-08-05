package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Armor;
import com.estudo.rpg.Repository.ArmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/armor")
public class ArmorController {

    @Autowired
    ArmorRepository armorRepository;

    @GetMapping
    public List<Armor> getAllArmorsOrASpecific(String rarity){
        if (rarity == null) {
            List<Armor> listArmor = armorRepository.findAll();
            System.out.println("Todos as armaduras foram encontrados");
            return listArmor;
        }
        List<Armor> listArmor = armorRepository.findByRarity(rarity);
        System.out.println("Todos as armaduras da raridade " + rarity + " foram encontradas!");
        return listArmor;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Armor> getArmorById(@PathVariable Long id) {
        Optional<Armor> armor = armorRepository.findById(id);
        if (armor.isPresent()) {
            System.out.println("Armadura do Id: " + id + " encontrado!");
            return ResponseEntity.ok(armor.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Armor> insertArmor(@RequestBody Armor armor, UriComponentsBuilder uriBuilder){
        armorRepository.save(armor);

        URI uri = uriBuilder.path("/armor/{id}").buildAndExpand(armor.getId()).toUri();
        System.out.println("Armadura adicionada com sucesso");
        return ResponseEntity.created(uri).body(armor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArmor(@PathVariable Long id){
        armorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
