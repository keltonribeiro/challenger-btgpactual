package com.head.btgpactual.challenger.listener;

import com.head.btgpactual.challenger.dto.OrderCreatedEvent;
import com.head.btgpactual.challenger.service.OrderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.head.btgpactual.challenger.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@AllArgsConstructor
@Component
public class OrderCreatedListener {
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);
    private final OrderService orderService;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        logger.info("Message consumed: {} ", message);
        orderService.save(message.getPayload()) ;
    }
}
