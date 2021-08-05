package com.estudo.rpg.Functions;

import com.estudo.rpg.Entity.*;
import com.estudo.rpg.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAll {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    BossRepository bossRepository;

    @Autowired
    QuestRepository questRepository;

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    ArmorRepository armorRepository;

    public FindAll(PlayerRepository playerRepository, MonsterRepository monsterRepository, BossRepository bossRepository, QuestRepository questRepository, WeaponRepository weaponRepository, ArmorRepository armorRepository) {
        this.playerRepository = playerRepository;
        this.monsterRepository = monsterRepository;
        this.bossRepository = bossRepository;
        this.questRepository = questRepository;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
    }

    public List<Player> receiveOpponent(Player player) {
        List<Player> playerList = playerRepository.findAllOpponents(player.getNickname());
        return playerList;
    }

    public List<Monster> receiveMonsters() {
        List<Monster> monsterList = monsterRepository.findAll();
        return monsterList;
    }

    public List<Boss> receiveBosses() {
        List<Boss> monsterList = bossRepository.findAll();
        return monsterList;
    }

    public List<Weapon> receiveWeapons() {
        List<Weapon> weaponList = weaponRepository.findAll();
        return weaponList;
    }

    public List<Armor> receiveArmors() {
        List<Armor> armorList = armorRepository.findAll();
        return armorList;
    }

    public List<Quest> receiveQuests() {
        List<Quest> questList = questRepository.findAll();
        return questList;
    }
}
