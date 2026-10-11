class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Loop through the array using 0-based index 'i'
        for (int i = 0; i < n; i++) {
            // The 1-based index is (i + 1)
            // Check if (i + 1) divides n evenly
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }
        
        return sum;
    }
}
