package com.codecool.bread.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException() {
        super("Invoice not found");
    }
}
