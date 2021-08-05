package com.estudo.rpg.Functions;

import com.estudo.rpg.Entity.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class Print {

    Loading loading;

    public Print(Loading loading) {
        this.loading = loading;
    }

    public void printAllPlayers(List<Player> playerList) {
        System.out.println("Esses são todos os players encontrados: ");
        System.out.println();
        for (Player player : playerList) {
            System.out.println(player.getId() + " " + player.getNickname() + "  |  Level: " + player.getLevel() + "  |  Arma: " +
                    player.getWeapon().getName() + " - Ataque: " + player.getWeapon().getAttack() + "  |  Armadura: " +
                    player.getArmor().getName() + " - Defesa: " + player.getArmor().getDefense());
        }
        System.out.println();
    }

    public void printAllPlayersRanking(List<Player> playerList) {
        int position = 1;
        System.out.println();
        for (Player player : playerList) {
            System.out.println(position + "° -  " + player.getNickname() + "  |  Classe: " + player.getClasse() + "  |  Level: " + player.getLevel());
            position++;
        }
        System.out.println();
    }

    public void printAllMonsters(List<Monster> monsterList) {
        for (Monster monster : monsterList) System.out.println(monster.getId() + " " + monster.getName());
    }

    public void printAllBosses(List<Boss> bossList) {
        for (Boss boss : bossList) System.out.println(boss.getId() + " " + boss.getName());
    }

    public void printAllWeapons(List<Weapon> weaponList) {
        System.out.println();
        System.out.println("Essas são as armas existentes no momento: ");
        for (Weapon weapon : weaponList)
            System.out.println(weapon.getId() + " " + weapon.getName() + " | Ataque: " + weapon.getAttack());
    }

    public void printAllArmors(List<Armor> armorList) {
        System.out.println();
        System.out.println("Essas são as armaduras existentes no momento: ");
        for (Armor armor : armorList)
            System.out.println(armor.getId() + " " + armor.getName() + " | Defesa: " + armor.getDefense());
    }

    public void printAllPlayerQuestsInOrder(Player player) {
        Collections.sort(player.getQuests());
        for (Quest quest : player.getQuests()) {
            if (!player.getQuest(player.getQuests(), (int) (quest.getId() -1)).isCompleted()) {
                if (player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getMonster() == null) {
                    System.out.println(quest.getId() + " " +
                            player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getName() + "   |   Vocẽ precisa matar: " + player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getTarget() + " " +
                            player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getBoss().getName() + " para completar a quest!");
                } else {
                    System.out.println(quest.getId() + " " +
                            player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getName() + "   |   Vocẽ precisa matar: " + player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getTarget() + " " +
                            player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getMonster().getName() + "'s para completar a quest!");
                }
            } else {
                System.out.println(player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getId() + " " + player.getQuest(player.getQuests(), (int) (quest.getId() -1)).getName() + "   |   Completada!!!");
            }
        }
        System.out.println();
    }

    public void printAllQuestsInOrder(List<Quest> questList) {
        Collections.sort(questList);
        for (Quest quest : questList) {
            if (!quest.isCompleted()) {
                if (quest.getMonster() == null) {
                    System.out.println(quest.getId() + " " +
                            quest.getName() + "   |   Vocẽ precisa matar: " + quest.getTarget() + " " +
                            quest.getBoss().getName() + " para completar a quest!");
                } else {
                    System.out.println(quest.getId() + " " +
                            quest.getName() + "   |   Vocẽ precisa matar: " + quest.getTarget() + " " +
                            quest.getMonster().getName() + "'s para completar a quest!");
                }
            } else {
                System.out.println(quest.getId() + " " + quest.getName() + "   |   Completada!!!");
            }
        }
        System.out.println();
    }

    public void printInfoPlayer(Player player, int xpToLevelUp) {
        System.out.println("Nickname: " + player.getNickname());
        System.out.println("Classe: " + player.getClasse());
        System.out.println("Genero: " + player.getGender());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Hp: " + player.getHp());
        System.out.println("Arma: " + player.getWeapon().getName() + " | Ataque: " + player.getWeapon().getAttack());
        System.out.println("Armadura: " + player.getArmor().getName() + " | Defesa: " + player.getArmor().getDefense());
        System.out.println("Xp atual: " + player.getXp() + " | Xp necessário para subir de Level: " + xpToLevelUp);
        System.out.println();
    }

    public void printLevelUp() {
        System.out.println();
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||| Level Up!!!! ||||||||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println();
    }

    public void printCompletedQuest() {
        System.out.println();
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||| Quest Completada!!!! ||||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println();
    }

    public void printCity() {
        System.out.println();
        System.out.println("Over World");
        System.out.println("Monsters Kingdom");
        System.out.println("Never Island");
        System.out.println("Feather");
        System.out.println("Giants City");
        System.out.println("Open PvP");
    }

    public void printNotAllowed(){
        System.out.println("NOT ALLOWED");
        loading.loading(1000);
        System.out.println("NOT ALLOWED");
        loading.loading(800);
        System.out.println("NOT ALLOWED");
        loading.loading(600);
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        loading.loading(500);
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        loading.loading(200);
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        System.out.println("NOT ALLOWED");
        System.out.println();
        loading.loading(1500);
    }

}
