package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Update.LevelUp;
import com.estudo.rpg.Entity.Update.NewQuestForPlayer;
import com.estudo.rpg.Functions.Calculate;
import com.estudo.rpg.Functions.Rewards;
import com.estudo.rpg.Functions.Validations.BattlesValidation;
import com.estudo.rpg.Repository.ArmorRepository;
import com.estudo.rpg.Repository.PlayerRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    ArmorRepository armorRepository;

    Calculate calculate = new Calculate();
    Rewards rewards = new Rewards();
    BattlesValidation battlesValidation = new BattlesValidation(calculate);

    @GetMapping
    public List<Player> getAllPlayersOrASpecific(String classe){
        if (classe == null) {
            List<Player> listPlayer = playerRepository.findAll();
            System.out.println("Todos os players foram encontrados");
            return listPlayer;
        }
        List<Player> listPlayer = playerRepository.findByClasse(classe);
        System.out.println("Todos os players que jogam com a classe " + classe + " foram encontrados!");
        return listPlayer;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            System.out.println("Player do Id: " + id + " encontrado!");
            return ResponseEntity.ok(player.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<Player> getPlayerByName(@PathVariable String nickname) {
        Player player = playerRepository.findByNickname(nickname);
        if (player != null) {
            System.out.println("Player do nickname: " + nickname + " encontrado!");
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ranking")
    public List<Player> getAllPlayersOrderByLevel(){
        List<Player> listPlayer = playerRepository.findAllPlayerOrderByLevel();
        System.out.println("Todos os players foram encontrados");
        return listPlayer;
    }

    @PostMapping("/register")
    public ResponseEntity<Player> insertPlayer(@RequestBody @Valid Player player, UriComponentsBuilder uriBuilder){
        player.setHp(calculate.calculatePlayerHp(player.getClasse()));
        player.setWeapon(weaponRepository.getOne(rewards.basicWeaponReward(player.getClasse())));
        player.setArmor(armorRepository.getOne(rewards.basicArmorReward()));
        player.setLevel(1);
        playerRepository.save(player);
        URI uri = uriBuilder.path("/player/{id}").buildAndExpand(player.getId()).toUri();
        System.out.println("Player adicionado com sucesso");
        return ResponseEntity.created(uri).body(player);
    }

    @PutMapping("/addQuest/{id}")
    @Transactional
    public ResponseEntity<Player> addQuestToPlayer(@PathVariable Long id, @RequestBody NewQuestForPlayer newQuestForPlayer) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = newQuestForPlayer.addQuest(id, playerRepository);
                playerRepository.save(player);
                return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/levelUp/{id}")
    @Transactional
    public ResponseEntity<Player> levelUp(@PathVariable Long id, LevelUp levelUp) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = levelUp.levelUpPlayer(id, playerRepository);
            playerRepository.save(player);
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Player> addQuestToPlayerInTerminal(Long id, NewQuestForPlayer newQuestForPlayer) {
        Optional<Player> optionalPlayer= playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = newQuestForPlayer.addQuest(id, playerRepository);
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlayer(@PathVariable Long id){
        playerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/battle/{opponentId}/{playerId}")
    public boolean pvpBattle(@PathVariable Long opponentId, @PathVariable Long playerId) {
        Optional<Player> opponent = playerRepository.findById(opponentId);
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent() && opponent.isPresent()) {
            System.out.println(battlesValidation.validateKillOpponentNoLoading(player.get(), opponent.get()));
            return battlesValidation.validateKillOpponentNoLoading(player.get(), opponent.get());
        }
        return false;
    }
}
