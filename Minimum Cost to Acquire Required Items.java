class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long minCost = Long.MAX_VALUE;
        
        // The total cost function is piecewise linear and convex.
        // Its slope only changes when we completely satisfy need1 or need2.
        // Therefore, the minimum cost will always occur at one of the critical points:
        // 1. Buying 0 of type 3 items.
        // 2. Buying exactly need1 of type 3 items.
        // 3. Buying exactly need2 of type 3 items.
        long[] candidates = {0, need1, need2};
        
        for (long x : candidates) {
            long currentCost = x * costBoth 
                             + Math.max(0L, need1 - x) * cost1 
                             + Math.max(0L, need2 - x) * cost2;
                             
            minCost = Math.min(minCost, currentCost);
        }
        
        return minCost;
    }
}
