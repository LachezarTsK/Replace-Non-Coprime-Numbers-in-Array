
function replaceNonCoprimes(input: number[]): number[] {
    const resultsProcessedValues: number[] = new Array();
    resultsProcessedValues.push(input[0]);

    for (let i = 1; i < input.length; ++i) {
        let next = input[i];
        let previous = resultsProcessedValues[resultsProcessedValues.length - 1];

        if (next === 1 || previous === 1) {
            resultsProcessedValues.push(next);
            continue;
        }
        if (next === previous) {
            continue;
        }

        let leastCommonMultiple = calculateLeastCommonMultiple(previous, next);

        while (previous * next !== leastCommonMultiple) {
            next = leastCommonMultiple;
            resultsProcessedValues.pop();
            if (resultsProcessedValues.length === 0) {
                break;
            }
            previous = resultsProcessedValues[resultsProcessedValues.length - 1];
            leastCommonMultiple = calculateLeastCommonMultiple(previous, next);
        }
        resultsProcessedValues.push(next);
    }

    return resultsProcessedValues;
};

function calculateLeastCommonMultiple(first: number, second: number): number {
    return (first * second) /
        calculateGreatestCommonDivisor(Math.max(first, second), Math.min(first, second));
}

function calculateGreatestCommonDivisor(first: number, second: number): number {
    if (second === 0) {
        return first;
    }
    return calculateGreatestCommonDivisor(second, first % second);
}
