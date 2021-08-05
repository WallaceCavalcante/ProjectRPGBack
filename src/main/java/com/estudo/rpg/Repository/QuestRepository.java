package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {

    List<Quest> findByMonsterName(String name);
    List<Quest> findByBossName(String name);
}
