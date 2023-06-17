package ru.otus.interpreter.model;

import ru.otus.interpreter.action.Action;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private String id;

    private List<Action> actions;

    private GameSetting gameSetting;

    public GameObject() {
        this.actions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public GameSetting getGameSetting() {
        return gameSetting;
    }

    public void setGameSetting(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public GameObject(String id, List<Action> actions, GameSetting gameSetting) {
        this.id = id;
        this.actions = actions;
        this.gameSetting = gameSetting;
    }
}
