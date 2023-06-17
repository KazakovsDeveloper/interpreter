package ru.otus.interpreter.command;

import ru.otus.interpreter.model.InterpretExpression;

public interface Expression {

    InterpretExpression interpret(String orderParameters);

}
