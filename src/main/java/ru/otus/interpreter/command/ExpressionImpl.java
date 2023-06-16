package ru.otus.interpreter.command;

import ru.otus.interpreter.model.Direction;
import ru.otus.interpreter.model.GameSetting;
import ru.otus.interpreter.model.SettingsTemplate;
import ru.otus.interpreter.model.Vector;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public class ExpressionImpl implements Expression {

    // "fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8"
    @Override
    public GameSetting intrpret(String orderParameters) {
        // массив типа [fuel:2, consumption:7 ...]
        String[] split = orderParameters.split(";");
        // пройтись по каждому элементу и разбить еще на элементы, лист массивов типа [fuel, 2]
        List<String[]> strings = Stream.of(split)
                .map(setting -> setting.split(":"))
                .toList();
        // перебрать массивы и записать в модель
        SettingsTemplate settingsTemplate = new SettingsTemplate();

        // Получение класса объекта
        Class<?> clazz = settingsTemplate.getClass();

        List<String> fieldNames = getFields(clazz);

        strings.stream()
                .map(Arrays::asList)
                .peek(list -> setValues(list, fieldNames, clazz))
                .forEach(System.out::println);

        return mapGameObject(settingsTemplate);
    }

    private List<String> getFields(Class<?> clazz) {
        // Получение всех объявленных полей класса
        Field[] fields = clazz.getDeclaredFields();
        // Список для хранения названий полей
        List<String> fieldNames = new ArrayList<>();
        // Итерация по полям и добавление их названий в список
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    private void setValues(List<String> list, List<String> fieldNames, Class<?> clazz) {
        for (String fieldName : fieldNames) {
            if (fieldName.equals(list.get(0))) {
                Field field = null;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                // Установка значения поля
                field.setAccessible(true); // Установка доступности приватного поля
                try {
                    field.set("", list.get(1));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private GameSetting mapGameObject(SettingsTemplate settingsTemplate) {
        if (isNull(settingsTemplate)) {
            return null;
        }
        GameSetting gameSetting = new GameSetting();
        gameSetting.setFuel(Double.parseDouble(settingsTemplate.getFuel()));
        gameSetting.setConsumption(Double.parseDouble(settingsTemplate.getFuel()));
        gameSetting.setVelocity(new Vector(Integer.parseInt(settingsTemplate.getVelocityX()),
                Integer.parseInt(settingsTemplate.getVelocityY())));
        gameSetting.setPosition(new Vector(Integer.parseInt(settingsTemplate.getPositionX()),
                Integer.parseInt(settingsTemplate.getPositionY())));
        gameSetting.setDirection(new Direction(Integer.parseInt(settingsTemplate.getDirection())));
        gameSetting.setDirectionsNumber(Integer.parseInt(settingsTemplate.getDirectionsNumber()));
        gameSetting.setAngularVelocity(Integer.parseInt(settingsTemplate.getAngularVelocity()));
        return gameSetting;
    }


}
