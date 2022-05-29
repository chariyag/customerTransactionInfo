package com.tab.purchase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tab.purchase.entitiy.PurchaseOrder;
import com.tab.purchase.model.PurchaseOrderRequest;
import com.tab.purchase.service.PurchaseOrderHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchaseOrderController.class)
class PurchaseOrderControllerTest {

    @Test
    void salesTransaction() {
    }



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PurchaseOrderHandler purchaseOrderHandler;


    @Test
    public void salesTransaction_shouldReturn_200_When_service_request_success() throws Exception {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseOrderId(1L);
        purchaseOrder.setPurchaseOrderTime("2022-05-30 14:36");
        purchaseOrder.setProductCode("PRODUCT_005");
        purchaseOrder.setQuantity(1);
        purchaseOrder.setCustomerId(1001l);

        String input_json = this.mapToJson(purchaseOrder);

        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/po/neworder")
                .accept((MediaType.APPLICATION_JSON))
                .content(input_json)
                .contentType(MediaType.APPLICATION_JSON);

        when(purchaseOrderHandler.saveProductTransactData(any(PurchaseOrderRequest.class))).thenReturn(purchaseOrder);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andExpect(jsonPath("$.productCode").value("PRODUCT_005"));
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}