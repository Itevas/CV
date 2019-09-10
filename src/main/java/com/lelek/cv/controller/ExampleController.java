package com.lelek.cv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping("/")
    public String index(){
        return "<br><hr><h1 align=\"center\" >Spring Boot Works!</h1><hr>" +
                "<h3 align=\"center\">" +
                "<a href=\"https://play.google.com/store/apps/details?id=com.storksking.trafficlamps\">" +
                "Traffic Lights" +
                "</a>" +
                "</h3>";
    }
}
