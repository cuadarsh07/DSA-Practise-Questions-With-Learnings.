import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        int maxVal = nums[nums.length - 1];
        // dp[v] stores the max length of a consecutive sequence ending at value v
        int[] dp = new int[maxVal + 2];
        int ans = 0;

        for (int x : nums) {
            // Update x + 1 first so the current element isn't used twice
            dp[x + 1] = dp[x] + 1;
            dp[x] = dp[x - 1] + 1;
            ans = Math.max(ans, Math.max(dp[x], dp[x + 1]));
        }

        return ans;
    }
}
