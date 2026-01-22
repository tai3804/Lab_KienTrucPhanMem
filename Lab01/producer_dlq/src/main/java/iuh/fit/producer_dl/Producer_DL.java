package iuh.fit.producer_dl;

import iuh.fit.model.Request;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Producer_DL {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody Request request) {

        String message = request.getMessage();
        String orderId = request.getOrderId();

        if (message == null || message.isEmpty() ||  orderId == null ||  orderId.isEmpty()) {
            return ResponseEntity.status(400).body("message or orderId is required");
        }

        Request request1 = Request.builder()
                .message(message)
                .orderId(orderId)
                .build();

        System.out.println("========= Producer =========");
        System.out.println("Sending Order ID: " + orderId);
        System.out.println("Sending Message: " + message);
        System.out.println("=====================================");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        rabbitTemplate.convertAndSend("", "orders_queue", request1);

        return ResponseEntity.ok("Message sent successfully: " +  message);
    }
}
