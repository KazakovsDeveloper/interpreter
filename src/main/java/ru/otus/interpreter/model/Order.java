package ru.otus.interpreter.model;

/**
 * Приказ содержит "id", "action", "параметры приказа"
 * формат параметров приказа:
 * "fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8"
 */
public class Order {

    private String id;

    private String action;

    private String orderParameters;

    public Order(String id, String action, String orderParameters) {
        this.id = id;
        this.action = action;
        this.orderParameters = orderParameters;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getOrderParameters() {
        return orderParameters;
    }
}
