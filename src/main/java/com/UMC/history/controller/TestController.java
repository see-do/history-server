package com.UMC.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMappingTest(){
        System.out.println("github action");
        return("test");
    }



}
