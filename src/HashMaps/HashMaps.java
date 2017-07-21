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

    public static List<String> kMostFrequentQueries(List<String> queries, int k) {
        Map<String, Integer> counts = new HashMap<>();

        for (String q : queries) {
            if (counts.containsKey(q)) {
                counts.put(q, counts.get(q) + 1);
            } else {
                counts.put(q, 1);
            }
        }

        queries = new ArrayList<String>(counts.keySet());

        return queryQuickSelect(queries, k, 0, queries.size() - 1, counts);
    }

    private static List<String> queryQuickSelect(List<String> input, int k, int first, int last, Map<String, Integer> valuesMap) {
        Random rand = new Random();

        while (first < last) {
            int nextRand = first + rand.nextInt(last - first);
            int nextPivot = partition(input, nextRand, first, last, valuesMap);

            if (nextPivot < (k - 1)) {
                first = nextPivot + 1;
            } else {
                last = nextPivot;
            }
        }

        return input.subList(0, k);
    }

    private static int partition(List<String> input, int randIdx, int first, int last, Map<String, Integer> valuesMap) {
        String key = input.get(randIdx);
        int val = valuesMap.get(key);

        Collections.swap(input, last, randIdx);

        for (int i = first; i < last; i++) {
            String currentKey = input.get(i);
            int currentVal = valuesMap.get(currentKey);

            if (currentVal > val) {
                Collections.swap(input, i, first);
                first++;
            }
        }

        Collections.swap(input, first, last);

        return first;
    }

    public static class SubArray {
        public int start;
        public int end;

        public SubArray(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Integer getValueForFirstEntry(LinkedHashMap<String, Integer> m) {
        Integer result = null;

        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            result = entry.getValue();
            break;
        }

        return result;
    }

    public static SubArray smallestSubArrayCoverage(Iterator<String> stream, List<String> queryStrings) {
        LinkedHashMap<String, Integer> dict = new LinkedHashMap<>();

        for (String s : queryStrings) {
            dict.put(s, null);
        }

        int numStringsFromQueryStringsSeenSoFar = 0;

        SubArray res = new SubArray(-1, -1);

        int idx = 0;

        while (stream.hasNext()) {
            String s = stream.next();

            if (dict.containsKey(s)) {
                Integer it = dict.get(s);

                if (it == null) {
                    numStringsFromQueryStringsSeenSoFar++;
                }

                dict.remove(s);
                dict.put(s, idx);
            }

            if (numStringsFromQueryStringsSeenSoFar == queryStrings.size()) {
                if ((res.start == -1 && res.end == -1) || idx - getValueForFirstEntry(dict) < res.end - res.start) {
                    res.start = getValueForFirstEntry(dict);
                    res.end = idx;
                }
            }

            idx++;
        }

        return res;
    }
}
