package ru.otus.interpreter;

import ru.otus.interpreter.command.Command;
import ru.otus.interpreter.command.ExpressionImpl;
import ru.otus.interpreter.command.OrderCommand;
import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.Order;

import java.util.List;

public class StartApp {

    private final List<GameObject> objects;
    private final List<Order> orders;
    private final Command command;

    public StartApp(List<GameObject> objects,
                    List<Order> orders,
                    Command command,
                    Action action) {
        this.objects = objects;
        this.orders = orders;
        this.command = new OrderCommand(new ExpressionImpl());
    }

    public void start() {
        createOrder();
        executeActions();
    }

    private void createOrder() {
        for (Order order : orders) {
            command.execute(order);
        }
    }

    private void executeActions() {
        for (GameObject gameObject : objects) {
            // вытащили настройки игры
            GameSetting gameSetting = gameObject.getGameSetting();
            // вытащили действия, которые нужно применить
            List<Action> actions = gameObject.getActions();
            // проходимся по действиям, передавая настройки
            for (Action action : actions) {
                action.doAction(gameSetting);
            }
        }

    }
}
