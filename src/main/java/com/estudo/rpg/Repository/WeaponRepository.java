package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Player;
import com.estudo.rpg.Entity.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    List<Weapon> findByRarity(String name);

    @Query(value = "SELECT w FROM Weapon w WHERE w.isSelling = true")
    List<Weapon> findWeaponsToSell();
}
