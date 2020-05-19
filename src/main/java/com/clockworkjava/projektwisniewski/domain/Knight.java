package com.clockworkjava.projektwisniewski.domain;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Knight {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min=2,max=40,message = "Długość nazwy musi być pomiędzy 2 a 40")
    private String name;

    @NotNull
    @Range(min=16,max=70,message = "Wiek musi być pomiędzy 16 a 70")
    private int age;

    private int level=0;
    private int bonus=0;
    private int exp=1;
    private int hp=100;
    private int hpmax=100;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpmax() {
        return hpmax;
    }

    public void setHpmax(int hpmax) {
        this.hpmax = hpmax;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @OneToOne
    private Quest quest;

    public Quest getQuest() {
        return quest;
    }

    public Knight() {

    }

    public Knight(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public void setAge(int age){
        this.age=age;
    }

    public int getAge()
    {
        return this.age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuest(Quest quest) {
        if(quest!=null) {
            quest.setStarted(true);
        }
        this.quest = quest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "Rycerz o imieniu " + name + "(" + age + "). Ma za zadanie: " + quest;
    }
}
