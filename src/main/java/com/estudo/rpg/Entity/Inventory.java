package com.estudo.rpg.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Weapon> weapons;

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void addWeapon(Weapon newWeapon){
        this.weapons.add(newWeapon);
    }
    public void removeWeapon(Long weaponId){
        for(int i = 0; i < weapons.size(); i++){
            if(weapons.get(i).getId() == weaponId){
                weapons.remove(i);
            }
        }
    }
}
