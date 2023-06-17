package ru.otus.interpreter;

import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.model.Gamer;
import ru.otus.interpreter.model.Order;

/**
 * класс отправляет приказ и id игрока
 */
public class OrderSender {

    private final ProcessingOrders processingOrders;

    public OrderSender(ProcessingOrders processingOrders) {
        this.processingOrders = processingOrders;
    }

    public void sendOrder(String gamerId, Order order) {
        // достали игрока
        Gamer gamerById = IoC.getGamerById(gamerId);
        // сложили игрока и приказ в контейнер
        IoC.addOrderToIoC(order, gamerById);
        processingOrders.addToOrders(order);
    }

}
