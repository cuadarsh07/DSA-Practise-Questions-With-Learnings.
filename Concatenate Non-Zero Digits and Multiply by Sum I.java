class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) {
            return 0;
        }
        
        long x = 0;
        long sum = 0;
        long multiplier = 1;
        
        while (n > 0) {
            int digit = n % 10;
            
            // Only process non-zero digits
            if (digit != 0) {
                x = x + (digit * multiplier);
                multiplier *= 10;
                sum += digit;
            }
            
            // Move to the next digit
            n /= 10;
        }
        
        return x * sum;
    }
}
