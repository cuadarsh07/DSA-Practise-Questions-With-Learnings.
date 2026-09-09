class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long base = 1000;
        
        // Add commas for each threshold: 1K, 1M, 1B, etc.
        while (n >= base) {
            // Count how many numbers are >= the current base
            totalCommas += (n - base + 1);
            base *= 1000;
        }
        
        return totalCommas;
    }
}
