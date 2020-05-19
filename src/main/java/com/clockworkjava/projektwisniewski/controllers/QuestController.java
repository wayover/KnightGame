package com.clockworkjava.projektwisniewski.controllers;


import com.clockworkjava.projektwisniewski.domain.Knight;
import com.clockworkjava.projektwisniewski.domain.PlayerInformation;
import com.clockworkjava.projektwisniewski.domain.Quest;
import com.clockworkjava.projektwisniewski.domain.repository.PlayerInformationRepository;
import com.clockworkjava.projektwisniewski.services.KnightService;
import com.clockworkjava.projektwisniewski.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestController {


    @Autowired
    KnightService knightService;



    @Autowired
    QuestService questService;

    @Autowired
    PlayerInformationRepository playerInformationRepository;



    @RequestMapping("/assignQuest")
    public String assignQuest(@RequestParam("knightId") Integer id, Model model){

        Knight knight= knightService.getKnight(id);
        List<Quest> notStartedQuest=questService.getAllNotStartedQuests();
        model.addAttribute("knight",knight);
        model.addAttribute("notStartedQuests",notStartedQuest);

        return "assignQuest";
    }


    @RequestMapping(value="/assignQuest",method = RequestMethod.POST)
    public String assignQuest(Knight knight){

        knightService.updateKnight(knight);
    //    Quest quest=knight.getQuest();
      //  questService.update(quest);
        return "redirect:/knights";
    }



    @RequestMapping(value="/checkQuests")
    public String checkQuests(){



        knightService.getMyGold();

        return "redirect:/knights";
    }

}
