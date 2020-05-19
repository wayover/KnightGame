package com.clockworkjava.projektwisniewski.services;

import com.clockworkjava.projektwisniewski.domain.Knight;
import com.clockworkjava.projektwisniewski.domain.PlayerInformation;
import com.clockworkjava.projektwisniewski.domain.repository.KnightRepository;
import com.clockworkjava.projektwisniewski.domain.repository.PlayerInformationRepository;
import com.clockworkjava.projektwisniewski.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class KnightService {

    @Autowired
    KnightRepository knightRepository;

    @Autowired
    PlayerInformationRepository playerInformation;

    @Autowired
    QuestRepository questRepository;



    public List<Knight> getAllKnights() {
        return new ArrayList<>(knightRepository.getAllKnights());
    }


    public void saveKnight(Knight knight) {
        knightRepository.createKnight(knight);
    }

    public Knight getKnight(Integer id) {
        return knightRepository.getKnightById(id);
    }

    public void deleteKnight(Integer id) {
        knightRepository.deleteKnight(id);



        PlayerInformation first= playerInformation.getFirst();
        int currentGold=first.getGold();
        currentGold+=200;
        playerInformation.getFirst().setGold(currentGold);

    }


    public void deleteKnight2(Integer id) {
        knightRepository.deleteKnight(id);
    }

    public void updateKnight(Knight knight) {
        knightRepository.updateKnight(knight.getId(),knight);
    }

    public int collectRewards() {
        Predicate<Knight> knightPredicate = knight -> {
            if (knight.getQuest() != null) {
                return knight.getQuest().isCompleted();
            } else {
                return false;
            }
        };

        int sum = knightRepository.getAllKnights().stream().filter(knightPredicate)
                .mapToInt(knight -> knight.getQuest().getReward())
                .sum();

        knightRepository.getAllKnights().stream().filter(knightPredicate).forEach(knight -> {
            knight.setQuest(null);
        });

        return sum;
    }

    @Transactional
    public void getMyGold(){


        List<Knight> allKnights= getAllKnights();
        allKnights.forEach(knight -> {
            if(knight.getQuest()!=null) {
               boolean competed= knight.getQuest().isCompleted();

               if(competed){
                   questRepository.update(knight.getQuest());
                   knight.setLevel(knight.getLevel()+1);

                   PlayerInformation first= playerInformation.getFirst();
                   int currentGold=first.getGold();

                   double bonusrw=knight.getQuest().getReward()*((knight.getExp()-1)*0.1);


                   currentGold+=(knight.getBonus()+bonusrw);
                   playerInformation.getFirst().setGold(currentGold);

                   if(knight.getHp()<=20){
                       deleteKnight2(knight.getId());
                   }
                   else{
                       knight.setHp(knight.getHp()-20);
                   }





               }

            }
            int bns=knight.getLevel()/3;
            knight.setBonus(20*bns);
        });

        PlayerInformation first= playerInformation.getFirst();
        int currentGold=first.getGold();
        playerInformation.getFirst().setGold(currentGold+ collectRewards());

    }


    public void upgradeKnight(Integer id) {
        knightRepository.upgradeKnight(id);
    }

    public void healKnight(Integer id) {
        knightRepository.healKnight(id);
    }

    public void upmaxhpKnight(Integer id) {
        knightRepository.upmaxhpKnight(id);
    }
}