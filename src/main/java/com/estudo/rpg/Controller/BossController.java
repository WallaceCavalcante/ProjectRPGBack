package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Boss;
import com.estudo.rpg.Repository.BossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boss")
public class BossController {

    @Autowired
    BossRepository bossRepository;

    @GetMapping
    public List<Boss> getAllBossesOrASpecific(String race){
//        if (race == null) {
            List<Boss> listBoss = bossRepository.findAll();
            System.out.println("Todos os bosses foram encontrados!");
            return listBoss;
        }
//        List<Boss> listBoss = bossRepository.findBossesByRace(race);
//        System.out.println("Todos os chefões da raça " + race + " foram encontrados!");
//        return listBoss;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Boss> getBossById(@PathVariable Long id) {
        Optional<Boss> boss = bossRepository.findById(id);
        if (boss.isPresent()) {
            System.out.println("Boss do Id: " + id + " encontrado!");
            return ResponseEntity.ok(boss.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Boss> insertBoss(@RequestBody Boss boss, UriComponentsBuilder uriBuilder){
        bossRepository.save(boss);

        URI uri = uriBuilder.path("/boss/{id}").buildAndExpand(boss.getId()).toUri();
        System.out.println("Boss adicionado com sucesso");
        return ResponseEntity.created(uri).body(boss);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoss(@PathVariable Long id){
        bossRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
