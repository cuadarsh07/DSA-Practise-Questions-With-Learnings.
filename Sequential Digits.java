import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String digits = "123456789";
        List<Integer> result = new ArrayList<>();
        
        // The lengths of sequential digits can range from 2 to 9
        for (int length = 2; length <= 9; length++) {
            // Slide a window of 'length' across the "123456789" string
            for (int i = 0; i <= 9 - length; i++) {
                String sub = digits.substring(i, i + length);
                int num = Integer.parseInt(sub);
                
                // If the number falls within the range, add it to our list
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        // The nested loops naturally generate the numbers in sorted order,
        // so no explicit sorting step is required.
        return result;
    }
}
