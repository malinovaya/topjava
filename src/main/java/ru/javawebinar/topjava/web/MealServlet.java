package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

        private static final Logger log = getLogger(ru.javawebinar.topjava.web.UserServlet.class);


        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            log.debug("redirect to meals");

            List<MealTo> mealToList = MealsUtil.hardcodedList();
            request.setAttribute("mealsWithExceed", mealToList);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);


            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("delete")) {
                int id = Integer.parseInt(request.getParameter("mealToId"));
                for (MealTo meal : mealToList) {
                    if (meal.getMealToId() == id) {
                        mealToList.remove(meal);
                        log.debug("removed!");

                    }
                }
            } else if (action.equalsIgnoreCase("update")) {
                int id = Integer.parseInt(request.getParameter("mealToId"));

                String dateTime = request.getParameter("dateTime");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);

                String description = request.getParameter("description");
                int calories = Integer.parseInt(request.getParameter("calories"));

                Meal m = new Meal(formatDateTime, description, calories);

                for (Meal meal: MealsUtil.meals) {
                    if (meal.getMealId() == id) {
                        MealsUtil.meals.add(id, m);
                      request.setAttribute("meal", m);
                      mealToList = MealsUtil.hardcodedList();
                    }
                }

            }

            request.setAttribute("mealsWithExceed", mealToList);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
