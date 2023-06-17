package ru.otus.interpreter.command;

import ru.otus.interpreter.exception.DeprecateOrderException;
import ru.otus.interpreter.model.Order;

public interface Command {

    void execute(Order order) throws DeprecateOrderException;

}
