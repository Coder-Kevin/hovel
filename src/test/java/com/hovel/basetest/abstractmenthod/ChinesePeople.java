package com.hovel.basetest.abstractmenthod;

import org.junit.Test;

public class ChinesePeople extends AbstractPerson {

    @Override
    public void say(){
        try{
            for(int i=0;i<7;i++){
                if(i == 5){
                    throw new RuntimeException("I can't to say!");
                }
                System.out.println("I want to say the something about " + i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void test(){
        Person chineseOne = new ChinesePeople();
        chineseOne.say();
    }
}
