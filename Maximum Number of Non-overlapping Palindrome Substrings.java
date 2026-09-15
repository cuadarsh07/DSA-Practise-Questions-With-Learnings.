class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        // dp[i] stores the max number of non-overlapping palindromes in s[0...i-1]
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // Base case: we don't form a palindrome ending at the current character
            dp[i] = dp[i - 1];

            // 1. Check if there is a palindrome of length 'k' ending at i - 1
            if (i - k >= 0) {
                if (isPalindrome(chars, i - k, i - 1)) {
                    dp[i] = Math.max(dp[i], dp[i - k] + 1);
                }
            }

            // 2. Check if there is a palindrome of length 'k + 1' ending at i - 1
            if (i - k - 1 >= 0) {
                if (isPalindrome(chars, i - k - 1, i - 1)) {
                    dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
                }
            }
        }

        return dp[n];
    }

    // Helper method to check if a substring is a palindrome
    private boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
