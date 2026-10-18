import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] combin;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute combinations (Pascal's Triangle) for nCr % MOD
        combin = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            combin[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                combin[i][j] = (combin[i - 1][j - 1] + combin[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 because we want the number of *reorderings*, excluding the original array
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int lSize = left.size();
        int rSize = right.size();

        // Ways to interleave left and right subtrees: C(lSize + rSize, lSize)
        long ways = combin[lSize + rSize][lSize];
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}
