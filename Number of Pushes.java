class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int totalPushes = 0;
        
        // Iterate through each character in the word
        for (int i = 0; i < n; i++) {
            // (i / 8) + 1 gives the number of pushes required for the current letter:
            // i = 0 to 7 -> 1 push
            // i = 8 to 15 -> 2 pushes
            // i = 16 to 23 -> 3 pushes
            // i = 24 to 25 -> 4 pushes
            totalPushes += (i / 8) + 1;
        }
        
        return totalPushes;
    }
}
