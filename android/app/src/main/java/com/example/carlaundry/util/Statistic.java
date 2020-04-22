package com.example.carlaundry.util;

public class Statistic {
    private enum StatisticType {
        SALES_TOTAL,
        TOTAL_APPOINTMENTS_COMPLETE
        //TODO add more statistic types
    }

    private StatisticType type;
    private double value;

    public Statistic(StatisticType type, double value) {
        this.type = type;
        this.value = value;
    }

    public StatisticType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }
}
