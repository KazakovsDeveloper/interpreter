package ru.otus.interpreter.command;

import ru.otus.interpreter.model.GameSetting;

public interface Expression {

    GameSetting intrpret(String orderParameters);

}
