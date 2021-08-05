package com.estudo.rpg.Functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardsTest {

    Rewards rewards = new Rewards();

    @Test
    void basicWeaponRewardDadoAssassinoRetorna3() {
        assertEquals(3, rewards.basicWeaponReward("Assassino"));
    }

    @Test
    void basicWeaponRewardDadoGuerreiroRetorna1() {
        assertEquals(1, rewards.basicWeaponReward("Guerreiro"));
    }

    @Test
    void basicWeaponRewardDadoVikingRetorna2() {
        assertEquals(2, rewards.basicWeaponReward("Viking"));
    }

    @Test
    void basicWeaponRewardDadoArqueiroRetorna4() {
        assertEquals(4, rewards.basicWeaponReward("Arqueiro"));
    }

    @Test
    void basicWeaponRewardDadoPistoleiroRetorna20() {
        assertEquals(20, rewards.basicWeaponReward("Pistoleiro"));
    }

    @Test
    void basicWeaponRewardDadoMagoRetorna5() {
        assertEquals(5, rewards.basicWeaponReward("Mago"));
    }

    @Test
    void basicWeaponRewardDadoNecromanteRetorna5() {
        assertEquals(5, rewards.basicWeaponReward("Necromante"));
    }

    @Test
    void basicWeaponRewardDadoVazioRetorna5() {
        assertEquals(5, rewards.basicWeaponReward(""));
    }

    @Test
    void basicWeaponRewardDadoQualquerOutroRetorna5() {
        assertEquals(5, rewards.basicWeaponReward("Undefined"));
    }

    @Test
    void intermediateWeaponRewardDadoAssassinoRetorna23() {
        assertEquals(23, rewards.intermediateWeaponReward("Assassino"));
    }

    @Test
    void intermediateWeaponRewardDadoGuerreiroRetorna21() {
        assertEquals(21, rewards.intermediateWeaponReward("Guerreiro"));
    }

    @Test
    void intermediateWeaponRewardDadoVikingRetorna22() {
        assertEquals(22, rewards.intermediateWeaponReward("Viking"));
    }

    @Test
    void intermediateWeaponRewardDadoArqueiroRetorna24() {
        assertEquals(24, rewards.intermediateWeaponReward("Arqueiro"));
    }

    @Test
    void intermediateWeaponRewardDadoPistoleiroRetorna26() {
        assertEquals(26, rewards.intermediateWeaponReward("Pistoleiro"));
    }


    @Test
    void intermediateWeaponRewardDadoMagoRetorna25() {
        assertEquals(25, rewards.intermediateWeaponReward("Mago"));
    }

    @Test
    void intermediateWeaponRewardDadoNecromanteRetorna25() {
        assertEquals(25, rewards.intermediateWeaponReward("Necromante"));
    }

    @Test
    void intermediateWeaponRewardDadoVazioRetorna25() {
        assertEquals(25, rewards.intermediateWeaponReward(""));
    }

    @Test
    void intermediateWeaponRewardDadoQualquerOutroRetorna25() {
        assertEquals(25, rewards.intermediateWeaponReward("Undefined"));
    }

    @Test
    void advancedWeaponRewardDadoAssassinoRetorna30() {
        assertEquals(30, rewards.advancedWeaponReward("Assassino"));
    }

    @Test
    void advancedWeaponRewardDadoGuerreiroRetorna28() {
        assertEquals(28, rewards.advancedWeaponReward("Guerreiro"));
    }

    @Test
    void advancedWeaponRewardDadoVikingRetorna29() {
        assertEquals(29, rewards.advancedWeaponReward("Viking"));
    }

    @Test
    void advancedWeaponRewardDadoArqueiroRetorna31() {
        assertEquals(31, rewards.advancedWeaponReward("Arqueiro"));
    }

    @Test
    void advancedWeaponRewardDadoPistoleiroRetorna33() {
        assertEquals(33, rewards.advancedWeaponReward("Pistoleiro"));
    }

    @Test
    void advancedWeaponRewardDadoMagoRetorna32() {
        assertEquals(32, rewards.advancedWeaponReward("Mago"));
    }

    @Test
    void advancedWeaponRewardDadoNecromanteRetorna32() {
        assertEquals(32, rewards.advancedWeaponReward("Necromante"));
    }

    @Test
    void advancedWeaponRewardDadoVazioRetorna32() {
        assertEquals(32, rewards.advancedWeaponReward(""));
    }

    @Test
    void advancedWeaponRewardDadoQualquerOutroRetorna32() {
        assertEquals(32, rewards.advancedWeaponReward("Undefined"));
    }

    @Test
    void heroicWeaponRewardDadoAssassinoRetorna37() {
        assertEquals(37, rewards.heroicWeaponReward("Assassino"));
    }

    @Test
    void heroicWeaponRewardDadoGuerreiroRetorna35() {
        assertEquals(35, rewards.heroicWeaponReward("Guerreiro"));
    }

    @Test
    void heroicWeaponRewardDadoVikingRetorna36() {
        assertEquals(36, rewards.heroicWeaponReward("Viking"));
    }

    @Test
    void heroicWeaponRewardDadoArqueiroRetorna38() {
        assertEquals(38, rewards.heroicWeaponReward("Arqueiro"));
    }

    @Test
    void heroicWeaponRewardDadoPistoleiroRetorna40() {
        assertEquals(40, rewards.heroicWeaponReward("Pistoleiro"));
    }

    @Test
    void heroicWeaponRewardDadoMagoRetorna39() {
        assertEquals(39, rewards.heroicWeaponReward("Mago"));
    }

    @Test
    void heroicWeaponRewardDadoNecromanteRetorna39() {
        assertEquals(39, rewards.heroicWeaponReward("Necromante"));
    }

    @Test
    void heroicWeaponRewardDadoVazioRetorna39() {
        assertEquals(39, rewards.heroicWeaponReward(""));
    }

    @Test
    void heroicWeaponRewardDadoQualquerOutroRetorna39() {
        assertEquals(39, rewards.heroicWeaponReward("Necromante"));
    }
}