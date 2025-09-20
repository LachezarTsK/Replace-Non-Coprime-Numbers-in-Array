
/**
 * @param {number[]} input
 * @return {number[]}
 */
var replaceNonCoprimes = function (input) {
    const resultsProcessedValues = new Array();
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

/**
 * @param {number} first
 * @param {number} second
 * @return {number}
 */
function calculateLeastCommonMultiple(first, second) {
    return (first * second) /
            calculateGreatestCommonDivisor(Math.max(first, second), Math.min(first, second));
}

/**
 * @param {number} first
 * @param {number} second
 * @return {number}
 */
function calculateGreatestCommonDivisor(first, second) {
    if (second === 0) {
        return first;
    }
    return calculateGreatestCommonDivisor(second, first % second);
}
