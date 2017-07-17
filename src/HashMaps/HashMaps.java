package HashMaps;

import java.util.*;

public class HashMaps {
    public static boolean canFormPalindrome(String s) {
        Set<Character> counts = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (counts.contains(c)) {
                counts.remove(c);
            } else {
                counts.add(c);
            }
        }

        return counts.size() <= 1;
    }

    public static boolean constructable(String targetText, String magazineText) {
        if (targetText.length() > magazineText.length()) return false;

        Map<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < targetText.length(); i++) {
            char c = targetText.charAt(i);

            if (!counts.containsKey(c)) {
                counts.put(c, 1);
            } else {
                counts.put(c, counts.get(c) + 1);
            }
        }

        for (char c : magazineText.toCharArray()) {
            if (counts.containsKey(c)) {
                counts.put(c, counts.get(c) - 1);
                if (counts.get(c) == 0) {
                    counts.remove(c);
                    if (counts.isEmpty()) {
                        return true;
                    }
                }
            }
        }

        return counts.isEmpty();
    }

    public static class LRUCache {
        LinkedHashMap<Integer, Integer> isbnToPrice;

        LRUCache(final int capacity) {
            this.isbnToPrice = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
                    return this.size() > capacity;
                }
            };
        }

        public Integer lookup(Integer key) {
            if (!isbnToPrice.containsKey(key)) {
                return null;
            }
            return isbnToPrice.get(key);
        }

        public void insert(Integer key, Integer value) {
            isbnToPrice.get(key);
            if (!isbnToPrice.containsKey(key)) {
                isbnToPrice.put(key, value);
            }
        }
    }
}
