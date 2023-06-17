package ru.otus.interpreter;

import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.command.Command;
import ru.otus.interpreter.command.ExpressionImpl;
import ru.otus.interpreter.command.OrderCommand;
import ru.otus.interpreter.exception.DeprecateOrderException;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * класс раз в какое-то время будет запускать метод старт, обрабатывать приказы и действия
 */
public class ProcessingOrders {

    private final List<GameObject> objects;
    private final List<Order> orders;
    private Command command;
    private final ScheduledExecutorService scheduler;

    public ProcessingOrders(List<GameObject> objects,
                            ScheduledExecutorService scheduler) {
        this.objects = objects;
        this.orders = new ArrayList<>();
        this.command = new OrderCommand(new ExpressionImpl());
        this.scheduler = scheduler;
    }

    public void addToOrders(Order order) {
        orders.add(order);
    }

    public void start() throws DeprecateOrderException {
        createOrder();
        executeActions();
    }

    private void createOrder() throws DeprecateOrderException {
        if (orders.isEmpty()) {
            throw new RuntimeException("ни одного приказа не передано");
        }
        for (Order order : orders) {
            try {
                command.execute(order);
            } catch (DeprecateOrderException ex) {
                orders.remove(order);
                System.out.println("приказ удален");
            }
        }
        orders.clear();
    }

    private void executeActions() {
        if (objects.isEmpty()) {
            throw new RuntimeException("игровых объектов нет");
        }
        for (GameObject gameObject : objects) {
            // вытащили настройки игры
            gameObject.getSettingMap()
                    .values()
                    .forEach(map -> {
                        // проходимся по действиям, передавая настройки
                        map.forEach(Action::doAction);
                    });
        }
        objects.forEach(gameObject -> gameObject.getSettingMap().clear());
    }
}
