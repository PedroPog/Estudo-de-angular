package com.api.pix.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
@RestController
public class controller {
    @CrossOrigin
    @GetMapping(value="helloWorld" )
    private String helloWorld(){

        Random r = new Random();
      System.out.println(r.nextInt(100));
        return "Hello world"+ r.nextInt(100);
    }

    @PostMapping("/callbackadress")
    public void callbackadress() {
        System.out.println("tudo ok" + "dadosPix.getTransactionId()");

    }
}
