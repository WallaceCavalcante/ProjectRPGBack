package com.estudo.rpg.Repository;

import com.estudo.rpg.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByClasse(String classe);

    Player findByNickname (String nickName);

    @Query(value = "SELECT p FROM Player p ORDER BY level DESC")
    List<Player> findAllPlayerOrderByLevel();

    @Query(value = "SELECT p FROM Player p WHERE NICKNAME != ?1")
    List<Player> findAllOpponents(String nickname);
}
