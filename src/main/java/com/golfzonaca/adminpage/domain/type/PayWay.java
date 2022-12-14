package com.golfzonaca.adminpage.domain.type;

public enum PayWay {
    PREPAYMENT("선결제"), POSTPAYMENT("현장결제");
    private final String description;

    PayWay(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
