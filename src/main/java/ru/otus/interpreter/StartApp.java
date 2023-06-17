package ru.otus.interpreter;

import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.command.Command;
import ru.otus.interpreter.command.ExpressionImpl;
import ru.otus.interpreter.command.OrderCommand;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.Order;

import java.util.List;

public class StartApp {

    private final List<GameObject> objects;
    private final List<Order> orders;
    private Command command;

    public StartApp(List<GameObject> objects,
                    List<Order> orders) {
        this.objects = objects;
        this.orders = orders;
        this.command = new OrderCommand(new ExpressionImpl());
    }

    public void start() {
        createOrder();
        executeActions();
    }

    private void createOrder() {
        if (orders.isEmpty()) {
            throw new RuntimeException("ни одного приказа не передано");
        }
        for (Order order : orders) {
            command.execute(order);
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
