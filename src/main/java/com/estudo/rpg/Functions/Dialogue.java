package com.estudo.rpg.Functions;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Functions.Updates.UpdateEquipment;
import com.estudo.rpg.Functions.Validations.*;
import com.estudo.rpg.Interactor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Dialogue {

    Loading loading;
    Scanner sc;
    Interactor interactor;
    Print print;
    PlayerValidation playerValidation;
    Calculate calculate;
    UpdateEquipment updateEquipment;
    WeaponValidation weaponValidation;
    ArmorValidation armorValidation;
    FindAll findAll;
    RegisterValidation registerValidation;
    SignUp signUp;

    public Dialogue(Loading loading, @Lazy Interactor interactor, Print print, @Lazy PlayerValidation playerValidation, Calculate calculate,
                    UpdateEquipment updateEquipment, WeaponValidation weaponValidation, ArmorValidation armorValidation, FindAll findAll,
                    RegisterValidation registerValidation, SignUp signUp) {
        this.loading = loading;
        this.sc = new Scanner(System.in);
        this.interactor = interactor;
        this.print = print;
        this.playerValidation = playerValidation;
        this.calculate = calculate;
        this.updateEquipment = updateEquipment;
        this.weaponValidation = weaponValidation;
        this.armorValidation = armorValidation;
        this.findAll = findAll;
        this.registerValidation = registerValidation;
        this.signUp = signUp;
    }

    public void welcomeDialogue() {
        System.out.println();
        System.out.println("Iniciando jogo.....");
        System.out.println();
        loading.loading(1000);
        System.out.println("Jogo iniciado com sucesso!!");
        System.out.println();
        loading.loading(1000);
        System.out.println("Olá, seja bem vindo aventureiro!!");
        System.out.println("Espero que possa se divertir muito nesse mundo incrível");
        loading.loading(2000);
        System.out.println();
    }

    public void newHereDialogue() {
        System.out.println("Você já possui um personagem?");
        System.out.println("Caso SIM digite 1");
        System.out.println("Caso NÃO digite 2");
        System.out.println("Caso deseje sair do jogo digite 0");
        String opcao = sc.nextLine();
        while (!opcao.equals("0") && !opcao.equals("1") && !opcao.equals("2")) {
            System.out.println("Por favor, digite 0, 1 ou 2");
            opcao = sc.nextLine();
        }
        if (opcao.equals("1")) {
            System.out.println("Digite o nome do seu personagem para você se conectar");
            loading.loading(1000);
            loginDialogue(sc.nextLine());
        } else if (opcao.equals("2")) {
            System.out.println("Vamos criar uma conta nova então.");
            registerDialogue();
        } else {
            System.out.println();
            System.out.println("Fechando o jogo.......");
            System.out.println();
            loading.loading(1500);
            System.out.println("Jogo fechado! Obrigado por jogar!");
            System.out.println();
            loading.loading(1000);
            System.out.println("Enter para abrir o jogo novamente...");
            sc.nextLine();
            welcomeDialogue();
            newHereDialogue();
        }
    }

    public void registerDialogue() {
        System.out.println("Digite o nickname que deseja");
        String nickname = registerValidation.validateNickName(sc.nextLine());
        System.out.println("Agora digite a classe com qual deseja jogar");
        System.out.println("As classes existentes são: ");
        System.out.println("Assassino, Guerreiro, Arqueiro, Pistoleiro, Viking, Mago e Necromante");
        String classe = registerValidation.validateClass(sc.nextLine());
        System.out.println("Muito bem, qual será o genero do personagem?");
        System.out.println("Masculino(1) ou Feminino(2)?");
        String gender = registerValidation.validateGender(sc.nextLine());
        double hp = calculate.calculatePlayerHp(classe);
        System.out.println("Perfeito, seu personagem está sendo criado........");
        System.out.println();
        loading.loading(1500);
        signUp.registerPlayer(nickname, classe, gender, hp);
        System.out.println("Personagem criado com sucesso!!");
        loading.loading(500);
        System.out.println();
        loginDialogue(nickname);
    }

    public void loginDialogue(String nickname) {
        System.out.println("Logando......");
        System.out.println();
        loading.loading(1000);
        Player player = playerValidation.validatePlayerExists(nickname);
        interactor.setPlayer(player);
        calculate.calculateXpToLevelUp(player);
        System.out.println("Aqui estão todos os seus dados: ");
        System.out.println();
        loading.loading(500);
        interactor.setXpToLevelUp(calculate.calculateXpToLevelUp(player));
        print.printInfoPlayer(player, calculate.calculateXpToLevelUp(player));
        System.out.println("Carregando o Mundo.....");
        System.out.println();
        loading.loading(2500);
        System.out.println("Mundo carregado.");
        loading.loading(1000);
        System.out.println();
        playerDecisionsAfterLoginDialogue();
    }


    public void playerDecisionsAfterLoginDialogue() {
        interactor.setBoss(null);
        interactor.setMonster(null);
        interactor.setOpponent(null);
        System.out.println("O que gostaria de fazer?");
        interactor.mainInteractor(choicesDialogue());
    }

    public String choicesDialogue() {
        System.out.println("Digite 1 para caçar monstros");
        System.out.println("Digite 2 para caçar chefões");
        System.out.println("Digite 3 para pegar uma nova quest");
        System.out.println("Digite 4 para trocar equipamento(GM)");
        System.out.println("Digite 5 para o PvP");
        System.out.println("Digite 6 para ver suas missoes");
        System.out.println("Digite 7 para ver seus dados");
        System.out.println("Digite 8 para ver o Ranking de Players");
        System.out.println("Digite 9 para viajar para outra cidade(FAKE)");
        System.out.println("Digite 0 para deslogar de seu personagem");

        String choice = sc.nextLine();
        interactor.validateChoices(choice);
        return choice;
    }

    public void changeEquipmentDialogue(Player player) {
        if (player.getNickname().equals("GM")) {
            System.out.println("Ativado modo GM para equipar itens novos.");
        } else {
            print.printNotAllowed();
            System.out.println("Já que insiste, por ser o beta, você pode pegar os equipamentos que quiser :)");
        }
        System.out.println();
        System.out.println("Gostaria de equipar uma arma nova ou uma armadura nova?");
        System.out.println("Digite 1 para ARMA, digite 2 para ARMADURA e digite 0 para voltar para a tela de interação");
        String escolha = sc.nextLine();
        while (!escolha.equals("0") && !escolha.equals("1") && !escolha.equals("2")) {
            System.out.println("Por favor, digite 0, 1 ou 2");
            escolha = sc.nextLine();
        }
        if (escolha.equals("1")) {
            print.printAllWeapons(findAll.receiveWeapons());
            System.out.println();
            System.out.println("Agora escolha pelo numero qual arma deseja equipar");
            escolha = sc.nextLine();
            updateEquipment.updateWeapon(weaponValidation.validateWeaponExists(escolha), player);
        } else if (escolha.equals("2")) {
            print.printAllArmors(findAll.receiveArmors());
            System.out.println();
            System.out.println("Agora escolha pelo numero qual armadura deseja equipar");
            escolha = sc.nextLine();
            updateEquipment.updateArmor(armorValidation.validateArmorExists(escolha), player);
        } else {
            playerDecisionsAfterLoginDialogue();
        }
    }
}
