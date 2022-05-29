package com.tab.purchase.repository;

import com.tab.purchase.entitiy.PurchaseOrder;
import com.tab.purchase.entitiy.TotalCostPerCustomer;
import com.tab.purchase.entitiy.TotalCostPerProduct;
import com.tab.purchase.entitiy.TotalCostomerInAustralia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {

    @Query( value = "SELECT PURCHASE_ORDER.CUSTOMER_ID as customerId,SUM (PURCHASE_ORDER.QUANTITY * PRODUCT.COST) as totalCost" +
            "            FROM PURCHASE_ORDER ,PRODUCT " +
            "            where PURCHASE_ORDER.PRODUCT_CODE =PRODUCT.PRODUCT_CODE" +
            "            GROUP BY PURCHASE_ORDER.CUSTOMER_ID",
            nativeQuery = true)
    List<TotalCostPerCustomer> getPurchaseOrderDetails();


    @Query( value = "SELECT PRODUCT.PRODUCT_CODE as productCode ,SUM(PRODUCT.COST*PURCHASE_ORDER .QUANTITY ) as totalCost" +
            "    FROM PRODUCT,PURCHASE_ORDER" +
            "    WHERE PRODUCT.PRODUCT_CODE =PURCHASE_ORDER.PRODUCT_CODE" +
            "    GROUP BY PRODUCT.PRODUCT_CODE",
            nativeQuery = true)
    List<TotalCostPerProduct> getTotalCostOfProducts();


    @Query( value = " SELECT count(distinct(CUSTOMER.CUSTOMER_ID)) as customersInAustralia" +
            "   FROM PURCHASE_ORDER,CUSTOMER " +
            "  WHERE PURCHASE_ORDER.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID" +
            "    AND CUSTOMER.LOCATION ='Australia'",
            nativeQuery = true)
    TotalCostomerInAustralia getTotalSalesFromAustralia();

}
