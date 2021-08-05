package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    List<Weapon> findByRarity(String name);
}
