package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    Meal create(Meal meal);
    Meal read(int id);
    void update(Meal meal);
    void delete(Meal meal);
    List<Meal> readAll();
}
