package com.example.carlaundry.dao;

import com.example.carlaundry.domain.CleaningStuffMember;

import java.util.HashSet;
import java.util.Set;

public class CleaningStuffDAO {
    private Set<CleaningStuffMember> cleaningStuffMembers = new HashSet<>();

    public Set<CleaningStuffMember> getCleaningStuffMembers() {
        return cleaningStuffMembers;
    }

    //TODO
    public static boolean addStuffMember(CleaningStuffMember stuffMember) {
        return false;
    }

    //TODO
    public static boolean removeStuffMember(int memberId) {
        return false;
    }
}
