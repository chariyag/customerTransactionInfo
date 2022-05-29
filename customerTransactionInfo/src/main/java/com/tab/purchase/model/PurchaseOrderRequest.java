package com.tab.purchase.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PurchaseOrderRequest  {
    private String purchaseOrderTime;
    private Long customerId;
    private int quantity;
    private String productCode;


}
