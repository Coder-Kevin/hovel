package com.hovel.design.action.templateMethod;

public class StudentPay extends PayTemplate {
    @Override
    public void pay() {
        System.out.println("学生使用工商银行卡支付");
    }

}
