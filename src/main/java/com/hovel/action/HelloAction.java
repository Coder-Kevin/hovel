package com.hovel.action;

import com.hovel.threadlocal.RequestHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloAction {

    @RequestMapping("/hi")
    @ResponseBody
    public String hello(){

        System.out.println("------" + RequestHelper.get());
        return "helli";
    }
}
