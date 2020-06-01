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
