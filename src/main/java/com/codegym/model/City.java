package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotNull
    @Positive
    private double area;

    @NotNull
    @Positive
    private Long population;

    @NotNull
    @Positive
    private double gdp;

    @NotEmpty
    @Size(min = 10)
    private String description;

    public City() {
    }

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private Nation nation;

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public double getGdp() {
        return gdp;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
