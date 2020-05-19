package com.clockworkjava.projektwisniewski.controllers;

import com.clockworkjava.projektwisniewski.components.TimeComponent;
import com.clockworkjava.projektwisniewski.domain.Knight;
import com.clockworkjava.projektwisniewski.domain.PlayerInformation;
import com.clockworkjava.projektwisniewski.domain.repository.PlayerInformationRepository;
import com.clockworkjava.projektwisniewski.services.KnightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class KnightController {

    @Autowired
    TimeComponent timeComponent;

    @Autowired
    PlayerInformationRepository playerInformationRepository;


    @Autowired
    KnightService service;

    @RequestMapping("/knights")
    public String getKnights(Model model) {
        PlayerInformation playerInformation= playerInformationRepository.getFirst();
        List<Knight> allKnights = service.getAllKnights();
        model.addAttribute("knights", allKnights);
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformation);
        return "knights";
    }


    @RequestMapping("/knight")
    public String getKnight(@RequestParam("id") Integer id, Model model) {
        Knight knight = service.getKnight(id);
        PlayerInformation playerInformation= playerInformationRepository.getFirst();
        model.addAttribute("knight", knight);
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformation);
        return "knight";
    }

    @RequestMapping(value="/knights",method= RequestMethod.POST)
    public String saveKnight(@Valid Knight knight, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Błąd");
            bindingResult.getAllErrors().forEach(error->{
                System.out.println(error.getObjectName()+" "+error.getDefaultMessage());
            });
            return "knightform";
        } else {
            service.saveKnight(knight);
            return "redirect:/knights";
        }
    }

    @RequestMapping("/newknight")
    public String createKnight(Model model){
        PlayerInformation playerInformation= playerInformationRepository.getFirst();
        model.addAttribute("knight",new Knight());
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformation);
        return "knightform";
    }



    @RequestMapping(value="/knight/delete/{id}")
    public String deleteKnight(@PathVariable("id") Integer id) {
        service.deleteKnight(id);
        return "redirect:/knights";
    }





    @RequestMapping("/knight/upgrade")
    public String upgradeKnight(@RequestParam("id") Integer id){



        service.upgradeKnight(id);
        return "redirect:/knight?id="+id;
    }

    @RequestMapping("/knight/heal")
    public String healKnight(@RequestParam("id") Integer id){



        service.healKnight(id);
        return "redirect:/knight?id="+id;
    }


    @RequestMapping("/knight/upmaxhp")
    public String upmaxhp(@RequestParam("id") Integer id){



        service.upmaxhpKnight(id);
        return "redirect:/knight?id="+id;
    }




}
