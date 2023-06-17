package ru.otus.interpreter.IoC;

import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.Gamer;
import ru.otus.interpreter.model.Order;
import ru.otus.interpreter.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class IoC {

    private static final Map<String, GameObject> gameObjects = new HashMap<>();
    private static final Map<String, Action> actions = new HashMap<>();
    private static final Map<String, Gamer> gamers = new HashMap<>();
    private static final Map<Order, Gamer> gamerOrders = new HashMap<>();

    public static void addGameObjectToIoC(String id, GameObject gameObject) {
        gameObjects.put(id, gameObject);
    }

    public static GameObject getGameObjectById(String id) {
        return gameObjects.get(id);
    }

    public static void addActionsToIoC(String id, Action action) {
        actions.put(id, action);
    }

    public static Action getActionById(String id) {
        return actions.get(id);
    }

    public static void addGamerToIoC(String id, Gamer gamer) {
        gamers.put(id, gamer);
    }

    public static Gamer getGamerById(String id) {
        return gamers.get(id);
    }

    public static void addOrderToIoC(Order order, Gamer gamer) {
        gamerOrders.put(order, gamer);
    }

    public static Gamer getGamerByOrder(Order order) {
        return gamerOrders.get(order);
    }

    public static void resolve(String id, String action, GameSetting interpretGameSetting) {
        // по id вычислить игровой объект
        GameObject gameObjectById = getGameObjectById(id);
        // достать действие
        Action actionById = getActionById(action);
        // добавить действие в лист действий объекта
        String randomId = Utils.createRandomId();
        gameObjectById.setToSettingMap(randomId, actionById, interpretGameSetting);
    }
}
