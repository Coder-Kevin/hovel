package com.hovel.spring.action;

import com.hovel.base.thread.threadlocal.RequestHelper;
import com.hovel.spring.sevice.IService;
import com.hovel.spring.sevice.impl.IServiceImpl;
import com.hovel.spring.sevice.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloAction {

    @Autowired
    private IService service;

    @Autowired
    private TestService testService;

    @RequestMapping("/hi")
    @ResponseBody
    public String hello() {
        service.test();

//        service.hello();

        testService.test();

        TestService.testFinal();

        TestService.testStatic();

        System.out.println(testService);
        System.out.println(service);
        System.out.println(new IServiceImpl());

        /**
         * class com.hovel.spring.sevice.impl.TestService$$EnhancerBySpringCGLIB$$ef633e82
         * class com.hovel.spring.sevice.impl.IServiceImpl$$EnhancerBySpringCGLIB$$b7e8bfc5
         */
        System.out.println(testService.getClass());
        System.out.println(service.getClass());

        System.out.println("------" + RequestHelper.get());
        return "helli";
    }

    @GetMapping("/test/array")
    @ResponseBody
    public Object testArray(@RequestBody Param param) {

        System.out.println(param);

        return "hello";
    }

    @GetMapping("/test/array0")
    @ResponseBody
    public Object testArray0(@RequestBody Param param) {

        System.out.println(param);

        return "hello";
    }
}
