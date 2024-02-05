package vn.cloud.invoiceservice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceClient {

    private final RestTemplate restTemplate;

    public Order getOrder(Long id) {
        return this.restTemplate
                .getForObject("/orders/" + id, Order.class);
    }

}
