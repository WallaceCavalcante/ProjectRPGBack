package com.estudo.rpg.Entity.Update;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Weapon;
import com.estudo.rpg.Repository.PlayerRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewWeaponForPlayer {

    @Autowired
    WeaponRepository weaponRepository;

    Long weaponId;

    public Long getWeapon() {
        return weaponId;
    }

    public void setWeapon(Long weaponId) {
        this.weaponId = weaponId;
    }

    public Player addItens(Long playerId, Long weaponId, PlayerRepository playerRepository){
        Player player = playerRepository.getOne(playerId);
        Weapon weapon = weaponRepository.getOne(weaponId);
        List<Weapon> weaponsList = player.getInventory().getWeapons();
        weaponsList.add(weapon);
        player.getInventory().setWeapons(weaponsList);
        return player;
    }
}
