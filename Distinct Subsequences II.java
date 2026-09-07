class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // Array to store the number of distinct subsequences ending with each character (a-z)
        int[] endsWith = new int[26]; 
        
        for (char c : s.toCharArray()) {
            int currentTotal = 0;
            
            // Calculate the total number of distinct subsequences formed so far
            for (int count : endsWith) {
                currentTotal = (currentTotal + count) % MOD;
            }
            
            // The number of subsequences ending with the current character is 
            // all previous subsequences + 1 (the character by itself)
            endsWith[c - 'a'] = (currentTotal + 1) % MOD;
        }
        
        // Sum up the subsequences ending in all possible characters to get the final answer
        int result = 0;
        for (int count : endsWith) {
            result = (result + count) % MOD;
        }
        
        return result;
    }
}
