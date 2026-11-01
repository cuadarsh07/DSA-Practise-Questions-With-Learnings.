class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        
        // Step 1: Find the minimum element in the array
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        
        // Step 2: Calculate the number of moves needed
        int moves = 0;
        for (int num : nums) {
            moves += num - min;
        }
        
        return moves;
    }
}
