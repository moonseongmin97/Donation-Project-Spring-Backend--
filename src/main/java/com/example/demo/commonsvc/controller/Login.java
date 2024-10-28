package com.example.demo.APIconnect;


import com.example.demo.DemoApplication;
import member.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.util.Map;


@RestController
public class conncection {
    @CrossOrigin(origins  = "/")
    @GetMapping("/api/hello")
    //@RequestMapping("/api")
    public String Hello(){

String a=DemoApplication.findMember();

        return a;
    }
    @CrossOrigin(origins  = "/")
    @PostMapping("/api/login")
    //@RequestMapping("/api")
    public String Login(HttpServletRequest request, @RequestBody Map<String, Object> paramMap){

        System.out.println("찍힘="+paramMap);


        return "";
    }

    @CrossOrigin(origins  = "/")
    @PostMapping("/api/Join")
    //@RequestMapping("/api")
    public String Join(HttpServletRequest request, @RequestBody Map<String, Object> paramMap){


        JoinService.


        return "";
    }
}
