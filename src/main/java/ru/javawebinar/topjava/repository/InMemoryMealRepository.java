package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryMealRepository implements MealRepository {
    private static ConcurrentMap<Integer, Meal> map = new ConcurrentHashMap<>();
    private int sequence =7;

    static {
        map.put(1, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        map.put(2, new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        map.put(3, new Meal(4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        map.put(4, new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        map.put(5, new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        map.put(6, new Meal(7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public synchronized Meal create(Meal meal) {
        int id = sequence++;
        meal.setId(id);
        return map.put(id, meal);
    }

    @Override
    public synchronized Meal read(int id) {
        return map.get(id);
    }

    @Override
    public synchronized void update(Meal meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public synchronized void delete(Meal meal) {
        map.put(meal.getId(), null);
    }

    @Override
    public synchronized List<Meal> readAll() {
        return new ArrayList<>(map.values());
    }
}
