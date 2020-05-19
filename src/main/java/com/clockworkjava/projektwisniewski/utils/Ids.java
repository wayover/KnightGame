package com.clockworkjava.projektwisniewski.utils;

import java.util.Set;

public class Ids {


    static public int getNewId(Set<Integer> keySoFar) {
        if(keySoFar.isEmpty()){
            return 0;
        }
        else{
            Integer integer = keySoFar.stream().max((o1, o2) -> o1.compareTo(o2)).get();
            return integer+1;
        }
    }

}
