package ru.otus.interpreter.model;

public class InterpretExpression {

    private String id;
    private String action;
    private String fuel;
    private String consumption;
    private String velocityX;
    private String velocityY;
    private String positionX;
    private String positionY;
    private String direction;
    private String directionsNumber;
    private String angularVelocity;

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(String velocityX) {
        this.velocityX = velocityX;
    }

    public String getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(String velocityY) {
        this.velocityY = velocityY;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirectionsNumber() {
        return directionsNumber;
    }

    public void setDirectionsNumber(String directionsNumber) {
        this.directionsNumber = directionsNumber;
    }

    public String getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(String angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public InterpretExpression(String id, String action, String fuel, String consumption, String velocityX, String velocityY, String positionX, String positionY, String direction, String directionsNumber, String angularVelocity) {
        this.id = id;
        this.action = action;
        this.fuel = fuel;
        this.consumption = consumption;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        this.directionsNumber = directionsNumber;
        this.angularVelocity = angularVelocity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InterpretExpression() {
    }
}
