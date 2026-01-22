package iuh.fit.consumer_dl;

import com.rabbitmq.client.Channel;
import iuh.fit.model.Request;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer_DL {
    @RabbitListener(queues = "orders_queue")
    public void consume(Request request, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        String message = request.getMessage();
        String orderId = request.getOrderId();

        try {
            System.out.println("========= Received =========");
            System.out.println("Received: " + deliveryTag);
            System.out.println("Processing order with id: " +  orderId);
            System.out.println("Processing order with message: " + message);
            System.out.println("=====================================");

            Thread.sleep(1000);

            if (message.contains("fail")) {
                throw new RuntimeException("Failed and sent to DEAD LETTER QUEUE!");
            }

            channel.basicAck(deliveryTag, false);
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            System.err.println("Sent to DEAD LETTER QUEUE!");
            channel.basicNack(deliveryTag, false, false);
        }
    }

    @RabbitListener(queues = "orders_dlq")
    public void processDeadLetter(Request request, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        System.out.println("========= DEAD LETTER QUEUE =========");
        System.out.println("Received failed order with id: " + request.getOrderId());
        System.out.println("Received failed order with message: " + request.getMessage());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        channel.basicAck(deliveryTag, false);
        System.out.println("Deleted failed order with id: " + request.getOrderId());
        System.out.println("=====================================");
    }
}
