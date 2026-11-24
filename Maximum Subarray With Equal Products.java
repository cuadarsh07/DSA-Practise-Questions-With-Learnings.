class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int gcdVal = 0;
            int lcmVal = 1;
            // For nums[i] <= 10, max LCM of any subset is lcm(1..10) = 2520,
            // and max product before exceeding 2520 * 10 = 25200 fits easily in long.
            long prodVal = 1L;
            long cap = 2520L * 10L;

            for (int j = i; j < n; j++) {
                int x = nums[j];
                gcdVal = (gcdVal == 0) ? x : gcd(gcdVal, x);
                lcmVal = lcm(lcmVal, x);
                prodVal *= x;

                if (prodVal > cap) {
                    break;
                }

                if (prodVal == (long) lcmVal * gcdVal) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
