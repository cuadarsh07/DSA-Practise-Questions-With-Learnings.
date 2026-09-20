class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // 'a' maps to 26, 'b' to 25, ..., 'z' to 1
            int revAlphabetIndex = 26 - (s.charAt(i) - 'a');
            // Multiply by 1-indexed position in the string
            sum += revAlphabetIndex * (i + 1);
        }
        
        return sum;
    }
}
