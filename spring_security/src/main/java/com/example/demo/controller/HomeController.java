package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String loginPage(){
        return "login.html";
    }

    @RequestMapping("/success")
    public String success(){
       return "success.html";
    }
}
