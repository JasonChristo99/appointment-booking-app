package com.example.carlaundry.dao;

import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.util.EmailAddress;

import java.util.HashSet;
import java.util.Set;

public class CleaningStuffDAO {
    private static Set<CleaningStuffMember> cleaningStuffMembers = new HashSet<>();

    public static Set<CleaningStuffMember> getCleaningStuffMembers() {
        return cleaningStuffMembers;
    }

    public static CleaningStuffMember find(EmailAddress email) {
        for (CleaningStuffMember stuffMember : cleaningStuffMembers) {
            if (stuffMember.getEmailAddress().equals(email)) {
                return stuffMember;
            }
        }
        return null;
    }

    public static boolean add(CleaningStuffMember cleaningStuffMember) {
        return cleaningStuffMembers.add(cleaningStuffMember);
    }

    public static boolean remove(CleaningStuffMember cleaningStuffMember) {
        return cleaningStuffMembers.remove(cleaningStuffMember);
    }

    public static void reset() {
        cleaningStuffMembers = new HashSet<>();
    }
}
