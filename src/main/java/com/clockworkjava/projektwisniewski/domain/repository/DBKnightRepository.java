package com.clockworkjava.projektwisniewski.domain.repository;

import com.clockworkjava.projektwisniewski.domain.Knight;
import com.clockworkjava.projektwisniewski.domain.PlayerInformation;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;


public class DBKnightRepository implements KnightRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void createKnight(String name, int age) {

        Knight knight = new Knight(name,age);

        em.persist(knight);

    }

    @Override
    public Collection<Knight> getAllKnights() {

        return  em.createQuery("from Knight", Knight.class).getResultList();
    }

    @Override
    public Optional<Knight> getKnight(String name) {

        Knight knightByName = em.createQuery("from Knight k where k.name=:name", Knight.class)
                .setParameter("name", name).getSingleResult();

        return Optional.ofNullable(knightByName);
    }

    @Override
    @Transactional
    public void deleteKnight(Integer id) {
        Knight knightId = em.createQuery("from Knight k where k.id=:id", Knight.class)
                .setParameter("id", id).getSingleResult();

        em.remove(knightId);

        PlayerInformation p=  em.createQuery("from PlayerInformation",PlayerInformation.class).getResultList().get(0);

        p.setGold(p.getGold()+200);






    }

    @Override
    public void build() {

    }

    @Override
    @Transactional
    public void createKnight(Knight knight) {
        PlayerInformation p=  em.createQuery("from PlayerInformation",PlayerInformation.class).getResultList().get(0);
       if(p.getGold()>=300) {
           p.setGold(p.getGold() - 300);
           em.persist(knight);
       }

    }

    @Override
    public Knight getKnightById(Integer id) {
        return em.find(Knight.class, id);
    }


    @Override
    @Transactional
    public void updateKnight(int id, Knight knight) {
        em.merge(knight);
    }

    @Override
    @Transactional
    public void upgradeKnight(Integer id) {
        PlayerInformation p=  em.createQuery("from PlayerInformation",PlayerInformation.class).getResultList().get(0);
        Knight knightId = em.createQuery("from Knight k where k.id=:id", Knight.class)
                .setParameter("id", id).getSingleResult();
        if(p.getGold()>=1000){
            p.setGold(p.getGold() - 1000);
            knightId.setExp(knightId.getExp()+1);
        }

    }

    @Override
    @Transactional
    public void healKnight(Integer id) {
        PlayerInformation p=  em.createQuery("from PlayerInformation",PlayerInformation.class).getResultList().get(0);
        Knight knightId = em.createQuery("from Knight k where k.id=:id", Knight.class)
                .setParameter("id", id).getSingleResult();
        if(p.getGold()>=500){
            p.setGold(p.getGold() - 500);
            knightId.setHp(knightId.getHpmax());
        }

    }


    @Override
    @Transactional
    public void upmaxhpKnight(Integer id) {
        PlayerInformation p=  em.createQuery("from PlayerInformation",PlayerInformation.class).getResultList().get(0);
        Knight knightId = em.createQuery("from Knight k where k.id=:id", Knight.class)
                .setParameter("id", id).getSingleResult();
        if(p.getGold()>=600){

            p.setGold(p.getGold() - 600);
            knightId.setHp(knightId.getHp()+20);
            knightId.setHpmax(knightId.getHpmax()+20);
        }

    }



}
