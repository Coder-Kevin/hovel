package com.hovel.design.action.chainResponsibility.example.workflow;

/**
 * 系主任
 */
public class DepartmentHead extends Leader {

    @Override
    public void handleApply(int days) {
        if (days < 15) {
            System.out.println("系主任批准请假" + days + "天");
        } else {
            System.out.println("系主任同意");
            if (getNext() != null) {
                getNext().handleApply(days);
            }
        }
    }

}
