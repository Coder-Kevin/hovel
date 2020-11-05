package com.hovel.design.action.chainResponsibility.example.workflow;

public class Main {

    public static void main(String[] args) {
        Leader classAdviser = new ClassAdviser();
        classAdviser.setNext(new DepartmentHead());

        Student student = new Student("张三");
        student.askLeave(classAdviser, 2);
    }

}
