package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.DayInfo;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510),

                new UserMeal(LocalDateTime.of(2015, Month.APRIL, 1,10,0), "Завтрак", 510),
                new UserMeal(LocalDateTime.of(2015, Month.APRIL, 1,13,0), "Обед", 510),
                new UserMeal(LocalDateTime.of(2015, Month.APRIL, 1,20,0), "Ужин", 510)
        );
        Collections.shuffle(mealList);
        getFilteredWithExceeded(mealList, LocalTime.of(0, 0), LocalTime.of(23,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> list = new ArrayList<>();

        Map<LocalDate, DayInfo> map = new TreeMap<>();

        for (UserMeal meal: mealList) {
            DayInfo orDefault = map.getOrDefault(meal.getDateTime().toLocalDate(), new DayInfo());
            orDefault.plusCalories(meal.getCalories());

            if(TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)){
                orDefault.add(meal);
            }

            map.merge(meal.getDateTime().toLocalDate(), orDefault, (oldValue, newValue) -> newValue);
        }

        for ( DayInfo dayInfo : map.values()) {
            for (UserMeal m : dayInfo.getList()) {
                list.add(new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(), dayInfo.getSumOfCaliroes() >= caloriesPerDay));
            }
        }

        return list;
    }
}
