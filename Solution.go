
package main

func replaceNonCoprimes(input []int) []int {

    resultsProcessedValues := []int{}
    resultsProcessedValues = append(resultsProcessedValues, input[0])

    for i := 1; i < len(input); i++ {
        next := input[i]
        previous := resultsProcessedValues[len(resultsProcessedValues) - 1]

        if next == 1 || previous == 1 {
            resultsProcessedValues = append(resultsProcessedValues, next)
            continue
        }
        if next == previous {
            continue
        }

        leastCommonMultiple := calculateLeastCommonMultiple(previous, next)

        for int64(previous) * int64(next) != leastCommonMultiple {
            next = int(leastCommonMultiple)
            resultsProcessedValues = resultsProcessedValues[0 : len(resultsProcessedValues) - 1]
            if len(resultsProcessedValues) == 0 {
                break
            }
            previous = resultsProcessedValues[len(resultsProcessedValues) - 1]
            leastCommonMultiple = calculateLeastCommonMultiple(previous, next)
        }
        resultsProcessedValues = append(resultsProcessedValues, next)
    }

    return resultsProcessedValues
}

func calculateLeastCommonMultiple(first int, second int) int64 {
    return (int64(first) * int64(second)) /
            int64(calculateGreatestCommonDivisor(max(first, second), min(first, second)))
}

func calculateGreatestCommonDivisor(first int, second int) int {
    if second == 0 {
        return first
    }
    return calculateGreatestCommonDivisor(second, first%second)
}
