package com.codecool.bread.service.simple;

import com.codecool.bread.exception.InvoiceNotFoundException;
import com.codecool.bread.model.Invoice;
import com.codecool.bread.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceServiceImpl extends AbstractService implements InvoiceService {

    @Override
    public Invoice getById(int invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isPresent()) {
            return invoice.get();
        } else {
            throw new InvoiceNotFoundException();
        }
    }
}
