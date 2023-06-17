package ru.otus.interpreter.model;

import java.util.ArrayList;
import java.util.List;

public class Gamer {

    private String id;

    private List<String> gameObjectsId;

    public Gamer(String id, List<String> gameObjectsId) {
        this.id = id;
        this.gameObjectsId = gameObjectsId;
    }

    public Gamer() {
        this.gameObjectsId = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGameObjectsId() {
        return gameObjectsId;
    }

    public void setGameObjectsId(List<String> gameObjectsId) {
        this.gameObjectsId = gameObjectsId;
    }
}
