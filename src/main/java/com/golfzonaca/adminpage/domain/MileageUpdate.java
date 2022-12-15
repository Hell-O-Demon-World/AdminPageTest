package com.golfzonaca.adminpage.domain;

import com.golfzonaca.adminpage.domain.type.MileageStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "mileage_update_history")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MileageUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MILEAGE_ID", nullable = false)
    private Mileage mileage;
    @Column(name = "UPDATE_POINT", nullable = false)
    private Long updatePoint;
    @Column(name = "UPDATE_DATE", nullable = false)
    private LocalDateTime updateDate;
    @Column(name = "EXPIRE_DATE", nullable = false)
    private LocalDateTime expireDate;
    @Column(name = "STATUS_TYPE", nullable = false)
    @Enumerated
    private MileageStatusType statusType;
    //양방향 매핑
    @OneToMany(mappedBy = "mileageUpdate")
    private List<MileageExpiredHistory> mileageExpiredUpdateList = new LinkedList<>();

    @Builder
    public MileageUpdate(Mileage mileage, Long updatePoint, LocalDateTime updateDate, LocalDateTime expireDate, MileageStatusType statusType) {
        this.mileage = mileage;
        this.updatePoint = updatePoint;
        this.updateDate = updateDate;
        this.expireDate = expireDate;
        this.statusType = statusType;
    }

    public Long minusPoint(Long point) {
        this.updatePoint -= point;
        return updatePoint;
    }
}