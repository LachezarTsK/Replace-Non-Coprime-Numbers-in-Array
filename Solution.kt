
import kotlin.math.max
import kotlin.math.min

class Solution {

    fun replaceNonCoprimes(input: IntArray): List<Int> {
        val resultsProcessedValues = mutableListOf<Int>()
        resultsProcessedValues.add(input[0])

        for (i in 1..<input.size) {
            var next = input[i]
            var previous = resultsProcessedValues.last()

            if (next == 1 || previous == 1) {
                resultsProcessedValues.add(next)
                continue
            }
            if (next == previous) {
                continue
            }

            var leastCommonMultiple = calculateLeastCommonMultiple(previous, next)

            while (previous * next != leastCommonMultiple) {
                next = leastCommonMultiple
                resultsProcessedValues.removeLast()
                if (resultsProcessedValues.isEmpty()) {
                    break
                }
                previous = resultsProcessedValues.last()
                leastCommonMultiple = calculateLeastCommonMultiple(previous, next)
            }
            resultsProcessedValues.add(next)
        }

        return resultsProcessedValues
    }

    private fun calculateLeastCommonMultiple(first: Int, second: Int): Int {
        return ((first.toLong() * second) /
                calculateGreatestCommonDivisor(max(first, second), min(first, second))).toInt()
    }

    private fun calculateGreatestCommonDivisor(first: Int, second: Int): Int {
        if (second == 0) {
            return first
        }
        return calculateGreatestCommonDivisor(second, first % second)
    }
}
