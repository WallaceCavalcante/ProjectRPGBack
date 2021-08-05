package com.estudo.rpg.Functions.Comparisons;

import com.estudo.rpg.Entity.Boss;
import com.estudo.rpg.Entity.Monster;
import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Quest;
import com.estudo.rpg.Functions.Print;
import com.estudo.rpg.Repository.PlayerRepository;
import com.estudo.rpg.Repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestComparison {

    @Autowired
    QuestRepository questRepository;

    @Autowired
    PlayerRepository playerRepository;

    RewardsComparison rewardsComparison;
    Print print;

    public QuestComparison(QuestRepository questRepository, RewardsComparison rewardsComparison, Print print, PlayerRepository playerRepository) {
        this.questRepository = questRepository;
        this.rewardsComparison = rewardsComparison;
        this.print = print;
        this.playerRepository = playerRepository;
    }

    public void compareQuestAndVerifyIsCompleted(List<Quest> questList, Monster monster, Boss boss, Player player) {
        for (Quest quest : questList) {
            if (quest.getBoss() == null && monster != null) {
                if (quest.getMonster().getName().equals(monster.getName()) && !player.getQuest(player.getQuests(), (int) (quest.getId() -1)).isCompleted()) {
                    player.getQuest(player.getQuests(), (int) (quest.getId() -1)).setTarget(player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getTarget() - 1);
                    playerRepository.save(player);
                    if (player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getTarget() == 0) {
                        player.getQuest(player.getQuests(), (int) (quest.getId() -1)).setCompleted(true);
                        playerRepository.save(player);
                        print.printCompletedQuest();
                        rewardsComparison.compareMonsterQuestForReward(quest, player);
                    }
                }
            } else if (quest.getMonster() == null && boss != null) {
                if (quest.getBoss().getName().equals(boss.getName()) && !quest.isCompleted()) {
                    quest.setTarget(quest.getTarget() - 1);
                    if (quest.getTarget() == 0) {
                        quest.setCompleted(true);
                        questRepository.save(quest);
                        print.printCompletedQuest();
                        rewardsComparison.compareBossQuestForReward(quest, player);
                    }
                }
            }
        }
    }
}
