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

    public static SubArray smallestConsecutiveSubArrayCoverage(List<String> paragraph, List<String> queryStrings) {
        Map<String, Integer> keywordToIdx = new HashMap<>();

        List<Integer> latestOccurrence = new ArrayList<>(queryStrings.size());

        List<Integer> shortestSubArrayLength = new ArrayList<>(queryStrings.size());

        for (int i = 0; i < queryStrings.size(); i++) {
            latestOccurrence.add(-1);
            shortestSubArrayLength.add(Integer.MAX_VALUE);
            keywordToIdx.put(queryStrings.get(i), i);
        }

        int shortestDistance = Integer.MAX_VALUE;
        SubArray result = new SubArray(-1, -1);

        for (int i = 0; i < paragraph.size(); i++) {
            Integer keywordIdx = keywordToIdx.get(paragraph.get(i));

            if (keywordIdx != null) {
                if (keywordIdx == 0) {
                    shortestSubArrayLength.set(0, 1);
                } else if (shortestSubArrayLength.get(keywordIdx - 1) != Integer.MAX_VALUE) {
                    int distanceToPreviousKeyword = i - latestOccurrence.get(keywordIdx - 1);
                    shortestSubArrayLength.set(keywordIdx, distanceToPreviousKeyword + shortestSubArrayLength.get(keywordIdx - 1));
                }

                latestOccurrence.set(keywordIdx, i);

                if (keywordIdx == queryStrings.size() - 1 && shortestSubArrayLength.get(shortestSubArrayLength.size() - 1) < shortestDistance) {
                    shortestDistance = shortestSubArrayLength.get(shortestSubArrayLength.size() - 1);
                    result.start = i - shortestSubArrayLength.get(shortestSubArrayLength.size() - 1) + 1;
                    result.end = i;
                }
            }
        }

        return result;
    }

    public static SubArray largestDistinctSubArray(List<String> source) {
        Set<String> set = new HashSet<>();
        SubArray answer = new SubArray(-1, -1);

        int startIdx = 0;

        for (int i = 0; i < source.size(); i++) {
            String s = source.get(i);

            if (!set.contains(s)) {
                set.add(s);
                int previousAnswerSize = answer.end - answer.start + 1;

                if (answer.start + answer.end < 0 || set.size() > previousAnswerSize) {
                    answer.start = startIdx;
                    answer.end = i;
                }
            } else {
                String word = source.get(startIdx);
                while (word != s) {
                    set.remove(word);
                    word = source.get(++startIdx);
                }
                startIdx++;
                set.add(s);
            }
        }

        return answer;
    }

    public static int largestSequentialSubSet(List<Integer> input) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (Integer i : input) {
            counts.put(i, 1);
        }

        for (Integer i : input) {
            if (counts.containsKey(i)) {
                int checkVal = i - 1;

                while (counts.containsKey(checkVal)) {
                    counts.put(i, counts.get(i) + 1);
                    counts.remove(checkVal--);
                }

                checkVal = i + 1;

                while (counts.containsKey(checkVal)) {
                    counts.put(i, counts.get(i) + 1);
                    counts.remove(checkVal++);
                }
            }
        }

        int max = 1;

        for (Integer i : counts.keySet()) {
            if (counts.get(i) > max) {
                max = counts.get(i);
            }
        }

        return max;
    }

    public static class Score {
        public int id;
        public int val;

        public Score(int id, int val) {
            this.id = id;
            this.val = val;
        }
    }

    public static int topStudent(List<Score> scores) {
        Map<Integer, PriorityQueue<Integer>> studentScores = new HashMap<>();

        for (Score s : scores) {
            int studentId = s.id;
            int val = s.val;

            if (!studentScores.containsKey(studentId)) {
                studentScores.put(studentId, new PriorityQueue<Integer>());
            }

            PriorityQueue<Integer> scoreQueue = studentScores.get(studentId);

            scoreQueue.add(val);
        }

        int max = -1;
        int answer = -1;

        for (int i : studentScores.keySet()) {
            PriorityQueue minHeap = studentScores.get(i);

            if (minHeap.size() >= 3) {
                int average = (int)minHeap.poll();

                for (int j = 0; j < 2; j++) {
                    int nextInt = (int)minHeap.poll();

                    average = (average + nextInt) / 2;
                }

                if (average > max) {
                    answer = i;
                    max = average;
                }
            }
        }

        return answer;
    }

    public static List<Integer> findAllSubstrings(String s, List<String> words) {
        Map<String, Integer> wordToFreq = new HashMap<>();

        for (String word : words) {
            increment(word, wordToFreq);
        }

        int unitSize = words.get(0).length();

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i + unitSize * words.size() <= s.length(); i++) {
            if (matchAllWordsInDict(s, wordToFreq, i , words.size(), unitSize)) {
                result.add(i);
            }
        }

        return result;
    }

    public static boolean matchAllWordsInDict(String s, Map<String, Integer> wordToFreq, int start, int numWords, int unitSize) {
        Map<String, Integer> currStringToFreq = new HashMap<>();

        for (int i = 0; i < numWords; i++) {
            String currWord = s.substring(start + i * unitSize, start + (i + 1) * unitSize);
            Integer freq = wordToFreq.get(currWord);

            if (freq == null) {
                return false;
            }

            increment(currWord, currStringToFreq);

            if (currStringToFreq.get(currWord) > freq) {
                return false;
            }
        }

        return true;
    }

    private static void increment(String word, Map<String, Integer> dict) {
        Integer count = dict.get(word);

        if (count == null) {
            count = 0;
        }

        count++;
        dict.put(word, count);
    }

    public static boolean testCollatz(int n) {
        if (n < 1) return false;
        if (n < 3) return true;

        Set<Long> validNumbers = new HashSet<>();

        for (int i = 3; i <= n; i += 2) {
            Set<Long> path = new HashSet<>();
            long j = i;

            while (j >= i) {
                if (path.contains(j)) {
                    return false;
                } else {
                    path.add(j);

                    if (j % 2 == 0) {
                        j /= 2;
                    } else {
                        if (validNumbers.contains(j)) {
                            break;
                        }

                        validNumbers.add(j);
                        Long next = (3 * j) + 1;

                        if (next <= j) {
                            throw new ArithmeticException("Collatz overflowed");
                        }

                        j = next;
                    }
                }
            }
        }

        return true;
    }
}
