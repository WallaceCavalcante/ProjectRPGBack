package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Boss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BossRepository extends JpaRepository<Boss, Long> {

//    @Query(value = "SELECT b FROM BOSS b WHERE b.RACE = ?1")
//    List<Boss> findBossesByRace(String race);

}
