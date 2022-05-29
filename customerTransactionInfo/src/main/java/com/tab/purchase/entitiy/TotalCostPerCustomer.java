package com.tab.purchase.entitiy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public interface TotalCostPerCustomer {
     Long getCustomerId();
     BigDecimal getTotalCost();
}
