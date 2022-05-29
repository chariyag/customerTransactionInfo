package com.tab.purchase.controller;

import com.tab.purchase.entitiy.PurchaseOrder;
import com.tab.purchase.model.PurchaseOrderRequest;
import com.tab.purchase.service.PurchaseOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/po")
public class PurchaseOrderController {

  private final  PurchaseOrderHandler purchaseOrderHandler;

    @PostMapping(value = "/neworder" )
    @Operation(summary = "Place purchase Order" )
    public PurchaseOrder salesTransaction(@RequestBody PurchaseOrderRequest purchaseOrderRequest){
        return   purchaseOrderHandler.saveProductTransactData(purchaseOrderRequest);

    }
}
