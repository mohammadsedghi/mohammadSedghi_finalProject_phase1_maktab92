package ir.maktab.entity.order_status_entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDone extends BaseEntity<Long> {
}
