
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    public List<Integer> replaceNonCoprimes(int[] input) {
        Deque<Integer> resultsProcessedValues = new ArrayDeque<>();
        resultsProcessedValues.addLast(input[0]);

        for (int i = 1; i < input.length; ++i) {
            int next = input[i];
            int previous = resultsProcessedValues.peekLast();

            if (next == 1 || previous == 1) {
                resultsProcessedValues.addLast(next);
                continue;
            }
            if (next == previous) {
                continue;
            }

            int leastCommonMultiple = calculateLeastCommonMultiple(previous, next);

            while (previous * next != leastCommonMultiple) {
                next = leastCommonMultiple;
                resultsProcessedValues.pollLast();
                if (resultsProcessedValues.isEmpty()) {
                    break;
                }
                previous = resultsProcessedValues.peekLast();
                leastCommonMultiple = calculateLeastCommonMultiple(previous, next);
            }
            resultsProcessedValues.addLast(next);
        }

        return new ArrayList<>(resultsProcessedValues);
    }

    private int calculateLeastCommonMultiple(int first, int second) {
        return (int) (((long) first * second)
                / calculateGreatestCommonDivisor(Math.max(first, second), Math.min(first, second)));
    }

    private int calculateGreatestCommonDivisor(int first, int second) {
        if (second == 0) {
            return first;
        }
        return calculateGreatestCommonDivisor(second, first % second);
    }
}
