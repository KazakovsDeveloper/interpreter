package ru.otus.interpreter.IoC;

import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.GameSetting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IoC {

    private static final Map<String, GameObject> gameObjects = new HashMap<>();
    private static final Map<String, Action> actions = new HashMap<>();

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

    public static void resolve(String id, String action, GameSetting intrpretGameSetting) {
        // по id вычислить игровой объект
        GameObject gameObjectById = getGameObjectById(id);
        // достать действие
        Action actionById = getActionById(action);
        // добавить действие в лист действий объекта
        List<Action> actions = gameObjectById.getActions();
        actions.add(actionById);
        gameObjectById.setGameSetting(intrpretGameSetting);
    }
}
