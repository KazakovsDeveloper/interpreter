package ru.otus.interpreter.action;

import ru.otus.interpreter.model.GameSetting;

public class StopAction implements Action {

    @Override
    public void doAction(GameSetting gameSetting) {
        System.out.println("объект остановлен");
    }
}
