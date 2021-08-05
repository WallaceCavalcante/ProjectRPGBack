package com.estudo.rpg.Entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class Quest implements Comparable<Quest> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private int target;
    @ManyToOne(fetch = FetchType.EAGER)
    private Monster monster;
    @ManyToOne(fetch = FetchType.EAGER)
    private Boss boss;
    private boolean completed = false;

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

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(Quest quest) {
        if(this.getId() < quest.getId()){
            return -1;
        }
        return 1;
    }
}
