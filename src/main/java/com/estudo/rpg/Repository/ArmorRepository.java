package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Armor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArmorRepository extends JpaRepository<Armor, Long> {

    List<Armor> findByRarity(String name);
}
