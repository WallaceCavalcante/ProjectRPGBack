package com.estudo.rpg.Entity.Update;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Repository.PlayerRepository;

public class LevelUp {

    int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Player levelUpPlayer(Long id, PlayerRepository playerRepository){
        Player player = playerRepository.getOne(id);
        level = player.getLevel() + 1;
        player.setLevel(level);
        playerRepository.save(player);
        return player;
    }
}
