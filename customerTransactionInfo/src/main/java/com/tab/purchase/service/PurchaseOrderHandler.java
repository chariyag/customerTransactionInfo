package com.tab.purchase.service;

import com.tab.purchase.entitiy.PurchaseOrder;
import com.tab.purchase.model.PurchaseOrderRequest;
import com.tab.purchase.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseOrderHandler {

    private final PurchaseOrderRepository transactionRepository;

    private final PurchaseOrderValidation purchaseOrderValidation;

    public PurchaseOrder saveProductTransactData(PurchaseOrderRequest transactionList){
        purchaseOrderValidation.purchaseOrderValidation(transactionList);

        PurchaseOrder trns = PurchaseOrder.builder()
                            .customerId(transactionList.getCustomerId())
                            .purchaseOrderTime(transactionList.getPurchaseOrderTime())
                            .quantity(transactionList.getQuantity())
                            .productCode(transactionList.getProductCode())
                            .build();
        return  transactionRepository.save(trns);

    }
}
