package ru.otus.interpreter.model;

public class SettingsTemplate {

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
}
