package com.tab.purchase.service;

import com.tab.purchase.entitiy.Product;
import com.tab.purchase.exception.ProductNotFoundException;
import com.tab.purchase.exception.TransactionDateFormatError;
import com.tab.purchase.model.PurchaseOrderRequest;
import com.tab.purchase.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderValidationTest {

    @InjectMocks
    PurchaseOrderValidation purchaseOrderValidation;

    @Mock
    ProductRepository poRepository;



    // date validation private dateValidation method test
    @Test
    public void dateValidation_should_throw_thransaction_date_pass_error_when_date_is_old_date(){
        // given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-20 14:36")
                .productCode("PRODUCT_005")
                .customerId(1001l)
                .quantity(3)
                .build();
        // when
        //then
       TransactionDateFormatError transactionDateFormatError=
               Assertions.assertThrows(TransactionDateFormatError.class,()->purchaseOrderValidation.purchaseOrderValidation(purchaseOrderRequest));
        Assertions.assertEquals(transactionDateFormatError.getCode(),"400");

    }
    @Test
    public void dateValidation_should_throw_thransaction_date_pass_error_when_date_is_incorrect_format(){
        // given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-20 14:346")
                .productCode("PRODUCT_005")
                .customerId(1001l)
                .quantity(3)
                .build();
        // when
        //then
        TransactionDateFormatError transactionDateFormatError=
                Assertions.assertThrows(TransactionDateFormatError.class,()->purchaseOrderValidation.purchaseOrderValidation(purchaseOrderRequest));
        Assertions.assertEquals(transactionDateFormatError.getMessage(),"Invalid transaction date format");

    }

    // date validation private product validation method test
    @Test
   public void productValidation_should_throw_product_not_found_error_when_pass_incorrect_product() {
       //given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-30 14:36")
                .productCode("PRODUCT_005")
                .customerId(1001l)
                .quantity(3)
                .build();
        Product product = new Product();
        Optional<Product> productOptional=Optional.empty();
        //when
        when(poRepository.findById(anyString())).thenReturn(productOptional);
       //then
        ProductNotFoundException productNotFoundException =
                Assertions.assertThrows(ProductNotFoundException.class,()->purchaseOrderValidation.purchaseOrderValidation(purchaseOrderRequest));
        Assertions.assertEquals(productNotFoundException.getMessage(),"Product Not Found Exception");
    }

    @Test
    public void productValidation_should_throw_product_not_active_error_when_pass_inactive_product() {
        //given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-30 14:36")
                .productCode("PRODUCT_003")
                .customerId(1001l)
                .quantity(3)
                .build();
        Product product = new Product();
        product.setProductCode("PRODUCT_003");
        product.setStatus("Inactive");
        Optional<Product> productOptional=Optional.of(product);
        //when
        when(poRepository.findById(anyString())).thenReturn(productOptional);
        //then
        ProductNotFoundException productNotFoundException =
                Assertions.assertThrows(ProductNotFoundException.class,()->purchaseOrderValidation.purchaseOrderValidation(purchaseOrderRequest));
        Assertions.assertEquals(productNotFoundException.getMessage(),"Requested product is not Active");
    }

    @Test
    public void productValidation_should_throw_product_request_qty_should_have_a_positive_value() {
        //given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-30 14:36")
                .productCode("PRODUCT_003")
                .customerId(1001l)
                .quantity(0)
                .build();
        Product product = new Product();
        product.setProductCode("PRODUCT_005");
        product.setStatus("Active");
        product.setCost(BigDecimal.valueOf(100));
           Optional<Product> productOptional=Optional.of(product);
        //when
        when(poRepository.findById(anyString())).thenReturn(productOptional);
        //then
        ProductNotFoundException productNotFoundException =
                Assertions.assertThrows(ProductNotFoundException.class,()->purchaseOrderValidation.purchaseOrderValidation(purchaseOrderRequest));
        Assertions.assertEquals(productNotFoundException.getMessage(),"Requested qeantity error");
    }



    @Test
    public void productValidation_should_throw_product_total_value_should_lessthan_500() {
        //given
        PurchaseOrderRequest purchaseOrderRequest = PurchaseOrderRequest.builder()
                .purchaseOrderTime("2022-05-30 14:36")
                .productCode("PRODUCT_003")
                .customerId(1001l)
                .quantity(50)
                .build();
        Product product = new Product();
        product.setProductCode("PRODUCT_005");
        product.setStatus("Active");
        product.setCost(BigDecimal.valueOf(200));
        Optional<Product> productOptional=Optional.of(product);
        //when
        when(poRepository.findById(anyString())).thenReturn(productOptional);
        //then
        ProductNotFoundException productNotFoundException =
                Assertions.assertThrows(ProductNotFoundException.class,()->purchaseOrderValidation.purchaseOrderValidation(purchaseOrderRequest));
        Assertions.assertEquals(productNotFoundException.getMessage(),"Total cost of transaction exceed 5000");
    }
}