package ru.otus.interpreter.action;

import ru.otus.interpreter.model.GameSetting;

public class ShootAction implements Action {

    @Override
    public void doAction(GameSetting gameSetting) {
        System.out.println("объект стреляет");
    }
}
