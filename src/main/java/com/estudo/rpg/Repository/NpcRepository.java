package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Npc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NpcRepository extends JpaRepository<Npc, Long> {

    List<Npc> findByJob(String job);
}
