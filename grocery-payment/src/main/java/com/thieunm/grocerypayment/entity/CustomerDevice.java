package com.thieunm.grocerypayment.entity;

import com.thieunm.grocerybase.entity.JpaAuditable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_device", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customer_id", "device_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDevice extends JpaAuditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "firebase_token")
    private String deviceToken;
}
