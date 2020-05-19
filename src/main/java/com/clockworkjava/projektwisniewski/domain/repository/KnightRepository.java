package com.clockworkjava.projektwisniewski.domain.repository;

import com.clockworkjava.projektwisniewski.domain.Knight;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;

public interface KnightRepository {
    void createKnight(String name, int age);
    void createKnight(Knight knight);
    Collection<Knight> getAllKnights();

    Optional<Knight> getKnight(String name);

    void deleteKnight(Integer id);


    void build();


    Knight getKnightById(Integer id);

    default void updateKnight(int id, Knight knight){
        throw new NotImplementedException();
    }


    void upgradeKnight(Integer id);

    void healKnight(Integer id);

    void upmaxhpKnight(Integer id);
}
