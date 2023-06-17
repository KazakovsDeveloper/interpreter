package ru.otus.interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.action.ShootAction;
import ru.otus.interpreter.action.StartAction;
import ru.otus.interpreter.action.StopAction;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StartAppTest {

    private StartApp startApp;
    private List<Order> orders;
    private StartApp startApp2;
    private StartApp startApp3;

    @BeforeEach
    public void init() {
        Map<String, Map<Action, GameSetting>> settingMap = new HashMap<>();
        GameObject gameObject = new GameObject("1234", settingMap);
        GameObject gameObject2 = new GameObject("5678", settingMap);
        List<GameObject> objects = new ArrayList<>();
        objects.add(gameObject);
        objects.add(gameObject2);
        Order order1 = new Order(
                "id:1234;action:move;fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8");
        Order order2 = new Order("id:5678;action:shoot");
        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        startApp = new StartApp(objects, orders);
        IoC.addGameObjectToIoC("1234", gameObject);
        IoC.addGameObjectToIoC("5678", gameObject2);
        IoC.addActionsToIoC("move", new StartAction());
        IoC.addActionsToIoC("shoot", new ShootAction());
        IoC.addActionsToIoC("stop", new StopAction());
        List<Order> emptyList = new ArrayList<>();
        List<GameObject> emptyGameObjects = new ArrayList<>();
        startApp2 = new StartApp(objects, emptyList);
        startApp3 = new StartApp(emptyGameObjects, orders);
    }

    @Test
    @DisplayName("приложение обрабатывает приказы")
    public void startTestSuccess() {
        assertDoesNotThrow(() -> startApp.start());
        assertTrue(orders.isEmpty());
    }

    @Test
    @DisplayName("приложение бросает исключение, если приказов нет")
    public void startTestThrowExceptionIfNoOrders() {
        assertThrows(RuntimeException.class, () -> startApp2.start());
    }

    @Test
    @DisplayName("приложение бросает исключение, если игровых объектов нет")
    public void startTestThrowExceptionIfNoGameObjects() {
        assertThrows(RuntimeException.class, () -> startApp3.start());
    }

}