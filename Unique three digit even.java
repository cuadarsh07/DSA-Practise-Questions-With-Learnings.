class Solution {
    public int totalNumbers(int[] digits) {
        // Step 1: Count the frequency of each digit in the input array
        int[] count = new int[10];
        for (int digit : digits) {
            count[digit]++;
        }
        
        int validCount = 0;
        
        // Step 2: Iterate through all possible 3-digit even numbers
        for (int i = 100; i <= 998; i += 2) {
            int[] currentCount = new int[10];
            int temp = i;
            
            // Extract the digits of the current number 'i'
            while (temp > 0) {
                currentCount[temp % 10]++;
                temp /= 10;
            }
            
            // Step 3: Check if the input array has enough of each required digit
            boolean isValid = true;
            for (int j = 0; j < 10; j++) {
                if (currentCount[j] > count[j]) {
                    isValid = false;
                    break;
                }
            }
            
            // If we have the required digits, increment our answer
            if (isValid) {
                validCount++;
            }
        }
        
        return validCount;
    }
}
