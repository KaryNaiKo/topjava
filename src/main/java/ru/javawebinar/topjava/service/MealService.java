package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    List<MealTo> getAll();
    MealTo get(int id);

}
