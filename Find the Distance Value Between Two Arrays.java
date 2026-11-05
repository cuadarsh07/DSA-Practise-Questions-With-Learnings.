class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        
        for (int val1 : arr1) {
            boolean isValid = true;
            
            for (int val2 : arr2) {
                // If we find any element in arr2 where the absolute difference 
                // is less than or equal to d, val1 is not valid.
                if (Math.abs(val1 - val2) <= d) {
                    isValid = false;
                    break;
                }
            }
            
            // If the loop finishes and isValid is still true, 
            // no element in arr2 violated the condition.
            if (isValid) {
                count++;
            }
        }
        
        return count;
    }
}
