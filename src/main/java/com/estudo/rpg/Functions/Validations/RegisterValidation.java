package com.estudo.rpg.Functions.Validations;

import com.estudo.rpg.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class RegisterValidation {

    @Autowired
    PlayerRepository playerRepository;

    Scanner sc;

    public RegisterValidation(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.sc = new Scanner(System.in);
    }

    public String validateNickName(String nickname) {
        while(playerRepository.findByNickname(nickname) != null){
            System.out.println("Já existe um personagem com esse nickname, por favor, digite outro");
            nickname = sc.nextLine();
        }
        System.out.println();
        return nickname;
    }


    public String validateClass(String classe) {
        while (!classe.equals("Assassino") && !classe.equals("Guerreiro") && !classe.equals("Arqueiro") && !classe.equals("Mago") && !classe.equals("Pistoleiro") && !classe.equals("Viking") && !classe.equals("Necromante")) {
            System.out.println("CLASSE INVÁLIDA, POR FAVOR, ESCOLHA UMA DAS CLASSES EXISTENTES");
            System.out.println();
            System.out.println("As classes existentes são: ");
            System.out.println("Assassino, Guerreiro, Arqueiro, Pistoleiro, Viking, Mago e Necromante");
            System.out.println();
            classe = sc.nextLine();
        }
        return classe;
    }

    public String validateGender(String gender) {
        while (!gender.equals("1") && !gender.equals("2")) {
            System.out.println("Numero digitado inválido, por favor, digite 1 para Masculino ou 2 para Feminino");
            gender = sc.nextLine();
        }
        if (gender.equals("1")) return "Masculino";
        else return "Feminino";
    }
}
