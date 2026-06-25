class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // P[x] stores the prefix sum up to the first x elements
        int[] P = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + (nums[i] == target ? 1 : -1);
        }
        
        int count = 0;
        // Check all pairs (i, j) where a valid subarray ends at j and starts at i
        for (int j = 1; j <= n; j++) {
            for (int i = 0; i < j; i++) {
                if (P[j] > P[i]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
