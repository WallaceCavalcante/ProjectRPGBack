package com.estudo.rpg.Entity.Update;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Quest;
import com.estudo.rpg.Repository.PlayerRepository;

import java.util.List;

public class NewQuestForPlayer {

    List<Quest> quests;

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public Player addQuest(Long id, PlayerRepository playerRepository){
        Player player = playerRepository.getOne(id);
        List<Quest> questList = player.getQuests();
        questList.addAll(quests);
        player.setQuests(questList);
        return player;
    }
}