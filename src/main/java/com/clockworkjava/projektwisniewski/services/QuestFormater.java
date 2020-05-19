package com.clockworkjava.projektwisniewski.services;

import com.clockworkjava.projektwisniewski.domain.Quest;
import com.clockworkjava.projektwisniewski.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;


@Service
public class QuestFormater implements Formatter<Quest> {


    @Autowired
    QuestRepository repository;


    @Override
    public Quest parse(String idAssr, Locale locale) throws ParseException {
        Integer id=Integer.parseInt(idAssr);
        return repository.getQuest(id);
    }

    @Override
    public String print(Quest quest, Locale locale) {
        return quest.toString();
    }
}
