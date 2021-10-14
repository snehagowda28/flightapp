package com.flightapp.mapper;


import com.flightapp.openapi.dto.Meal;
import org.springframework.stereotype.Component;

@Component
public class MealMapper {

    public Meal toDto(com.flightapp.domain.Meal meal) {
        return Meal
                .builder()
                .nonvegmeal(meal.getNonVegMeal())
                .vegmeal(meal.getVegMeal())
                .build();
    }

    public com.flightapp.domain.Meal toDomain(Meal meal) {
        return com.flightapp.domain.Meal
                .builder()
                .nonVegMeal(meal.getNonvegmeal())
                .vegMeal(meal.getVegmeal())
                .build();
    }
}
