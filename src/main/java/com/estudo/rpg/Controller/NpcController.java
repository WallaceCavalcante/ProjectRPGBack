package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Npc;
import com.estudo.rpg.Repository.NpcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/npc")
public class NpcController {

    @Autowired
    NpcRepository npcRepository;

    @GetMapping
    public List<Npc> getAllNpcsOrFiltered(String job){
        if (job == null) {
            List<Npc> listNpc = npcRepository.findAll();
            System.out.println("Todos os npcs foram encontrados");
            return listNpc;
        }
        List<Npc> listNpc = npcRepository.findByJob(job);
        System.out.println("Todos os npcs de job " + job + " foram encontrados!");
        return listNpc;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Npc> getNpcById(@PathVariable Long id) {
        Optional<Npc> npc = npcRepository.findById(id);
        if (npc.isPresent()) {
            System.out.println("Npc do Id: " + id + " encontrado!");
            return ResponseEntity.ok(npc.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Npc> insertNpc(@RequestBody Npc npc, UriComponentsBuilder uriBuilder){
        npcRepository.save(npc);

        URI uri = uriBuilder.path("/npc/{id}").buildAndExpand(npc.getId()).toUri();
        System.out.println("Npc adicionado com sucesso");
        return ResponseEntity.created(uri).body(npc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNpc(@PathVariable Long id){
        npcRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
