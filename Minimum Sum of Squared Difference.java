class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] counts = new int[100001]; // max absolute difference is 10^5
        int maxDiff = 0;
        
        // Count the frequencies of each absolute difference
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            counts[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }
        
        long k = (long) k1 + k2;
        
        // Greedily reduce the largest differences
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (counts[i] > 0) {
                // We can at most reduce all 'i's or exhaust 'k'
                long reduceCount = Math.min((long) counts[i], k);
                
                counts[i] -= reduceCount;
                counts[i - 1] += reduceCount;
                k -= reduceCount;
            }
        }
        
        // Calculate the final minimum sum of squared differences
        long minSumSquare = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (counts[i] > 0) {
                minSumSquare += (long) counts[i] * (long) i * i;
            }
        }
        
        return minSumSquare;
    }
}
