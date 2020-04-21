package com.example.carlaundry.dao;

import com.example.carlaundry.domain.CleaningType;

import java.util.HashSet;
import java.util.Set;

public class CleaningTypesDAO {
    private Set<CleaningType> cleaningTypes = new HashSet<>();

    public Set<CleaningType> getCleaningTypes() {
        return cleaningTypes;
    }

    //TODO
    public static boolean addCleaningType(CleaningType cleaningType) {
        return false;
    }

    //TODO
    public static boolean removeCleaningType(CleaningType cleaningType) {
        return false;
    }
}
