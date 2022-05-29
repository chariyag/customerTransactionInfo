package com.tab.purchase.entitiy;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="PRODUCT")
public class Product {
    @Id
    @Column(name= "PRODUCT_CODE")
    private String productCode;
    @Column(name="COST")
    private BigDecimal cost;
    @Column(name="STATUS")
    private String status;

   }
