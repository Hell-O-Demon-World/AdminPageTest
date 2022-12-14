package com.golfzonaca.adminpage.domain.type;

public enum PayType {
    DEPOSIT("보증금"), BALANCE("잔금"), FULL_PAYMENT("전체 결제");
    private final String description;

    PayType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
