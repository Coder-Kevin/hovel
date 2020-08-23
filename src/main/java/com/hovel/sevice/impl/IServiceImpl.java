package com.hovel.sevice.impl;

import com.hovel.sevice.IService;
import org.springframework.stereotype.Service;

/**
 * @author Kevin
 */
@Service
public class IServiceImpl implements IService {
    @Override
    public void test() {
        System.out.println("-----test");
    }

//    @Override
//    public void hello() {
//        System.out.println("-----override hello-----");
//    }
}
