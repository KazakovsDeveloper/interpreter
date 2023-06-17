package ru.otus.interpreter.command;

import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.mapper.Mapper;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.InterpretExpression;
import ru.otus.interpreter.model.Order;

import static ru.otus.interpreter.utils.Utils.isNullOrBlank;

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
        InterpretExpression interpret = expression.interpret(orderParameters);
        String id = interpret.getId();
        String action = interpret.getAction();
        if (isNullOrBlank(id) || isNullOrBlank(action)) {
            throw new RuntimeException("id или action для игрового объекта не переданы");
        }
        GameSetting gameSetting = Mapper.mapGameObject(interpret);
        // передаем в IoC параметры для совершения действия
        IoC.resolve(id, action, gameSetting);
    }
}
