import java.util.Arrays;

class Solution {
    public long maxSum(int[] nums) {
        int n = nums.length;
        long[] dp = new long[n];
        Arrays.fill(dp, -1L);

        return solve(n - 1, nums, dp);
    }

    private long solve(int ind, int[] nums, long[] dp) {
        // Base case
        if (ind < 0) return 0;

        // Already calculated
        if (dp[ind] != -1) return dp[ind];

        // Include current element
        long incl = nums[ind] + solve(ind - 2, nums, dp);

        // Exclude current element
        long excl = solve(ind - 1, nums, dp);

        // Store and return the maximum
        return dp[ind] = Math.max(incl, excl);
    }
}
