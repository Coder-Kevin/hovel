package com.hovel.spring.sevice.impl;

import com.hovel.spring.sevice.IService;
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

}
