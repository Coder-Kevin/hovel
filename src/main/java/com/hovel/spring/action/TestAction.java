package com.hovel.spring.action;

import com.hovel.base.thread.threadlocal.RequestHelper;
import com.hovel.spring.sevice.IService;
import com.hovel.spring.sevice.impl.IServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestAction {

    @Autowired
    private IService service;

    @RequestMapping("/test")
    @ResponseBody
    public String hello() {
        service.test();

//        service.hello();

        System.out.println(service);
        System.out.println(new IServiceImpl());

        /**
         * class com.hovel.spring.sevice.impl.TestService$$EnhancerBySpringCGLIB$$ef633e82
         * class com.hovel.spring.sevice.impl.IServiceImpl$$EnhancerBySpringCGLIB$$b7e8bfc5
         */
        System.out.println(service.getClass());

        System.out.println("------" + RequestHelper.get());
        return "helli";
    }

}
