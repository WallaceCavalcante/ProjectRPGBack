package com.estudo.rpg.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull @NotEmpty @Length(min = 3)
    private String name;
    @NotNull
    private float attack;
    @NotNull @NotEmpty
    private String rarity;
    @NotNull
    private int level;
    @NotNull
    private String type;
    private boolean isSelling;
    @Nullable
    private Double price;
    private Long owner;

    public Weapon() {
    }

    public Weapon setNewWeapon(Weapon weapon){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        Weapon newWeapon = new Weapon();
        newWeapon.setName(weapon.getName());
        newWeapon.setAttack((float) ((Math.random() * ((weapon.getAttack()*2 - weapon.getAttack()) + 1)) + weapon.getAttack()));
        newWeapon.setRarity(weapon.getRarity());
        newWeapon.setLevel(weapon.getLevel());
        newWeapon.setType(weapon.getType());
        newWeapon.setSelling(false);
        newWeapon.setPrice(0.0);
        return newWeapon;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        isSelling = selling;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
}
