package ru.otus.interpreter.command;

import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.Order;

/**
 * Команда, которая получает на вход приказ и с помощью IoC выполняет необходимое действие.
 */
public class OrderCommand implements Command {

    private final Expression expression;

    public OrderCommand(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(Order order) {
        // необходимо интерпретировать параметры для дальнейшей работы в IoC
        String orderParameters = order.getOrderParameters();
        GameSetting intrpretGameSetting = expression.intrpret(orderParameters);
        // передаем в IoC параметры для совершения действия
        String id = order.getId();
        String action = order.getAction();
        IoC.resolve(id, action, intrpretGameSetting);
    }
}
