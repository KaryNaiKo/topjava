package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealService mealService = new MealServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        int id;
        List<MealTo> filtered;

        if (method != null) {
            switch (method) {
                case "edit":
                    id = Integer.parseInt(req.getParameter("id"));
                    log.debug("edit meal with id {}", id);
                    Meal meal = mealService.get(id);
                    req.setAttribute("meal", meal);
                    req.getRequestDispatcher("editMeal.jsp").forward(req, resp);
                    break;

                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    log.debug("delete meal with id {}", id);
                    mealService.delete(id);
                    filtered = mealService.getAll();
                    req.setAttribute("mealList", filtered);
                    resp.sendRedirect("meals");
                    break;
                case "create":
                    log.debug("create new meal");
                    meal = new Meal();
                    req.setAttribute("meal", meal);
                    req.getRequestDispatcher("editMeal.jsp").forward(req, resp);
                    break;

                default:
                    log.debug("forward to meals");
                    filtered = mealService.getAll();
                    req.setAttribute("mealList", filtered);
                    req.getRequestDispatcher("meals.jsp").forward(req, resp);
            }
        } else {
            log.debug("forward to meals");
            filtered = mealService.getAll();
            req.setAttribute("mealList", filtered);
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Receive meal");

        String id = req.getParameter("id");
        String description = req.getParameter("description");
        String date = req.getParameter("date");
        String calories = req.getParameter("calories");

        int i = 0;
        if (id != null) {
            i = Integer.parseInt(id);
        }

        int c = Integer.parseInt(calories);

        mealService.saveOrUpdate(new Meal(i, LocalDateTime.parse(date), description, c));

        resp.sendRedirect("meals");
    }
}
