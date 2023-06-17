package ru.otus.interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.interpreter.IoC.IoC;
import ru.otus.interpreter.action.Action;
import ru.otus.interpreter.action.ShootAction;
import ru.otus.interpreter.action.StartAction;
import ru.otus.interpreter.action.StopAction;
import ru.otus.interpreter.exception.DeprecateOrderException;
import ru.otus.interpreter.model.GameObject;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.Gamer;
import ru.otus.interpreter.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProcessingOrdersTest {

    private ProcessingOrders processingOrders;
    private ProcessingOrders processingOrders2;
    private ProcessingOrders processingOrders3;
    private ProcessingOrders processingOrders4;
    private ScheduledExecutorService scheduler;

    @BeforeEach
    public void init() {
        scheduler = Executors.newScheduledThreadPool(1);
        Map<String, Map<Action, GameSetting>> settingMap = new HashMap<>();
        GameObject gameObject = new GameObject("1234", settingMap);
        GameObject gameObject2 = new GameObject("5678", settingMap);
        List<GameObject> objects = new ArrayList<>();
        objects.add(gameObject);
        objects.add(gameObject2);
        Order order1 = new Order(
                "id:1234;action:move;fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8");
        Order order2 = new Order("id:5678;action:shoot");
        processingOrders = new ProcessingOrders(objects, scheduler);
        processingOrders.addToOrders(order1);
        processingOrders.addToOrders(order2);
        IoC.addGameObjectToIoC("1234", gameObject);
        IoC.addGameObjectToIoC("5678", gameObject2);
        IoC.addActionsToIoC("move", new StartAction());
        IoC.addActionsToIoC("shoot", new ShootAction());
        IoC.addActionsToIoC("stop", new StopAction());
        List<GameObject> emptyGameObjects = new ArrayList<>();
        processingOrders2 = new ProcessingOrders(objects, scheduler);
        processingOrders3 = new ProcessingOrders(emptyGameObjects, scheduler);
        Gamer gamer = new Gamer("890", List.of("1234", "5678"));
        IoC.addOrderToIoC(order1, gamer);
        IoC.addGamerToIoC("890", gamer);
        processingOrders4 = new ProcessingOrders(objects, scheduler);
        processingOrders4.addToOrders(order1);
        Gamer gamer2 = new Gamer("123", List.of("098"));
        IoC.addOrderToIoC(order1, gamer2);
        IoC.addGamerToIoC("123", gamer2);
    }

    @Test
    @DisplayName("приложение обрабатывает приказы")
    public void processingOrdersTestSuccess() throws InterruptedException {
        // Запуск метода через 2 секунды
        scheduler.schedule(() -> {
            try {
                processingOrders.start();
            } catch (DeprecateOrderException e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.SECONDS);

        Thread.sleep(4000);

        scheduler.shutdown();
    }

    @Test
    @DisplayName("приложение бросает исключение, если приказов нет")
    public void processingOrdersTestThrowExceptionIfNoOrders() throws InterruptedException {
        scheduler.schedule(() -> {
            assertThrows(RuntimeException.class, () -> processingOrders2.start());
        }, 2, TimeUnit.SECONDS);

        Thread.sleep(4000);

        scheduler.shutdown();
    }

    @Test
    @DisplayName("приложение бросает исключение, если игровых объектов нет")
    public void processingOrdersTestThrowExceptionIfNoGameObjects() throws InterruptedException {
        scheduler.schedule(() -> {
            assertThrows(RuntimeException.class, () -> processingOrders3.start());
        }, 2, TimeUnit.SECONDS);

        Thread.sleep(4000);

        scheduler.shutdown();
    }


    @Test
    @DisplayName("приложение бросает исключение, т.к. игровой объект не принадлежит игроку")
    public void processingOrdersTestDeleteOrder() throws InterruptedException {
        scheduler.schedule(() -> {
            assertThrows(DeprecateOrderException.class, () -> processingOrders4.start());
        }, 2, TimeUnit.SECONDS);

        Thread.sleep(4000);

        scheduler.shutdown();
    }

}