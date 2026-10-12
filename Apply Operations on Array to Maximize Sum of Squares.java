import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[32];
        
        // Count frequencies of each bit position across all numbers
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long maxSum = 0;
        long MOD = 1_000_000_007;
        
        // Greedily construct the top k numbers to maximize the sum of squares
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            
            for (int bit = 0; bit < 32; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCounts[bit]--;
                }
            }
            
            // Add square to our total sum with modulo arithmetic
            maxSum = (maxSum + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) maxSum;
    }
}
