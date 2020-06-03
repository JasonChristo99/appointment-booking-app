package com.example.carlaundry.domain;

import androidx.annotation.NonNull;

import com.example.carlaundry.dao.CleaningTypesDAO;
import com.example.carlaundry.util.Money;

import java.time.Duration;

public class CleaningType {
    private String description;
    private Money cost;
    private Duration estimatedDuration;

    public CleaningType(String description, Money cost, Duration estimatedDuration) {
        this.description = description;
        this.cost = cost;
        this.estimatedDuration = estimatedDuration;
    }

    public String getDescription() {
        return description;
    }

    public Money getCost() {
        return cost;
    }

    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }

    public boolean add() {
        return CleaningTypesDAO.add(this);
    }
}
