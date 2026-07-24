class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // The maximum value is 1500, so an 11-bit integer is the maximum bounding limit.
        // 2^11 = 2048, meaning XOR combinations will never exceed 2047.
        boolean[] possible = new boolean[2048];
        int[] unique = new int[nums.length];
        int k = 0;
        
        // 1. Gather unique numbers and mark them as possible initially
        for (int x : nums) {
            if (!possible[x]) {
                possible[x] = true;
                unique[k++] = x;
            }
        }
        
        // 2. T array tracks all the results of XORing 2 distinct unique values
        boolean[] T = new boolean[2048];
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                T[unique[i] ^ unique[j]] = true;
            }
        }
        
        // 3. For each found valid pair XOR (t), XOR it with all single unique values
        // This simulates our 3 distinct values criteria effectively.
        for (int t = 0; t < 2048; t++) {
            if (T[t]) {
                for (int i = 0; i < k; i++) {
                    possible[t ^ unique[i]] = true;
                }
            }
        }
        
        // 4. Count the distinct XOR outputs
        int count = 0;
        for (int i = 0; i < 2048; i++) {
            if (possible[i]) {
                count++;
            }
        }
        
        return count;
    }
}
