import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Loop through each possible starting index of a subarray
        for (int i = 0; i < n; i++) {
            // Since nums[i] <= 100, a boolean array is faster than a HashSet
            boolean[] seen = new boolean[101];
            int distinctCount = 0;
            
            // Loop through each possible ending index
            for (int j = i; j < n; j++) {
                int val = nums.get(j);
                
                // If we haven't seen this number in the current subarray, increment count
                if (!seen[val]) {
                    seen[val] = true;
                    distinctCount++;
                }
                
                // Add the square of the distinct count to the total sum
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}
