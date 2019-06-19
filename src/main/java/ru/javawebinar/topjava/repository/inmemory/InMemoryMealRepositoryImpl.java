package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.text.Collator;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(SecurityUtil.authUserId());
            repository.put(meal.getId(), meal);
            return meal;
        }

        if (!isExistMeal(meal.getId())) {
            return null;
        }
        if (!isAuthUser(meal.getId())) {
            return null;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        if (!isExistMeal(id)) {
            return false;
        }
        if (!isAuthUser(id)) {
            return false;
        }

            return repository.remove(id) != null;


    }

    @Override
    public Meal get(int id) {
        if (!isExistMeal(id)) {
            return null;
        }
        if (!isAuthUser(id)) {
            return null;
        }
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream().filter(e->e.getUserId() == SecurityUtil.authUserId()).
                sorted(Comparator.comparing(Meal::getDateTime).reversed()).
                collect(Collectors.toList());
    }

    private boolean isExistMeal (int id) {
        return repository.containsKey(id);
    }
    private boolean isAuthUser (int id) {
        return repository.get(id).getUserId() == SecurityUtil.authUserId();
    }
}

