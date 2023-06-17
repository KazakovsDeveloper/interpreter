package ru.otus.interpreter.command;

import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.exception.DeprecateOrderException;
import ru.otus.interpreter.mapper.Mapper;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.InterpretExpression;
import ru.otus.interpreter.model.Order;

import static ru.otus.interpreter.utils.Utils.checkGamerAndOrder;
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
    public void execute(Order order) throws DeprecateOrderException {
        // необходимо интерпретировать параметры для дальнейшей работы в IoC
        String orderParameters = order.getOrderParameters();
        InterpretExpression interpret = expression.interpret(orderParameters);

        String gameObjectId = interpret.getId();
        String actionId = interpret.getAction();
        if (isNullOrBlank(gameObjectId) || isNullOrBlank(actionId)) {
            throw new RuntimeException("id или action для игрового объекта не переданы");
        }

        // проверить приказ
        checkGamerAndOrder(gameObjectId, order);
        // смаппить интерпретированный приказ в настройки
        GameSetting gameSetting = Mapper.mapGameObject(interpret);
        // передаем в IoC параметры для совершения действия
        IoC.resolve(gameObjectId, actionId, gameSetting);
    }
}
