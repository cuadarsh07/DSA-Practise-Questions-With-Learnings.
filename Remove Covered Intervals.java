import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort the intervals: 
        // 1. Ascending by start point
        // 2. Descending by end point (if start points are equal)
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int removed = 0;
        int maxEnd = -1;

        for (int[] interval : intervals) {
            // If the current interval's end is less than or equal to the maximum 
            // end seen so far, it is completely covered by a previous interval.
            if (interval[1] <= maxEnd) {
                removed++;
            } else {
                // Otherwise, it extends further, so we update the maxEnd.
                maxEnd = interval[1];
            }
        }

        // Return the total number of intervals minus the ones we removed
        return intervals.length - removed;
    }
}
