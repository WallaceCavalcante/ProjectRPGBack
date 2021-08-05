package com.estudo.rpg.Functions.Validations;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Functions.Dialogue;
import com.estudo.rpg.Functions.Loading;
import com.estudo.rpg.Interactor;
import com.estudo.rpg.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PlayerValidation {

    @Autowired
    PlayerRepository playerRepository;

    Loading loading;
    Scanner sc;
    Dialogue dialogue;
    Interactor interactor;


    public PlayerValidation(PlayerRepository playerRepository, Loading loading, Dialogue dialogue, @Lazy Interactor interactor) {
        this.playerRepository = playerRepository;
        this.loading = loading;
        this.sc = new Scanner(System.in);
        this.dialogue = dialogue;
        this.interactor = interactor;
    }

    public Player validatePlayerExists(String nickname) {
        while (playerRepository.findByNickname(nickname) == null) {
            System.out.println("Esse personagem não existe, por favor, digite seu nickname corretamente. Caso queira voltar para tela inicial digite 1");
            nickname = sc.nextLine();
            if (nickname.equals("1")) {
                dialogue.newHereDialogue();
            }
            System.out.println("Logando......");
            System.out.println();
            loading.loading(1000);
        }
        Player player = playerRepository.findByNickname(nickname);
        System.out.println(nickname + ", Você foi conectado com sucesso");
        return player;
    }

    public Player validateOpponentExists(String id) {
        String playerId = String.valueOf(interactor.getPlayer().getId());
        while (id.equals(playerId)) {
            System.out.println("Vocẽ não pode batalhar contra você mesmo, por favor, digite uma das opções que foram mostradas na tela.");
            id = sc.nextLine();
        }

        while (!id.matches("\\d") && !id.matches("\\d\\d")) {
            System.out.println("Por favor, digite um numero válido");
            id = sc.nextLine();
        }
        while (!playerRepository.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Esse personagem não existe, por favor, digite um numero válido");
            id = sc.nextLine();
            while (!id.matches("\\d") && !id.matches("\\d\\d")) {
                System.out.println("Por favor, digite um numero válido");
                id = sc.nextLine();
                while (id.equals(playerId)) {
                    System.out.println("Vocẽ não pode batalhar contra você mesmo, por favor, digite uma das opções que foram mostradas na tela.");
                    id = sc.nextLine();
                }
            }
        }
        Player player = playerRepository.findById(Long.valueOf(id)).get();
        return  player;
    }

}
