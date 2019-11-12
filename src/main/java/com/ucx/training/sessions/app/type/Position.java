package com.ucx.training.sessions.app.type;

public enum Position {
    QA(100),
    DEV(200),
    PM(300);

    private final Integer payPerHour;

    Position(Integer payPerHour) {
        this.payPerHour = payPerHour;
    }


    public Integer getPayPerHour() {
        return payPerHour;
    }
}
