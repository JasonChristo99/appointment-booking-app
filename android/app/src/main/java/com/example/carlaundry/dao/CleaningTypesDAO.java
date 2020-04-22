package com.example.carlaundry.dao;

import com.example.carlaundry.domain.CleaningType;

import java.util.HashSet;
import java.util.Set;

public class CleaningTypesDAO {
    private static Set<CleaningType> cleaningTypes = new HashSet<>();

    public static Set<CleaningType> getCleaningTypes() {
        return cleaningTypes;
    }

    public static CleaningType find(int id) {
        for (CleaningType cleaningType : cleaningTypes) {
            if (cleaningType.getId() == id) {
                return cleaningType;
            }
        }
        return null;
    }
}
