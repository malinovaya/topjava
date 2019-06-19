package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id) throws NotFoundException {
      repository.delete(id);
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public void update(Meal meal, int id) throws NotFoundException {

    }

    @Override
    public List<MealTo> getAll() {
        return new ArrayList<>(MealsUtil.getWithExcess(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
    }

    @Override
    public List<MealTo> getAllByDateTime() {
        return null;
    }
}