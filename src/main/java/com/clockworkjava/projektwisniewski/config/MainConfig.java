package com.clockworkjava.projektwisniewski.config;


import com.clockworkjava.projektwisniewski.domain.repository.DBKnightRepository;
import com.clockworkjava.projektwisniewski.domain.repository.KnightRepository;
import org.springframework.context.annotation.*;

@Configuration

public class MainConfig {




    @Bean(name="DBKnightRepository")
    @Profile("prod")
    public KnightRepository createDBRepo(){
        KnightRepository repo=new DBKnightRepository();
        return repo;
    }











}
