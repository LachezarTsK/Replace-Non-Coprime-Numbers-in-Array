
#include <vector>
#include <algorithm>
using namespace std;

class Solution {

public:
    vector<int> replaceNonCoprimes(vector<int>& input) {
        vector<int> resultsProcessedValues;
        resultsProcessedValues.push_back(input[0]);

        for (int i = 1; i < input.size(); ++i) {
            int next = input[i];
            int previous = resultsProcessedValues.back();

            if (next == 1 || previous == 1) {
                resultsProcessedValues.push_back(next);
                continue;
            }
            if (next == previous) {
                continue;
            }

            long long leastCommonMultiple = calculateLeastCommonMultiple(previous, next);

            while (static_cast<long long>(previous) * next != leastCommonMultiple) {
                next = leastCommonMultiple;
                resultsProcessedValues.pop_back();
                if (resultsProcessedValues.empty()) {
                    break;
                }
                previous = resultsProcessedValues.back();
                leastCommonMultiple = calculateLeastCommonMultiple(previous, next);
            }
            resultsProcessedValues.push_back(next);
        }

        return resultsProcessedValues;
    }

private:
    long long calculateLeastCommonMultiple(int first, int second) {
        return (static_cast<long long>(first) * second) / calculateGreatestCommonDivisor(max(first, second), min(first, second));
    }

    int calculateGreatestCommonDivisor(int first, int second) {
        if (second == 0) {
            return first;
        }
        return calculateGreatestCommonDivisor(second, first % second);
    }
};
