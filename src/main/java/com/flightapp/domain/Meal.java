package com.flightapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Meal {
    @Id
    @Column(name = "MEAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int mealId;

    @Column
    private String vegMeal;

    @Column
    private String nonVegMeal;
}
