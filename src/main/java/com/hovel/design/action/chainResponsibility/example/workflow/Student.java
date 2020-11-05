package com.hovel.design.action.chainResponsibility.example.workflow;

public class Student {

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void askLeave(Leader leader, int days) {
        System.out.println(name + "申请" + days + "假期，审批中...");
        leader.handleApply(days);
    }
}
