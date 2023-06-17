package ru.otus.interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.model.Gamer;
import ru.otus.interpreter.model.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderSenderTest {

    private OrderSender orderSender;
    private Gamer gamer;
    private Order order;

    @BeforeEach
    public void init() {
        ProcessingOrders processingOrders = mock(ProcessingOrders.class);
        orderSender = new OrderSender(processingOrders);
        doNothing().when(processingOrders).addToOrders(any());
        gamer = new Gamer("id", List.of("123"));
        order = new Order("id:12;action:shoot");
        IoC.addGamerToIoC("id", gamer);
    }

    @Test
    @DisplayName("проверяем, что можно найти игрока по приказу")
    public void sendOrderTestSuccess() {
        orderSender.sendOrder("id", order);
        assertEquals(gamer, IoC.getGamerByOrder(order));
    }
}