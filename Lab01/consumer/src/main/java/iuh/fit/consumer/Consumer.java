package iuh.fit.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    @RabbitListener(queues = "orders_queue")
    public void consume(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String payload = new String(message.getBody());

        try {

            System.out.println("========= Consumer =========");
            System.out.println("Received: " + deliveryTag);
            System.out.println("Processing: " + payload);
            System.out.println("=====================================");

            Thread.sleep(1000);

            channel.basicAck(deliveryTag, false);
        }
        catch (Exception e){
            channel.basicNack(deliveryTag, false, true);
            System.out.println("Error: " + e.getMessage());
        }
    }
}
