package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class MealServiceImpl implements MealService{
    private MealRepository mealRepository = new InMemoryMealRepository();

    @Override
    public List<MealTo> getAll() {
        return MealsUtil.getFiltered(mealRepository.readAll(), LocalTime.MIN, LocalTime.MAX, DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public Meal get(int id) {
        return mealRepository.read(id);
    }

    @Override
    public void delete(int id) {
        mealRepository.delete(id);
    }

    @Override
    public void saveOrUpdate(Meal meal) {
        if(meal.getId() == 0) {
            mealRepository.create(meal);
        } else {
            mealRepository.update(meal);
        }
    }
}
