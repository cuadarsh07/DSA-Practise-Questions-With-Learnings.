import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] L = new int[26];
        int[] R = new int[26];
        
        // Initialize with -1 to indicate unseen characters
        Arrays.fill(L, -1);
        Arrays.fill(R, -1);
        
        // Step 1: Find the first and last occurrence index of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (L[c] == -1) {
                L[c] = i;
            }
            R[c] = i;
        }
        
        // Step 2: Get all valid candidate intervals
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (L[i] == -1) continue;
            int end = getValidEnd(s, i, L, R);
            // If it's a valid interval (doesn't force us to expand leftward)
            if (end != -1) {
                intervals.add(new int[]{L[i], end});
            }
        }
        
        // Step 3: Sort by interval end points (Greedy approach)
        Collections.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Step 4: Extract the results ensuring they don't overlap
        List<String> res = new ArrayList<>();
        int lastEnd = -1;
        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                res.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1]; // Update the bounds
            }
        }
        
        return res;
    }
    
    // Helper method to expand our bound or reject an invalid candidate 
    private int getValidEnd(String s, int c, int[] L, int[] R) {
        int right = R[c];
        for (int i = L[c]; i <= right; i++) {
            int curr = s.charAt(i) - 'a';
            
            // If we find a character that forces the interval to stretch left before L[c], 
            // it means `c` is not the true bounding start. We abandon this path.
            if (L[curr] < L[c]) {
                return -1; 
            }
            // Expand the right bound dynamically if needed
            right = Math.max(right, R[curr]);
        }
        return right;
    }
}
