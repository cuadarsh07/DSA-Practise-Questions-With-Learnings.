class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;
        
        // Find the minimum value and check for the presence of any odd number
        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
            if (num % 2 != 0) {
                hasOdd = true;
            }
        }
        
        // If there are no odd numbers, all elements are already even.
        if (!hasOdd) {
            return true;
        }
        
        // If there are odd numbers, the minimum element must be odd 
        // to transform the even numbers.
        return minVal % 2 != 0;
    }
}
