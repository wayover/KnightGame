package com.clockworkjava.projektwisniewski;


import com.clockworkjava.projektwisniewski.domain.PlayerInformation;
import com.clockworkjava.projektwisniewski.domain.repository.KnightRepository;
import com.clockworkjava.projektwisniewski.domain.repository.PlayerInformationRepository;
import com.clockworkjava.projektwisniewski.domain.repository.QuestRepository;
import com.clockworkjava.projektwisniewski.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {




    @Autowired
    KnightRepository knightRepository;

    @Autowired
    QuestRepository questRepository;


    @Autowired
    QuestService questService;


    @Autowired
    PlayerInformationRepository playerInformationRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        questRepository.createRandomQuest();
        questRepository.createRandomQuest();

        knightRepository.createKnight("Andrzej",23);

        playerInformationRepository.createPlaterInformation(new PlayerInformation());


        questService.assignRandomQuest("Andrzej");


}
}
