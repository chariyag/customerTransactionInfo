package com.tab.purchase.service;

import com.tab.purchase.entitiy.TotalCostPerCustomer;
import com.tab.purchase.entitiy.TotalCostPerProduct;
import com.tab.purchase.entitiy.TotalCostomerInAustralia;
import com.tab.purchase.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportEndPoint {
    // Total Cost of transactions per customer
    // Total cost of transactions per product
    // Number of transactions sold to customer from Australia

private final PurchaseOrderRepository purchaseOrderRepository;


public  List<TotalCostPerCustomer>     totalCostPerCustomers() {
    List<TotalCostPerCustomer>  totalCostPerCustomers = purchaseOrderRepository.getPurchaseOrderDetails();
      return totalCostPerCustomers;

}

    public List<TotalCostPerProduct> getTotalCostOfProducts() {
        List<TotalCostPerProduct> totalCostOfProducts = purchaseOrderRepository.getTotalCostOfProducts();
        return totalCostOfProducts;
    }


    public   TotalCostomerInAustralia getTotalSalesFromAustralia() {
        TotalCostomerInAustralia totalSalesFromAustralia = purchaseOrderRepository.getTotalSalesFromAustralia();
        return totalSalesFromAustralia;
    }
}
