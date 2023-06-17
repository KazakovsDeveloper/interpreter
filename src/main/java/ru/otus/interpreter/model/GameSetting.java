package ru.otus.interpreter.model;

/**
 * настройки для действий Action
 */
public class GameSetting {

    private double fuel;

    private double consumption;

    private Vector velocity;

    private Vector position;

    private Direction direction;

    private int directionsNumber;

    private int angularVelocity;

    public GameSetting(double fuel, double consumption, Vector velocity, Vector position, Direction direction, int directionsNumber, int angularVelocity) {
        this.fuel = fuel;
        this.consumption = consumption;
        this.velocity = velocity;
        this.position = position;
        this.direction = direction;
        this.directionsNumber = directionsNumber;
        this.angularVelocity = angularVelocity;
    }

    public GameSetting() {
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getDirectionsNumber() {
        return directionsNumber;
    }

    public void setDirectionsNumber(int directionsNumber) {
        this.directionsNumber = directionsNumber;
    }

    public int getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(int angularVelocity) {
        this.angularVelocity = angularVelocity;
    }
}
