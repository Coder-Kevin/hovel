package com.hovel.design.action.chainResponsibility.example.workflow;

/**
 * 班主任
 */
public class ClassAdviser extends Leader {

    @Override
    public void handleApply(int days) {
        if (days < 3) {
            System.out.println("班主任批准请假" + days + "天");
        } else {
            System.out.println("班主任同意");
            if (getNext() != null) {
                getNext().handleApply(days);
            }
        }
    }

}
