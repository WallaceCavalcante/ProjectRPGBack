package com.estudo.rpg.Entity.Update;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Functions.Calculate;
import com.estudo.rpg.Repository.PlayerRepository;

public class LevelUp {

    Calculate calculate = new Calculate();

    public Player levelUpPlayer(Player player){
        int level = player.getLevel() + 1;
        double hp = player.getHp() + calculate.calculateLevelUpSumPlayerHp(player.getClasse());
        player.setHp(hp);
        player.setLevel(level);
        return player;
    }
}
