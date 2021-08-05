package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Monster;
import com.estudo.rpg.Repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monster")
public class MonsterController {

    @Autowired
    MonsterRepository monsterRepository;

    @GetMapping
    public List<Monster> getAllMonstersOrASpecific(String race){
//        if (race == null) {
            List<Monster> listMonster = monsterRepository.findAll();
            System.out.println("Todos os monstros foram encontrados!");
            return listMonster;
        }
//        List<Monster> listMonster = monsterRepository.findMonstersByRace(race);
//        System.out.println("Todos os monstros da raça " + race + " foram encontrados!");
//        return listMonster;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Monster> getMonsterById(@PathVariable Long id) {
        Optional<Monster> monster = monsterRepository.findById(id);
        if (monster.isPresent()) {
            System.out.println("Monstro do Id: " + id + " encontrado!");
            return ResponseEntity.ok(monster.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Monster> insertMonster(@RequestBody Monster monster, UriComponentsBuilder uriBuilder){
        monsterRepository.save(monster);

        URI uri = uriBuilder.path("/monster/{id}").buildAndExpand(monster.getId()).toUri();
        System.out.println("Monstro adicionado com sucesso");
        return ResponseEntity.created(uri).body(monster);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMonster(@PathVariable Long id){
        monsterRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
