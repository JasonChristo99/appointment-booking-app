package com.example.carlaundry.dao;

import com.example.carlaundry.domain.CleaningStuffMember;

import java.util.HashSet;
import java.util.Set;

public class CleaningStuffDAO {
    private static Set<CleaningStuffMember> cleaningStuffMembers = new HashSet<>();

    public static Set<CleaningStuffMember> getCleaningStuffMembers() {
        return cleaningStuffMembers;
    }

    public static CleaningStuffMember find(int id) {
        for (CleaningStuffMember stuffMember : cleaningStuffMembers) {
            if (stuffMember.getId() == id) {
                return stuffMember;
            }
        }
        return null;
    }
}
