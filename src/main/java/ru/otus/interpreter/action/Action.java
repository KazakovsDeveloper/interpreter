package ru.otus.interpreter.action;

import ru.otus.interpreter.model.GameSetting;

/**
 * действие, которое выполняет игровой объект
 */
public interface Action {

    void doAction(GameSetting gameSetting);

}
