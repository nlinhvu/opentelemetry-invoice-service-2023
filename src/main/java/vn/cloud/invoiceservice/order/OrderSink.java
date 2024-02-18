package vn.cloud.invoiceservice.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderSink {

    @KafkaListener(id = "invoice-service", topics = "public.order.orders.v1")
    public void listen(OrderPlacedEvent event) {
        log.info("Received: " + event);
        log.info("Invoice generating!");
    }
}
