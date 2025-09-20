
using System;
using System.Collections.Generic;

public class Solution
{
    public IList<int> ReplaceNonCoprimes(int[] input)
    {
        Stack<int> resultsProcessedValues = new Stack<int>();
        resultsProcessedValues.Push(input[0]);

        for (int i = 1; i < input.Length; ++i)
        {
            int next = input[i];
            int previous = resultsProcessedValues.Peek();

            if (next == 1 || previous == 1)
            {
                resultsProcessedValues.Push(next);
                continue;
            }
            if (next == previous)
            {
                continue;
            }

            int leastCommonMultiple = CalculateLeastCommonMultiple(previous, next);

            while (previous * next != leastCommonMultiple)
            {
                next = leastCommonMultiple;
                resultsProcessedValues.Pop();
                if (resultsProcessedValues.Count == 0)
                {
                    break;
                }
                previous = resultsProcessedValues.Peek();
                leastCommonMultiple = CalculateLeastCommonMultiple(previous, next);
            }
            resultsProcessedValues.Push(next);
        }

        List<int> resultsProcessedValuesReversed = resultsProcessedValues.ToList();
        resultsProcessedValuesReversed.Reverse();
        return resultsProcessedValuesReversed;
    }

    private int CalculateLeastCommonMultiple(int first, int second)
    {
        return (int)(((long)first * second)
                / CalculateGreatestCommonDivisor(Math.Max(first, second), Math.Min(first, second)));
    }

    private int CalculateGreatestCommonDivisor(int first, int second)
    {
        if (second == 0)
        {
            return first;
        }
        return CalculateGreatestCommonDivisor(second, first % second);
    }
}
