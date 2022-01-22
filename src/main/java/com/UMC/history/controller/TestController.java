package com.UMC.history.controller;

import com.UMC.history.entity.TestEntity;
import com.UMC.history.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    TestRepository testRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMappingTest(){
        System.out.println("github action");
        return("test");
    }

    @RequestMapping(value = "/insert/{dummydata}", method = RequestMethod.POST)
    public void postMappingTest(@PathVariable("dummydata") String dummy){
        try{
        TestEntity testInput = TestEntity.builder().dummyData(dummy).build();
        testRepository.save(testInput);
        System.out.println("success");
        }
        catch (Exception error){
            System.out.println(error);
        }
    }

}
