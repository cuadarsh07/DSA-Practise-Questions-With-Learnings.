class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int[] prefMax = new int[n];
        prefMax[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i - 1], nums[i]);
        }

        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }

        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || prefMax[i] <= suffMin[i + 1]) {
                int val = prefMax[i];
                for (int j = start; j <= i; j++) {
                    ans[j] = val;
                }
                start = i + 1;
            }
        }

        return ans;
    }
}
