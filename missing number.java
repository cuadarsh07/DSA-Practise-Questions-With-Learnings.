import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean[] present = new boolean[101]; 
        
        // Find min, max, and record present numbers
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
            present[num] = true;
        }
        
        // Collect missing numbers in the range
        List<Integer> missing = new ArrayList<>();
        for (int i = min + 1; i < max; i++) {
            if (!present[i]) {
                missing.add(i);
            }
        }
        
        return missing;
    }
}
