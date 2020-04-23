package com.example.carlaundry.domain;

import com.example.carlaundry.dao.CleaningTypesDAO;
import com.example.carlaundry.util.Money;

import java.time.Duration;

public class CleaningType {
    private String description;
    private Money cost;
    private Duration estimatedDuration;
    private int id;

    public CleaningType(String description, Money cost, Duration estimatedDuration, int id) {
        this.description = description;
        this.cost = cost;
        this.estimatedDuration = estimatedDuration;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getCost() {
        return cost;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }

    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Duration estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public boolean addToCollection() {
        return CleaningTypesDAO.getCleaningTypes().add(this);
    }

    public boolean removeFromCollection(int id) {
        return CleaningTypesDAO.getCleaningTypes().remove(this);
    }
}
