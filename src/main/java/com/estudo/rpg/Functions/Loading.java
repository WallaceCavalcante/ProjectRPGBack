package com.estudo.rpg.Functions;

import org.springframework.stereotype.Service;

@Service
public class Loading {

    public Loading() {
    }

    public void loading(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
