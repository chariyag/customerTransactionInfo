package com.tab.purchase.controller;

import com.tab.purchase.entitiy.TotalCostPerCustomer;
import com.tab.purchase.entitiy.TotalCostPerProduct;
import com.tab.purchase.entitiy.TotalCostomerInAustralia;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportEndPointController {

    private final com.tab.purchase.service.ReportEndPoint reportEndPoint;

    @GetMapping("/totalcustomercost")
    @Operation(summary = "Get total cost per customer")
    public List<TotalCostPerCustomer> totalCostPerCustomers(){
        return reportEndPoint.totalCostPerCustomers();
    }

    @GetMapping("/totalproductcost")
    @Operation(summary = "Get total cost per product")
    private List<TotalCostPerProduct> getTotalCostOfProducts(){
        return reportEndPoint.getTotalCostOfProducts();
    }

    @GetMapping("/ausisalescount")
    @Operation(summary = "Get total number customers in Australia")
    private TotalCostomerInAustralia getTotalSalesFromAus(){
        return reportEndPoint.getTotalSalesFromAustralia();
    }


}
