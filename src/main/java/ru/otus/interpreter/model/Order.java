package ru.otus.interpreter.model;

/**
 * Приказ содержит "id", "action", "параметры приказа"
 * формат приказа:
 * "id:15375;action:move;fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8"
 */
public class Order {

    private String orderParameters;

    public Order(String orderParameters) {
        this.orderParameters = orderParameters;
    }

    public Order() {
    }

    public String getOrderParameters() {
        return orderParameters;
    }
}
