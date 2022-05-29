package com.tab.purchase.service;

import com.tab.purchase.entitiy.PurchaseOrder;
import com.tab.purchase.exception.ProductNotFoundException;
import com.tab.purchase.model.PurchaseOrderRequest;
import com.tab.purchase.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderHandlerTest {

    @InjectMocks
    PurchaseOrderHandler purchaseOrderHandler ;

    @Mock
    PurchaseOrderRepository transactionRepository;

    @Mock
    PurchaseOrderValidation orderValidation;

    @Captor
    ArgumentCaptor<PurchaseOrder> purchaseOrderArgumentCaptor;

    @Test
    public void saveProductTransactData_should_save_purchase_request_when_validation_is_sucess(){
        //given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-30 14:36")
                .productCode("PRODUCT_005")
                .customerId(1001l)
                .quantity(3)
                .build();
        //then
        purchaseOrderHandler.saveProductTransactData(purchaseOrderRequest);
        verify(transactionRepository).save(purchaseOrderArgumentCaptor.capture());
        PurchaseOrder purchaseOrder = purchaseOrderArgumentCaptor.getValue();
        Assertions.assertEquals(purchaseOrder.getProductCode(),purchaseOrderRequest.getProductCode());
    }

    @Test
    public void saveProductTransactData_should_throw_exception_when_validation_is_failed(){
        //given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-30 14:36")
                .productCode("PRODUCT_005")
                .customerId(1001l)
                .quantity(3)
                .build();
        //when
        doThrow(new ProductNotFoundException()).when(orderValidation).purchaseOrderValidation(any(PurchaseOrderRequest.class));
        //then
        Assertions.assertThrows(ProductNotFoundException.class,()->purchaseOrderHandler.saveProductTransactData(purchaseOrderRequest));

    }







}