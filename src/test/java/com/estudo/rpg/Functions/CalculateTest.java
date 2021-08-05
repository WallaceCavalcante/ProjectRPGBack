package com.estudo.rpg.Functions;

import com.estudo.rpg.Functions.Calculate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTest {

    private Calculate calculate = new Calculate();

    @Test
    void calculateXpToLevelUp() {
    }

    @Test
    void calculatePlayerHpDadoAssassinoRetorna12e5() {
        assertEquals(12.5, calculate.calculatePlayerHp("Assassino"));
    }

    @Test
    void calculatePlayerHpDadoGuerreiroRetorna20() {
        assertEquals(20, calculate.calculatePlayerHp("Guerreiro"));
    }

    @Test
    void calculatePlayerHpDadoVikingRetorna18() {
        assertEquals(18, calculate.calculatePlayerHp("Viking"));
    }

    @Test
    void calculatePlayerHpDadoArqueiroRetorna14() {
        assertEquals(14, calculate.calculatePlayerHp("Arqueiro"));
    }

    @Test
    void calculatePlayerHpDadoPistoleiroRetorna11e5() {
        assertEquals(11.5, calculate.calculatePlayerHp("Pistoleiro"));
    }

    @Test
    void calculatePlayerHpDadoMagoRetorna11() {
        assertEquals(11, calculate.calculatePlayerHp("Mago"));
    }

    @Test
    void calculatePlayerHpDadoNecromanteRetorna11() {
        assertEquals(11, calculate.calculatePlayerHp("Necromante"));
    }

    @Test
    void calculatePlayerHpDadoVazioRetorna11() {
        assertEquals(11, calculate.calculatePlayerHp(""));
    }

    @Test
    void calculatePlayerHpDadoQualquerOutroRetorna11() {
        assertEquals(11, calculate.calculatePlayerHp("Undefined"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoAssassinoRetorna1e5() {
        assertEquals(1.5, calculate.calculatePlayerAttackSpeed("Assassino"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoGuerreiroRetorna1() {
        assertEquals(1, calculate.calculatePlayerAttackSpeed("Guerreiro"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoVikingRetorna0e9() {
        assertEquals(0.9, calculate.calculatePlayerAttackSpeed("Viking"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoArqueiroRetorna1e2() {
        assertEquals(1.2, calculate.calculatePlayerAttackSpeed("Arqueiro"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoPistoleiroRetorna2e0() {
        assertEquals(2.0, calculate.calculatePlayerAttackSpeed("Pistoleiro"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoMagoRetorna1e5() {
        assertEquals(1.5, calculate.calculatePlayerAttackSpeed("Mago"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoNecromanteRetorna1e5() {
        assertEquals(1.5, calculate.calculatePlayerAttackSpeed("Necromante"));
    }

    @Test
    void calculatePlayerAttackSpeedDadoVazioRetorna1e5() {
        assertEquals(1.5, calculate.calculatePlayerAttackSpeed(""));
    }

    @Test
    void calculatePlayerAttackSpeedDadoQualquerOutroRetorna1e5() {
        assertEquals(1.5, calculate.calculatePlayerAttackSpeed("Undefined"));
    }

    @Test
    void calculateMonsterAttackSpeedDadoOrcRetorna0e9() {
        assertEquals(0.9, calculate.calculateMonsterAttackSpeed("Orc"));
    }

    @Test
    void calculateMonsterAttackSpeedDadoGoblinRetorna1e1() {
        assertEquals(1.1, calculate.calculateMonsterAttackSpeed("Goblin"));
    }

    @Test
    void calculateMonsterAttackSpeedDadoVazioRetorna1() {
        assertEquals(1, calculate.calculateMonsterAttackSpeed(""));
    }

    @Test
    void calculateMonsterAttackSpeedDadoQualquerOutroRetorna1() {
        assertEquals(1, calculate.calculateMonsterAttackSpeed("Undefined"));
    }
}