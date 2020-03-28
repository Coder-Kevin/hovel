package com.hovel.base.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 发布不安全的对象  细品，就是不安全的域，导致可以修改
 */
@Slf4j
public class UnsafePublish {
    private String[] states = {"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();

        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "f";

        log.info("{}",Arrays.toString(unsafePublish.getStates()));
    }

}
