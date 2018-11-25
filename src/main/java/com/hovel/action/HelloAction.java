package com.hovel.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloAction {

    @RequestMapping("/hi")
    @ResponseBody
    public String hello(){
        return "helli";
    }
}
