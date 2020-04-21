package com.example.carlaundry.domain;

import com.example.carlaundry.util.Money;

import java.time.Duration;

public class CleaningType {
    private String description;
    private Money cost;
    private Duration estimatedTime;

    public CleaningType(String description, Money cost, Duration estimatedTime) {
        this.description = description;
        this.cost = cost;
        this.estimatedTime = estimatedTime;
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

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
