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
        Optional<Player> optionalOpponent = playerRepository.findById(opponentId);
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent() && optionalOpponent.isPresent()) {
            Player player = optionalPlayer.get();
            Player opponent = optionalOpponent.get();
            BattleResults battleResults = battlesValidation.validateKillOpponentRandomValue(player, opponent);
            if(battleResults.isWinner()){
                player.setXp(player.getXp() + opponent.getLevel() * 10);
                battleResults.setCoinsDropped((int)((Math.random() * ((opponent.getLevel()*20 - opponent.getLevel()*5 + 1)) + opponent.getLevel()*5)));
                player.setCoins(player.getCoins() + battleResults.getCoinsDropped());
                playerRepository.save(player);
            }
            return  battleResults;
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
    public BattleResults monsterRollBattle(@PathVariable Long monsterId, @PathVariable Long playerId) {
        Optional<Monster> optionalMonster = monsterRepository.findById(monsterId);
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent() && optionalMonster.isPresent()) {
            Player player = optionalPlayer.get();
            Monster monster = optionalMonster.get();
            BattleResults battleResults = battlesValidation.validateKillMonsterRandomValue(player, monster);
            if(battleResults.isWinner()){
                player.setXp(player.getXp() + monster.getXpWhenKilled());
                battleResults.setCoinsDropped((int)((Math.random() * ((monster.getXpWhenKilled()*10 - monster.getXpWhenKilled())*3 + 1)) + monster.getXpWhenKilled()*3));
                player.setCoins(player.getCoins() + battleResults.getCoinsDropped());
                playerRepository.save(player);
            }
            return battleResults;
        }
        return null;
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
    public BattleResults bossRollBattle(@PathVariable Long bossId, @PathVariable Long playerId) {
        Optional<Boss> optionalBoss = bossRepository.findById(bossId);
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent() && optionalBoss.isPresent()) {
            Player player = optionalPlayer.get();
            Boss boss = optionalBoss.get();
            BattleResults battleResults = battlesValidation.validateKillBossRandomValue(player, boss);
            if(battleResults.isWinner()){
                player.setXp(player.getXp() + boss.getXpWhenKilled());
                battleResults.setCoinsDropped((int) ((Math.random() * ((boss.getXpWhenKilled()*2 - boss.getXpWhenKilled()) + 1)) + boss.getXpWhenKilled()));
                player.setCoins(player.getCoins() + battleResults.getCoinsDropped());
                playerRepository.save(player);
            }
            return battleResults;
        }
        return null;
    }
}
