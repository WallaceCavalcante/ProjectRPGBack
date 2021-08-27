package com.estudo.rpg.Controller;

import com.estudo.rpg.Entity.BattleResults;
import com.estudo.rpg.Entity.Boss;
import com.estudo.rpg.Entity.Monster;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Update.LevelUp;
import com.estudo.rpg.Functions.Calculate;
import com.estudo.rpg.Functions.Rewards;
import com.estudo.rpg.Functions.Validations.BattlesValidation;
import com.estudo.rpg.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/battle")
public class BattleController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    BossRepository bossRepository;

    Calculate calculate = new Calculate();
    BattlesValidation battlesValidation = new BattlesValidation(calculate);

    @GetMapping("/pvp/{opponentId}/{playerId}")
    public boolean pvpBattle(@PathVariable Long opponentId, @PathVariable Long playerId) {
        Optional<Player> opponent = playerRepository.findById(opponentId);
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent() && opponent.isPresent()) {
            System.out.println(battlesValidation.validateKillOpponentNoLoading(player.get(), opponent.get()));
            return battlesValidation.validateKillOpponentNoLoading(player.get(), opponent.get());
        }
        return false;
    }

    @GetMapping("/roll/pvp/{opponentId}/{playerId}")
    public BattleResults pvpRollBattle(@PathVariable Long opponentId, @PathVariable Long playerId) {
        Optional<Player> opponent = playerRepository.findById(opponentId);
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent() && opponent.isPresent()) {
            System.out.println(battlesValidation.validateKillOpponentRandomValue(player.get(), opponent.get()).isWinner());
            return battlesValidation.validateKillOpponentRandomValue(player.get(), opponent.get());
        }
        return null;
    }

    @GetMapping("/monster/{monsterId}/{playerId}")
    public boolean monsterBattle(@PathVariable Long monsterId, @PathVariable Long playerId) {
        Optional<Monster> monster = monsterRepository.findById(monsterId);
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent() && monster.isPresent()) {
            return battlesValidation.validateKillMonsterNoLoading(player.get(), monster.get());
        }
        return false;
    }

    @GetMapping("/roll/monster/{monsterId}/{playerId}")
    public boolean monsterRollBattle(@PathVariable Long monsterId, @PathVariable Long playerId) {
        Optional<Monster> monster = monsterRepository.findById(monsterId);
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent() && monster.isPresent()) {
            return battlesValidation.validateKillMonsterRandomValue(player.get(), monster.get());
        }
        return false;
    }

    @GetMapping("/boss/{bossId}/{playerId}")
    public boolean bossBattle(@PathVariable Long bossId, @PathVariable Long playerId) {
        Optional<Boss> boss = bossRepository.findById(bossId);
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent() && boss.isPresent()) {
            System.out.println(battlesValidation.validateKillBossNoLoading(player.get(), boss.get()));
            return battlesValidation.validateKillBossNoLoading(player.get(), boss.get());
        }
        return false;
    }

    @GetMapping("/roll/boss/{bossId}/{playerId}")
    public boolean bossRollBattle(@PathVariable Long bossId, @PathVariable Long playerId) {
        Optional<Boss> boss = bossRepository.findById(bossId);
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent() && boss.isPresent()) {
            return battlesValidation.validateKillBossRandomValue(player.get(), boss.get());
        }
        return false;
    }
}
