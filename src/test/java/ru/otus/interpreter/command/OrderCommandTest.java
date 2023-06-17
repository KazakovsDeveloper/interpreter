package ru.otus.interpreter.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.action.ShootAction;
import ru.otus.interpreter.action.StartAction;
import ru.otus.interpreter.action.StopAction;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.InterpretExpression;
import ru.otus.interpreter.model.Order;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderCommandTest {

    private OrderCommand orderCommand;
    private Expression expression;
    private Order order1;
    private Order order2;
    private Order order3;
    private Order emptyOrder;
    private GameObject tank;

    @BeforeEach
    public void init() {
        String orders1 =
                "id:1234;action:move;fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8";
        String orders2 =
                "id:1234;action:shoot";
        String orders3 = "id:1234;action:stop";
        String empty = "id: ;";
        order1 = new Order(orders1);
        order2 = new Order(orders2);
        order3 = new Order(orders3);
        emptyOrder = new Order(empty);
        InterpretExpression interpretExpression1 =
                new InterpretExpression("1234", "move", "2", "7", "3", "9", "7", "8", "5", "7", "8");
        InterpretExpression interpretExpression2 =
                new InterpretExpression("1234", "shoot", null, null, null, null, null, null, null, null, null);
        InterpretExpression interpretExpression3 =
                new InterpretExpression("1234", "stop", null, null, null, null, null, null, null, null, null);
        InterpretExpression emptyInter =
                new InterpretExpression(" ", null, null, null, null, null, null, null, null, null, null);
        expression = mock(ExpressionImpl.class);
        orderCommand = new OrderCommand(expression);
        Action start = new StartAction();
        Action stop = new StopAction();
        Action shoot = new ShootAction();
        IoC.addActionsToIoC("move", start);
        IoC.addActionsToIoC("stop", stop);
        IoC.addActionsToIoC("shoot", shoot);
        tank = new GameObject("1234", new ArrayList<>(), null);
        IoC.addGameObjectToIoC("1234", tank);
        when(expression.interpret(orders1)).thenReturn(interpretExpression1);
        when(expression.interpret(orders2)).thenReturn(interpretExpression2);
        when(expression.interpret(orders3)).thenReturn(interpretExpression3);
        when(expression.interpret(empty)).thenReturn(emptyInter);
    }

    @Test
    @DisplayName("сложить необходимое действие от входящего приказа в настройки")
    public void orderCommandTestSuccessAddActionAndGameSetting() {
        orderCommand.execute(order1);
        assertEquals(2.0, tank.getGameSetting().getFuel());
        assertEquals("StartAction", tank.getActions().get(0).getClass().getSimpleName());
    }

    @Test
    @DisplayName("уметь обрабатывать приказы на старт/стоп/выстрел")
    public void orderCommandTestSuccess() {
        orderCommand.execute(order1);
        orderCommand.execute(order2);
        orderCommand.execute(order3);
        assertEquals("StartAction", tank.getActions().get(0).getClass().getSimpleName());
        assertEquals("ShootAction", tank.getActions().get(1).getClass().getSimpleName());
        assertEquals("StopAction", tank.getActions().get(2).getClass().getSimpleName());
    }

    @Test
    @DisplayName("выбросить исключение, если id пустой")
    public void orderCommandTestThrowException() {
        assertThrows(RuntimeException.class, () -> orderCommand.execute(emptyOrder));
    }

}