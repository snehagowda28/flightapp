package com.flightapp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Meal {
    @Id
    @Column(name = "MEAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mealId;

    @Column
    private String vegMeal;

    @Column
    private String nonVegMeal;

//    @Column
//    @ToString.Exclude
//    @OneToMany(mappedBy = "meal", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Passenger> passengers;
}
