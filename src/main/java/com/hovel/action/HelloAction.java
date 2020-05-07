package com.hovel.action;

import com.hovel.base.thread.threadlocal.RequestHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/test/array")
    @ResponseBody
    public Object testArray(@RequestBody Param param){

        System.out.println(param);

        return "hello";
    }

    @GetMapping("/test/array0")
    @ResponseBody
    public Object testArray0(@RequestBody Param param){

        System.out.println(param);

        return "hello";
    }
}
