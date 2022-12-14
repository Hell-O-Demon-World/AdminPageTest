package com.golfzonaca.adminpage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mileage_transaction_usage_history")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MileageTransactionUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "MILEAGE_PAYMENT_UPDATE_ID")
    private MileagePaymentUpdate mileagePaymentUpdate;
    @Column(name = "POINT")
    private long point;

    @Builder
    public MileageTransactionUsage(MileagePaymentUpdate mileagePaymentUpdate, long point) {
        this.mileagePaymentUpdate = mileagePaymentUpdate;
        this.point = point;
    }
}
