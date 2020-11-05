package com.hovel.design.action.chainResponsibility.example.workflow;

public abstract class Leader {

    private Leader next;

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }

    public abstract void handleApply(int days);
}
