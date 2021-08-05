package com.estudo.rpg.Functions;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Interactor;
import com.estudo.rpg.Repository.ArmorRepository;
import com.estudo.rpg.Repository.PlayerRepository;
import com.estudo.rpg.Repository.QuestRepository;
import com.estudo.rpg.Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SignUp {

    @Autowired
    WeaponRepository weaponRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ArmorRepository armorRepository;
    @Autowired
    QuestRepository questRepository;

    Rewards rewards;
    Interactor interactor;

    public SignUp(WeaponRepository weaponRepository, PlayerRepository playerRepository, ArmorRepository armorRepository,
                  QuestRepository questRepository, Rewards rewards, @Lazy Interactor interactor) {
        this.weaponRepository = weaponRepository;
        this.playerRepository = playerRepository;
        this.armorRepository = armorRepository;
        this.questRepository = questRepository;
        this.rewards = rewards;
        this.interactor = interactor;
    }

    public void registerPlayer(String nickname, String classe, String gender, double hp) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setClasse(classe);
        player.setGender(gender);
        player.setLevel(1);
        player.setHp(hp);
        player.setWeapon(weaponRepository.getOne(rewards.basicWeaponReward(classe)));
        player.setArmor(armorRepository.getOne(2L));
        player.setQuests(interactor.castQuest(questRepository.getOne(1L)));
        player.setXp(0);
        playerRepository.save(player);
    }
}
