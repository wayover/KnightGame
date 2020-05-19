package com.clockworkjava.projektwisniewski.domain.repository;

import com.clockworkjava.projektwisniewski.domain.Quest;
import com.clockworkjava.projektwisniewski.utils.Ids;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class QuestRepository {

    @PersistenceContext
    private EntityManager em;

     Random rand=new Random();




    @Transactional
    public void createQuest(String description){

        Quest newQuest=new Quest(description);
        em.persist(newQuest);

    }

    public List<Quest> getAll(){
       return em.createQuery("from Quest",Quest.class).getResultList();
    }





    @Transactional
    public void deleteQuest(Quest quest){
        em.remove(quest);
    }




    @Scheduled(fixedDelayString = "${questCreationDelay}")
    @Transactional
    public void createRandomQuest(){
        List<String> descriptions=new ArrayList<>();
        descriptions.add("Uratuj księżniczkę");
        descriptions.add("Zabij księżniczkę");
        descriptions.add("Zabij smoka");
        descriptions.add("Zabij wilki");
        descriptions.add("Weź udział w turnieju");


        String description = descriptions.get(rand.nextInt(descriptions.size()));
        createQuest(description);

    }

    public void update(Quest quest) {
        em.merge(quest);
    }

    public Quest getQuest(Integer id) {
        return em.find(Quest.class,id);
    }
}
