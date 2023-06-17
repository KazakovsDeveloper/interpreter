package ru.otus.interpreter.command;

import ru.otus.interpreter.model.InterpretExpression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static ru.otus.interpreter.utils.Checker.isNullOrBlank;

public class ExpressionImpl implements Expression {

    // "id:12;action:move;fuel:2;consumption:7;velocityX:3;velocityY:9;positionX:7;positionY:8;direction:5;directionsNumber:7;angularVelocity:8"
    @Override
    public InterpretExpression interpret(String orderParameters) {
        if (isNullOrBlank(orderParameters)) {
            throw new RuntimeException("строка не содержит параметров приказа");
        }
        // массив типа [fuel:2, consumption:7 ...]
        String[] split = orderParameters.split(";");
        // пройтись по каждому элементу и разбить еще на элементы, лист массивов типа [fuel, 2]
        List<String[]> strings = Stream.of(split)
                .map(setting -> setting.split(":"))
                .toList();
        // перебрать массивы и записать в модель
        InterpretExpression interpretExpression = new InterpretExpression();

        // Получение класса объекта
        Class<?> clazz = interpretExpression.getClass();

        List<String> fieldNames = getFields(clazz);

        strings.stream()
                .map(Arrays::asList)
                .peek(list -> setValues(list, fieldNames, clazz, interpretExpression))
                .forEach(System.out::println);

        return interpretExpression;
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

    private void setValues(List<String> list, List<String> fieldNames, Class<?> clazz, InterpretExpression interpretExpression) {
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
                    field.set(interpretExpression, list.get(1));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
