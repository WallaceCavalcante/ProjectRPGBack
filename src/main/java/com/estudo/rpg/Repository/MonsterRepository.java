package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

//    @Query(value = "SELECT m FROM MONSTER m WHERE m.RACE = ?1")
//    List<Monster> findMonstersByRace(String race);

}
