package ru.otus.interpreter.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.interpreter.model.InterpretExpression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionImplTest {

    private ExpressionImpl expression;
    private String orderParameters;
    private String emptyOrderParameters;
    private InterpretExpression expected;

    @BeforeEach
    public void init() {
        expression = new ExpressionImpl();
        orderParameters =
                "id:12;action:move;fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8";
        expected = new InterpretExpression("12", "move", "2", "7", "3", "9", "7", "8", "5", "7", "8");
        emptyOrderParameters = "";
    }

    @Test
    @DisplayName("расскладывает строку со значениями в сущность")
    public void interpretTestSuccess() {
        InterpretExpression interpret = expression.interpret(orderParameters);
        assertThat(interpret).isEqualToComparingFieldByField(expected);
    }

    @Test
    @DisplayName("выкидывает исключение, если строка пустая или null")
    public void interpretTestThrowException() {
       assertThrows(RuntimeException.class, () -> expression.interpret(emptyOrderParameters));
    }

}