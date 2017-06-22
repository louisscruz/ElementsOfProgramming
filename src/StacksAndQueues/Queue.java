package StacksAndQueues;

import java.util.Arrays;
import java.util.Collections;

public class Queue {
    private int head = 0, tail = 0, numQueueElements = 0;
    private static final int SCALE_FACTOR = 2;
    private Integer[] entries;

    public Queue(int size) {
        entries = new Integer[size];
    }

    public Integer enqueue(Integer el) {
        if (numQueueElements == entries.length) {
            head = 0;
            tail = numQueueElements;
            Collections.rotate(Arrays.asList(entries), -head);
            entries = Arrays.copyOf(entries, numQueueElements * SCALE_FACTOR);
        }
        entries[tail] = el;
        tail = (tail + 1) % entries.length;
        numQueueElements++;
    }

    public Integer dequeue() {
        if (numQueueElements != 0) {
            numQueueElements--;
            Integer val = entries[head];
            head = (head + 1) % entries.length;

            if (numQueueElements <= (entries.length / (SCALE_FACTOR * 2))) {
                Integer[] newEntries = new Integer[entries.length / SCALE_FACTOR];

                for (int i = head, j = 0; i < tail + 1; i++, j++) {
                    newEntries[j] = entries[i];
                }
                head = 0;
                entries = newEntries;
                tail = entries.length - 1;
            }

            return val;
        }
    }
}
