package com.estudo.rpg;

import com.estudo.rpg.Entity.*;
import com.estudo.rpg.Functions.*;
import com.estudo.rpg.Functions.Comparisons.QuestComparison;
import com.estudo.rpg.Functions.Updates.UpdateEquipment;
import com.estudo.rpg.Functions.Updates.UpdatePlayer;
import com.estudo.rpg.Functions.Validations.*;
import com.estudo.rpg.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;

@Component
public class Interactor implements CommandLineRunner {

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

    Scanner sc;
    Player player;
    Player opponent;
    Monster monster;
    Boss boss;
    DecimalFormat decimalFormat;
    List<Quest> questList;
    int xpToLevelUp;
    Print print;
    Loading loading;
    PlayerValidation playerValidation;
    Dialogue dialogue;
    Calculate calculate;
    Rewards rewards;
    FindAll findAll;
    MonsterValidation monsterValidation;
    BossValidation bossValidation;
    ArmorValidation armorValidation;
    WeaponValidation weaponValidation;
    QuestValidation questValidation;
    RegisterValidation registerValidation;
    BattlesValidation battlesValidation;
    QuestComparison questComparison;
    SignUp signUp;
    UpdateEquipment updateEquipment;
    UpdatePlayer updatePlayer;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public int getXpToLevelUp() {
        return xpToLevelUp;
    }

    public void setXpToLevelUp(int xpToLevelUp) {
        this.xpToLevelUp = xpToLevelUp;
    }

    public Interactor(PlayerRepository playerRepository, MonsterRepository monsterRepository,
                      BossRepository bossRepository, QuestRepository questRepository, WeaponRepository weaponRepository,
                      ArmorRepository armorRepository, List<Quest> questList, Print print, Loading loading,
                      PlayerValidation playerValidation, Dialogue dialogue, Calculate calculate, Rewards rewards,
                      FindAll findAll, MonsterValidation monsterValidation, BossValidation bossValidation,
                      ArmorValidation armorValidation, WeaponValidation weaponValidation, QuestValidation questValidation,
                      RegisterValidation registerValidation, BattlesValidation battlesValidation, QuestComparison questComparison,
                      SignUp signUp, UpdateEquipment updateEquipment, UpdatePlayer updatePlayer) {
        this.playerRepository = playerRepository;
        this.monsterRepository = monsterRepository;
        this.bossRepository = bossRepository;
        this.questRepository = questRepository;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.player = new Player();
        this.opponent = new Player();
        this.monster = new Monster();
        this.boss = new Boss();
        this.sc = new Scanner(System.in);
        this.decimalFormat = new DecimalFormat();
        this.questList = questList;
        this.xpToLevelUp = 999;
        this.print = print;
        this.loading = loading;
        this.playerValidation = playerValidation;
        this.dialogue = dialogue;
        this.calculate = calculate;
        this.rewards = rewards;
        this.findAll = findAll;
        this.monsterValidation = monsterValidation;
        this.bossValidation = bossValidation;
        this.armorValidation = armorValidation;
        this.weaponValidation = weaponValidation;
        this.questValidation = questValidation;
        this.registerValidation = registerValidation;
        this.battlesValidation = battlesValidation;
        this.questComparison = questComparison;
        this.signUp = signUp;
        this.updateEquipment = updateEquipment;
        this.updatePlayer = updatePlayer;
    }

    //////////////////////////////////////////// Run After SpringBoot ///////////////////////////////////////////////////////

    @Override
    public void run(String... args) throws Exception {
        dialogue.welcomeDialogue();
        dialogue.newHereDialogue();
    }

/////////////////////////////////////////////////// Main ////////////////////////////////////////////////////////////////

    public void mainInteractor(String escolha) {
        switch (escolha) {
            case "1":
                List<Monster> monsterList = findAll.receiveMonsters();
                System.out.println("Qual dos monstro abaixo você deseja caçar?");
                print.printAllMonsters(monsterList);
                String monstroEscolhido = sc.nextLine();
                monster = monsterValidation.validateMonsterExists(monstroEscolhido);
                if (battlesValidation.validateKillMonster(player, monster)) {
                    System.out.println("Você venceu a batalha contra um " + monster.getName());
                    System.out.println("Você recebeu: " + monster.getXpWhenKilled() + " de XP");
                    questComparison.compareQuestAndVerifyIsCompleted(player.getQuests(), monster, boss, player);
                    updatePlayer.updatePlayerXpMonster(player, monster);
                    System.out.println("Deseja tentar matar outro monstro? Caso NÃO digite 1, caso SIM aperte enter ou digite qualquer coisa");
                } else {
                    System.out.println("Você Morreu para o monstro " + monster.getName());
                    System.out.println("Deseja tentar matar outro monstro?? Caso NÃO digite 1, caso SIM aperte enter ou digite qualquer coisa");
                }
                if (sc.nextLine().equals("1")) dialogue.playerDecisionsAfterLoginDialogue();
                else mainInteractor("1");
                break;

            case "2":
                List<Boss> bossList = findAll.receiveBosses();
                System.out.println("Qual dos chefões você deseja caçar?");
                print.printAllBosses(bossList);
                String bossEscolhido = sc.nextLine();
                boss = bossValidation.validateBossExists(bossEscolhido);
                if (battlesValidation.validateKillBoss(player, boss)) {
                    System.out.println("Você venceu a batalha contra o chefão " + boss.getName());
                    System.out.println("Você recebeu: " + boss.getXpWhenKilled() + " de XP");
                    questComparison.compareQuestAndVerifyIsCompleted(player.getQuests(), monster, boss, player);
                    updatePlayer.updatePlayerXpBoss(player, boss);
                    System.out.println("Deseja tentar matar outro chefão? Caso NÃO digite 1, caso SIM aperte enter ou digite qualquer coisa");
                } else {
                    System.out.println("Você Morreu para o chefão " + boss.getName());
                    System.out.println("Deseja tentar matar outro chefão?? Caso NÃO digite 1, caso SIM aperte enter ou digite qualquer coisa");
                }
                if (sc.nextLine().equals("1")) dialogue.playerDecisionsAfterLoginDialogue();
                else mainInteractor("2");
                break;

            case "3":
                questList = findAll.receiveQuests();
                System.out.println("Qual missão você gostaria de pegar?");
                print.printAllQuestsInOrder(questList);
                String questEscolhida = sc.nextLine();
                int index = questValidation.validateQuestExists(questEscolhida);
                List<Quest> quests = new ArrayList<>();
                quests.addAll(player.getQuests());
                quests.add(questList.get(index - 1));
                player.setQuests(quests);
                playerRepository.save(player);
                System.out.println("Quest " + index + " adicionada as suas Missões");
                System.out.println("Deseja pegar mais alguma missão?? Caso NÃO digite 1, caso SIM aperte enter ou digite qualquer coisa");
                if (sc.nextLine().equals("1")) dialogue.playerDecisionsAfterLoginDialogue();
                else mainInteractor("3");
                break;

            case "4":
                dialogue.changeEquipmentDialogue(player);
                System.out.println("Deseja trocar o equipamento denovo?? Caso NÃO digite 1, caso SIM aperte enter ou digite qualquer coisa");
                if (sc.nextLine().equals("1")) dialogue.playerDecisionsAfterLoginDialogue();
                else mainInteractor("4");
                break;

            case "5":
                print.printAllPlayers(findAll.receiveOpponent(player));
                System.out.println("Contra qual player você gostaria de batalhar?");
                String playerEscolhido = sc.nextLine();
                opponent = playerValidation.validateOpponentExists(playerEscolhido);
                if (battlesValidation.validateKillOpponent(player, opponent)) {
                    System.out.println("Você venceu a batalha contra o player " + opponent.getNickname());
                    int xpRecebida = opponent.getLevel() * 50;
                    System.out.println("E recebeu um total de " + xpRecebida + " de xp");
                    calculatePlayerXp(xpRecebida);
                    System.out.println("Deseja batalhar novamente? Caso NÃO digite 1, caso SIM apenas aperte enter");
                } else {
                    System.out.println("Você Morreu para o player " + opponent.getNickname());
                    System.out.println("Deseja tentar batalhar contra outro oponente?? Caso NÃO digite 1, caso SIM aperte enter ou digite qualquer coisa");
                }
                if (sc.nextLine().equals("1")) dialogue.playerDecisionsAfterLoginDialogue();
                else mainInteractor("5");
                break;

            case "6":
                System.out.println("Suas quests: ");
                print.printAllPlayerQuestsInOrder(player);
                dialogue.playerDecisionsAfterLoginDialogue();
                break;

            case "7":
                print.printInfoPlayer(player, calculate.calculateXpToLevelUp(player));
                dialogue.playerDecisionsAfterLoginDialogue();
                break;

            case "8":
                List<Player> playerList = playerRepository.findAllPlayerOrderByLevel();
                print.printAllPlayersRanking(playerList);
                dialogue.playerDecisionsAfterLoginDialogue();
                break;

            case "9":
                System.out.println("Essas são as cidades existentes: ");
                print.printCity();
                System.out.println("Qual cidade gostaria de ir?");
                String cidadeEscolhida = sc.nextLine();
                System.out.println("Viajando para " + cidadeEscolhida + ".........");
                loading.loading(3000);
                System.out.println("Bem vindo, você chegou em " + cidadeEscolhida);
                loading.loading(1000);
                dialogue.playerDecisionsAfterLoginDialogue();
                break;

            case "0":
                dialogue.newHereDialogue();
                break;

            default:
                System.out.println("Por favor, digite uma das opções existentes");
                dialogue.playerDecisionsAfterLoginDialogue();
        }
    }

    public List<Quest> castQuest(Quest quest) {
        List<Quest> questList = new ArrayList<>();
        questList.add(quest);
        return questList;
    }

    public void validateChoices(String choice){
        while (!choice.equals("0") && !choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6") && !choice.equals("7") && !choice.equals("8") && !choice.equals("9")) {
            System.out.println("Por favor, digite 0, 1, 2, 3, 4, 5, 6, 7, 8 ou 9");
            choice = sc.nextLine();
        }
    }

    public void calculatePlayerXp(int xp) {
        player.setXp(player.getXp() + xp);
        updatePlayer.updateLevelUpVerify(player);
    }
}
