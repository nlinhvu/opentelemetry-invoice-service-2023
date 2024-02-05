package vn.cloud.invoiceservice.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.cloud.invoiceservice.order.Order;
import vn.cloud.invoiceservice.order.OrderServiceClient;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController {

    private final OrderServiceClient orderServiceClient;

    @GetMapping(value = "/{id}")
    public Invoice getInvoice(@PathVariable Long id) {
        Order order = this.orderServiceClient.getOrder(id);
        log.info("Invoice saved!");
        return new Invoice(id, order.orderDate(), order.totalAmount());
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<String> handleConflict(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
