package com.tab.purchase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDateFormatError extends RuntimeException{

    private String code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
