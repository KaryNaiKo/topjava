package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DayInfo {
    private int sumOfCaliroes;
    private List<UserMeal> list;

    public DayInfo(int sumOfCaliroes, List<UserMeal> list) {
        this.sumOfCaliroes = sumOfCaliroes;
        this.list = list;
    }

    public DayInfo() {
        list = new ArrayList<>();
    }

    public int getSumOfCaliroes() {
        return sumOfCaliroes;
    }

    public void setSumOfCaliroes(int sumOfCaliroes) {
        this.sumOfCaliroes = sumOfCaliroes;
    }

    public List<UserMeal> getList() {
        list.sort((o1, o2) -> {
            if (o1.getDateTime().isEqual(o2.getDateTime())) {
                return 0;
            } else if (o1.getDateTime().isBefore(o2.getDateTime())) {
                return -1;
            } else {
                return 1;
            }
        });
        return list;
    }

    public void setList(List<UserMeal> list) {
        this.list = list;
    }

    public void add(UserMeal meal) {
        list.add(meal);
    }

    public void plusCalories(int calories) {
        sumOfCaliroes += calories;
    }
}