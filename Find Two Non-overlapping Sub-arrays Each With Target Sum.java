class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        
        int left = 0;
        int sum = 0;
        int bestSoFar = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the sliding window if the sum exceeds the target
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            // If we find a valid sub-array
            if (sum == target) {
                int currentLen = right - left + 1;
                
                // If there's a valid non-overlapping sub-array before the current one
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currentLen + minLen[left - 1]);
                }
                
                // Update the minimum length of a single sub-array found so far
                bestSoFar = Math.min(bestSoFar, currentLen);
            }
            
            // Store the best single sub-array length up to the current index
            minLen[right] = bestSoFar;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
