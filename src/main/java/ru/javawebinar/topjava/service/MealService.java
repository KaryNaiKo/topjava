package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    List<MealTo> getAll();
    Meal get(int id);
    void delete(int id);
    void saveOrUpdate(Meal meal);
}
