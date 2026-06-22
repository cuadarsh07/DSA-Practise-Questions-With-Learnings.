class Solution {
    public int maxNumberOfBalloons(String text) {
        // Frequency array for all 26 lowercase English letters
        int[] counts = new int[26];
        
        // Count the occurrences of each character in the text
        for (char c : text.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Get the counts for the specific characters in "balloon"
        int bCount = counts['b' - 'a'];
        int aCount = counts['a' - 'a'];
        int lCount = counts['l' - 'a'] / 2; // Requires 2 'l's per word
        int oCount = counts['o' - 'a'] / 2; // Requires 2 'o's per word
        int nCount = counts['n' - 'a'];
        
        // The bottleneck (minimum available) determines the max instances we can form
        return Math.min(bCount, Math.min(aCount, Math.min(lCount, Math.min(oCount, nCount))));
    }
}
