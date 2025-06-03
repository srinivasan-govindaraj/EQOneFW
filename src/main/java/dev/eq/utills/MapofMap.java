package dev.eq.utills;

import java.util.HashMap;
import java.util.Map;

public class MapofMap {
    public static void main(String[] args) {
        // Outer map: key is String (user name), value is another Map (user attributes)
        Map<String, Map<String, String>> userAttributes = new HashMap<>();

        // Create inner map for user "Alice"
        Map<String, String> aliceAttributes = new HashMap<>();
        aliceAttributes.put("age", "30");
        aliceAttributes.put("city", "New York");

        // Add Alice's attributes to the outer map
        userAttributes.put("Alice", aliceAttributes);

        // Create inner map for user "Bob"
        Map<String, String> bobAttributes = new HashMap<>();
        bobAttributes.put("age", "25");
        bobAttributes.put("city", "London");

        // Add Bob's attributes to the outer map
        userAttributes.put("Bob", bobAttributes);

        // Print all users and their attributes
        for (Map.Entry<String, Map<String, String>> entry : userAttributes.entrySet()) {
            String username = entry.getKey();
            Map<String, String> attributes = entry.getValue();
            System.out.println("User: " + username);
            for (Map.Entry<String, String> attrEntry : attributes.entrySet()) {
                System.out.println("  " + attrEntry.getKey() + ": " + attrEntry.getValue());
            }
        }
    }
}
