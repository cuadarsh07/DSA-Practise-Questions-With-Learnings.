class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Precompute the minimums of the suffix ending at each index
        int[] minSuffix = new int[n];
        minSuffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = Math.min(nums[i], minSuffix[i + 1]);
        }
        
        int currentMax = nums[0]; // To track the max in nums[0..i]
        
        // Iterate to find the first index satisfying the condition
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            
            // Instability score: max(nums[0..i]) - min(nums[i..n - 1])
            if (currentMax - minSuffix[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
