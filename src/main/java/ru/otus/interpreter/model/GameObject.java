package ru.otus.interpreter.model;

import ru.otus.interpreter.action.Action;

import java.util.HashMap;
import java.util.Map;

public class GameObject {

    private String id;

    private Map<String, Map<Action, GameSetting>> settingMap;

    public GameObject() {
        this.settingMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToSettingMap(String id, Action action, GameSetting gameSetting) {
        settingMap.put(id, Map.of(action, gameSetting));
    }

    public void setSettingMap(Map<String, Map<Action, GameSetting>> settingMap) {
        this.settingMap = settingMap;
    }

    public GameObject(String id, Map<String, Map<Action, GameSetting>> settingMap) {
        this.id = id;
        this.settingMap = settingMap;
    }

    public Map<String, Map<Action, GameSetting>> getSettingMap() {
        return settingMap;
    }
}
