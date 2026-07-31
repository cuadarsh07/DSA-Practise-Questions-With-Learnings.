class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequencies of each character
        int[] counts = new int[26];
        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        java.util.Arrays.sort(counts);
        
        int totalPushes = 0;
        int position = 0;
        
        // Step 3: Iterate from highest frequency to lowest (backwards)
        for (int i = 25; i >= 0; i--) {
            if (counts[i] == 0) {
                break; // No more characters left in the word
            }
            
            // Calculate push cost based on its mapped position
            // 0-7 -> cost 1
            // 8-15 -> cost 2
            // 16-23 -> cost 3
            // 24-25 -> cost 4
            int cost = (position / 8) + 1;
            totalPushes += counts[i] * cost;
            
            position++;
        }
        
        return totalPushes;
    }
}
