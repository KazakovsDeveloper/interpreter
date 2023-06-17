package ru.otus.interpreter.utils;

import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.exception.DeprecateOrderException;
import ru.otus.interpreter.model.Gamer;
import ru.otus.interpreter.model.Order;

import java.util.UUID;

import static java.util.Objects.isNull;

public class Utils {

    public static boolean isNullOrBlank(String s) {
        if (isNull(s)) {
            return true;
        } else return s.isBlank();
    }

    public static String createRandomId() {
        return UUID.randomUUID().toString();
    }


    /**
     * защита, которая не позволяет отдавать приказы чужим объектам
     */
    public static void checkGamerAndOrder(String gameObjectId, Order order) throws DeprecateOrderException {
        Gamer gamerByOrder = IoC.getGamerByOrder(order);
        if (!gamerByOrder.getGameObjectsId().contains(gameObjectId)) {
            throw new DeprecateOrderException("приказ " + order + " не принадлежит игроку " + gamerByOrder);
        } else {
            System.out.println("игровой объект принадлежит игроку, приказ будет выполнен");
        }
    }
}
