class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int maxLength = 1;
        int incLength = 1;
        int decLength = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                incLength++;
                decLength = 1; // Reset decreasing length
            } else if (nums[i] < nums[i - 1]) {
                decLength++;
                incLength = 1; // Reset increasing length
            } else {
                // If equal, reset both lengths
                incLength = 1;
                decLength = 1;
            }
            
            maxLength = Math.max(maxLength, Math.max(incLength, decLength));
        }
        
        return maxLength;
    }
}
