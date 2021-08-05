package com.estudo.rpg.Functions.Validations;

import com.estudo.rpg.Repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class QuestValidation {

    @Autowired
    QuestRepository questRepository;

    Scanner sc;

    public QuestValidation(QuestRepository questRepository) {
        this.questRepository = questRepository;
        this.sc = new Scanner(System.in);
    }

    public int validateQuestExists(String id) {
        while (!id.matches("\\d") && !id.matches("\\d\\d")) {
            System.out.println("Por favor, digite um numero válido");
            id = sc.nextLine();
        }

        while (!questRepository.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Essa quest não existe, por favor, digite um numero válido");
            id = sc.nextLine();
            while (!id.matches("\\d") && !id.matches("\\d\\d")) {
                System.out.println("Por favor, digite um numero válido");
                id = sc.nextLine();
            }
        }
        return Integer.parseInt(id);
    }

}
