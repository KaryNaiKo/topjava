package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(1, meal));
        save(2, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            Map<Integer, Meal> meals = repository.get(userId);
            if(meals != null){
                meals.put(meal.getId(), meal);
            } else {
                HashMap<Integer, Meal> newMap = new HashMap<>();
                newMap.put(meal.getId(), meal);
                repository.put(userId, newMap);
            }
            return meal;
        }
        // treat case: update, but not present in storage
        return repository.computeIfPresent(userId, (id, oldMeal) -> {
            oldMeal.put(meal.getId(), meal);
            return oldMeal;
        }) != null ? meal : null;
    }

    @Override
    public boolean delete(int userId, int id) {
        Map<Integer, Meal> meals = repository.get(userId);
        if(meals != null) {
            return meals.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int id) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null ? meals.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId) != null ? repository.get(userId).values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList())

                : Collections.emptyList();
    }
}

