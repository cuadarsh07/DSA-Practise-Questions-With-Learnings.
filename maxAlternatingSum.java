import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        // Convert all numbers to their absolute values.
        // Because x^2 is strictly increasing for non-negative x, 
        // sorting absolute values is equivalent to sorting their squares.
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        
        // Sort the elements in ascending order
        Arrays.sort(nums);
        
        long maxScore = 0;
        int numSubtractions = n / 2;
        
        // The smallest elements go to odd indices (subtracted)
        for (int i = 0; i < numSubtractions; i++) {
            long val = nums[i];
            maxScore -= val * val;
        }
        
        // The largest elements go to even indices (added)
        for (int i = numSubtractions; i < n; i++) {
            long val = nums[i];
            maxScore += val * val;
        }
        
        return maxScore;
    }
}
