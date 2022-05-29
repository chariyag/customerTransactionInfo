package com.tab.purchase.entitiy;

import lombok.*;

import javax.persistence.*;


@Data @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="PURCHASE_ORDER")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_ORDER_ID")
    private Long purchaseOrderId;
    @Column(name = "PURCHASE_ORDER_TIME")
    private String purchaseOrderTime;//
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "PRODUCT_CODE")
    private String productCode;
}
