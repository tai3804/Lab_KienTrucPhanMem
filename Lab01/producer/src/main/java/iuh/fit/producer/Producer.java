package iuh.fit.producer;

import iuh.fit.model.Request;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public String send(@RequestBody Request request) {

        String message = request.getMessage();

        System.out.println("========= Producer =========");
        System.out.println("Sending Message: " + message);
        System.out.println("=====================================");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        rabbitTemplate.convertAndSend("orders_queue", message);

        return message;
    }
}
