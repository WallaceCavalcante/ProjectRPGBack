package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.*;
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
import java.util.ArrayList;
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
    LevelUp levelUp = new LevelUp();

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
        player.setArmor(armorRepository.getOne(rewards.basicArmorReward()));
        player.setLevel(1);
        player.setCoins(500.0);
        player.setInventory(new Inventory());
        player.getInventory().setWeapons(new ArrayList<>());
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

    @PutMapping("/xpUpdate/{id}/{xpRecebida}")
    @Transactional
    public ResponseEntity<Player> levelUp(@PathVariable Long id, @PathVariable int xpRecebida) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        Player player = new Player();
        if (optionalPlayer.isPresent()) {
            int xpToLevelUp = optionalPlayer.get().getLevel() * 10;
            optionalPlayer.get().setXp(xpRecebida);
            playerRepository.save(optionalPlayer.get());
            while(optionalPlayer.get().getXp() > xpToLevelUp){
                optionalPlayer.get().setXp(optionalPlayer.get().getXp() - xpToLevelUp);
                player = levelUp.levelUpPlayer(optionalPlayer.get());
                playerRepository.save(player);
                System.out.println("Player: " + player.getNickname() + " Subiu de Nivel!");
            }
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/levelUp/{id}")
    @Transactional
    public ResponseEntity<Player> levelUp(@PathVariable Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = levelUp.levelUpPlayer(optionalPlayer.get());
            playerRepository.save(player);
            System.out.println("Player: " + player.getNickname() + " Subiu de Nivel!");
            return ResponseEntity.ok(player);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/changeWeapon/{weaponId}/{playerId}")
    @Transactional
    public ResponseEntity<Player> changeWeapon(@PathVariable Long weaponId, @PathVariable Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        Optional<Weapon> weapon = weaponRepository.findById(weaponId);
        if (player.isPresent() && weapon.isPresent()) {
            player.get().setWeapon(weapon.get());
            playerRepository.save(player.get());
            System.out.println("Foi trocado a arma do Player: " + player.get().getNickname() + "!");
            return ResponseEntity.ok(player.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/addWeaponToInventory/{weaponId}/{playerId}")
    @Transactional
    public ResponseEntity<Player> addWeaponToInventory(@PathVariable Long weaponId, @PathVariable Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        Optional<Weapon> weapon = weaponRepository.findById(weaponId);
        if (player.isPresent() && weapon.isPresent()) {
            player.get().getInventory().getWeapons().add(weapon.get());
            playerRepository.save(player.get());
            System.out.println("Adicionado a arma ao inventario do Player: " + player.get().getNickname() + "!");
            return ResponseEntity.ok(player.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/changeArmor/{armorId}/{playerId}")
    @Transactional
    public ResponseEntity<Player> changeArmor(@PathVariable Long armorId, @PathVariable Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        Optional<Armor> armor = armorRepository.findById(armorId);
        if (player.isPresent() && armor.isPresent()) {
            player.get().setArmor(armor.get());
            playerRepository.save(player.get());
            System.out.println("Foi trocado a armadura do Player: " + player.get().getNickname() + "!");
            return ResponseEntity.ok(player.get());
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
}
