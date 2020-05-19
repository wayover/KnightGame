package com.clockworkjava.projektwisniewski.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;


@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    private String description;
    private int reward=100;
    protected int lenght =3;
    private boolean started = false;
    private boolean completed=false;

    protected LocalDateTime startDate;


    public Quest(){

    }

     public Quest(int id,String description){
         this.Id=id;

         Random czas = new Random();
         int randczas = czas.nextInt(30);
         this.lenght=randczas;
         Random gold = new Random();
         int randgold = gold.nextInt(300);
         this.reward=randgold;

         description+=" czas:"+lenght+", nagroda:"+reward;
         this.description=description;

     }
     public Quest(String description){

         Random czas = new Random();
         int randczas = czas.nextInt(30)+5;
         this.lenght=randczas;
         Random gold = new Random();
         int randgold = gold.nextInt(250)+50;
         this.reward=randgold;

         description+=" czas:"+lenght+"s , nagroda:"+reward+" złota";

        this.description=description;
     }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        if(started){
            this.startDate=LocalDateTime.now();
        }
         this.started = started;

    }

    public boolean isCompleted() {

        if (this.completed) {
            return this.completed;
        } else {
            LocalDateTime now = LocalDateTime.now();

            LocalDateTime questEndDate = this.startDate.plusSeconds(this.lenght);

            boolean isAfter = now.isAfter(questEndDate);
            if (isAfter) {
                this.completed = true;
            }
            return isAfter;
        }
    }



    @Override
    public String toString() {
         return description;
     }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }




}
