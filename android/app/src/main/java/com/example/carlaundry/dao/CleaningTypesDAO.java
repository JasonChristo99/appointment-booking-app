package com.example.carlaundry.dao;

import com.example.carlaundry.domain.CleaningType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CleaningTypesDAO {
    private static Set<CleaningType> cleaningTypes = new HashSet<>();

    public static Set<CleaningType> getCleaningTypes() {
        return cleaningTypes;
    }

    public static CleaningType find(String desc) {
        for (CleaningType cleaningType : cleaningTypes) {
            if (cleaningType.getDescription().equals(desc)) {
                return cleaningType;
            }
        }
        return null;
    }

    public static boolean add(CleaningType cleaningType) {
        return cleaningTypes.add(cleaningType);
    }

    public static boolean remove(CleaningType cleaningType) {
        return cleaningTypes.remove(cleaningType);
    }

    public static void reset() {
        cleaningTypes = new HashSet<>();
    }

    public static List<String> getCleaningTypesStringsList() {
        List<String> strings = new ArrayList<>();
        for (CleaningType cleaningType : cleaningTypes) {
            strings.add(cleaningType.getDescription());
        }
        Collections.sort(strings);
        return strings;
    }
}
