package com.tab.purchase.service;

import com.tab.purchase.entitiy.Product;
import com.tab.purchase.exception.ProductNotFoundException;
import com.tab.purchase.exception.TransactionDateFormatError;
import com.tab.purchase.model.PurchaseOrderRequest;
import com.tab.purchase.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Data @Slf4j
@AllArgsConstructor
public class PurchaseOrderValidation {

    @Autowired
    private final ProductRepository poRepository;


    public void purchaseOrderValidation(PurchaseOrderRequest transactionList){
     dateValidation(transactionList.getPurchaseOrderTime());
     productValidation(transactionList.getQuantity(),transactionList.getProductCode());

    }

    //Date must not be in the past
    private void dateValidation(String transactionTime) {
        if (transactionTime.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")) {
            Period period = Period.between(LocalDate.now(), LocalDate.parse(transactionTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            if (period.isNegative()) {
                log.error("TransactionTime. requested ::: "+transactionTime);
                throw new TransactionDateFormatError("400", "Transaction date should not be passed");
            }
        } else {
            log.error("Invalid transaction date format. requested ::: "+transactionTime);
            throw new TransactionDateFormatError("400", "Invalid transaction date format");
        }

    }

    //Total cost of transaction must not exceed 5000
    //Product must be active

    private void productValidation(int requstQty, String productCode) {

            Optional<Product> productOptional = poRepository.findById(productCode);
            if (productOptional.isPresent()) {
                if (productOptional.get().getStatus().equals("Active")) {
                    BigDecimal itemCost = productOptional.get().getCost().multiply(BigDecimal.valueOf(requstQty));
                    if (requstQty > 0) {
                        if (itemCost.compareTo(BigDecimal.valueOf(5000.00)) == 1) {
                            log.error("Total cost of transaction exceed 5000. requested ::: "+productOptional);
                            throw new ProductNotFoundException("400", "Total cost of transaction exceed 5000");
                        }
                    } else {
                        log.error("Requested qeantity error. requested ::: "+productOptional);
                        throw new ProductNotFoundException("400", "Requested qeantity error");
                    }
                } else {
                    log.error("Requested product is not Active. requested :::  "+productOptional);
                    throw new ProductNotFoundException("400", "Requested product is not Active");
                }
            } else {
                log.error("Product Not Found Exception. requested ::: "+productOptional);
                throw new ProductNotFoundException("400", "Product Not Found Exception");
            }



    }

}
