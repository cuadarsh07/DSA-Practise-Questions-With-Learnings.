class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        int idx = 0;

        // Add elements less than pivot
        for (int num : nums) {
            if (num < pivot) {
                ans[idx++] = num;
            }
        }

        // Add elements equal to pivot
        for (int num : nums) {
            if (num == pivot) {
                ans[idx++] = num;
            }
        }

        // Add elements greater than pivot
        for (int num : nums) {
            if (num > pivot) {
                ans[idx++] = num;
            }
        }

        return ans;
    }
}
