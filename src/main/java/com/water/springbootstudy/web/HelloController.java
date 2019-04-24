package com.water.springbootstudy.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    control + return 相当于window上的alt+insert

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
