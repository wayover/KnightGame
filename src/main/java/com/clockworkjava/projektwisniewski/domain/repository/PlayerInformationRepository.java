package com.clockworkjava.projektwisniewski.domain.repository;


import com.clockworkjava.projektwisniewski.domain.PlayerInformation;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Component
public class PlayerInformationRepository {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void createPlaterInformation(PlayerInformation playerInformation) {
        PlayerInformation pi=new PlayerInformation();


        em.persist(pi);
    }

    public PlayerInformation getFirst() {
        return em.createQuery("from PlayerInformation",PlayerInformation.class).getResultList().get(0);
    }



}
