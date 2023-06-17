package ru.otus.interpreter.mapper;

import ru.otus.interpreter.model.Direction;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.InterpretExpression;
import ru.otus.interpreter.model.Vector;

import static java.util.Objects.isNull;
import static ru.otus.interpreter.utils.Utils.isNullOrBlank;

public class Mapper {

    public static GameSetting mapGameObject(InterpretExpression interpretExpression) {
        if (isNull(interpretExpression)) {
            return null;
        }
        GameSetting gameSetting = new GameSetting();
        if (!isNullOrBlank(interpretExpression.getFuel())) {
            gameSetting.setFuel(Double.parseDouble(interpretExpression.getFuel()));
        }
        if (!isNullOrBlank(interpretExpression.getConsumption())) {
            gameSetting.setConsumption(Double.parseDouble(interpretExpression.getConsumption()));
        }
        if (!isNullOrBlank(interpretExpression.getVelocityX()) && isNullOrBlank(interpretExpression.getVelocityY())) {
            gameSetting.setVelocity(new Vector(Integer.parseInt(interpretExpression.getVelocityX()),
                    Integer.parseInt(interpretExpression.getVelocityY())));
        }
        if (!isNullOrBlank(interpretExpression.getPositionX()) && isNullOrBlank(interpretExpression.getPositionY())) {
            gameSetting.setPosition(new Vector(Integer.parseInt(interpretExpression.getPositionX()),
                    Integer.parseInt(interpretExpression.getPositionY())));
        }
        if (!isNullOrBlank(interpretExpression.getDirection())) {
            gameSetting.setDirection(new Direction(Integer.parseInt(interpretExpression.getDirection())));
        }
        if (!isNullOrBlank(interpretExpression.getDirectionsNumber())) {
            gameSetting.setDirectionsNumber(Integer.parseInt(interpretExpression.getDirectionsNumber()));
        }
        if (!isNullOrBlank(interpretExpression.getAngularVelocity())) {
            gameSetting.setAngularVelocity(Integer.parseInt(interpretExpression.getAngularVelocity()));
        }
        return gameSetting;
    }

}
