package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Quest;
import com.estudo.rpg.Repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quest")
public class QuestController {

    @Autowired
    QuestRepository questRepository;

    @GetMapping
    public List<Quest> getAllQuestsOrFiltered(String name){
        if (name == null) {
            List<Quest> listQuest = questRepository.findAll();
            System.out.println("Todos as missoes foram encontradas!");
            return listQuest;
        }
        List<Quest> listQuest = questRepository.findByMonsterName(name);
        if(listQuest.isEmpty()){
            listQuest = questRepository.findByBossName(name);
            System.out.println("Todos as missoes vinculadas ao boss " + name + " foram encontradas!");
        }
        else {
            System.out.println("Todos as missoes vinculadas ao monstro " + name + " foram encontradas!");
        }
        return listQuest;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quest> getQuestById(@PathVariable Long id) {
        Optional<Quest> quest = questRepository.findById(id);
        if (quest.isPresent()) {
            System.out.println("Quest do Id: " + id + " encontrado!");
            return ResponseEntity.ok(quest.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Quest> insertNpc(@RequestBody Quest quest, UriComponentsBuilder uriBuilder){
        questRepository.save(quest);

        URI uri = uriBuilder.path("/quest/{id}").buildAndExpand(quest.getId()).toUri();
        System.out.println("Missão adicionada com sucesso");
        return ResponseEntity.created(uri).body(quest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuest(@PathVariable Long id){
        questRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
