import java.util.TreeMap;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;
        long[] costs = new long[m];

        SlidingMedian sm = new SlidingMedian();
        for (int i = 0; i < n; i++) {
            sm.add(nums[i]);
            if (i >= x) {
                sm.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                costs[i - x + 1] = sm.getCost();
            }
        }

        long[][] dp = new long[m][k + 1];
        long INF = Long.MAX_VALUE / 3;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = INF;
            }
            dp[i][0] = 0;
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 0; i < m; i++) {
                long skip = (i > 0) ? dp[i - 1][j] : INF;
                long take = costs[i];
                if (i >= x) {
                    take += dp[i - x][j - 1];
                } else if (j > 1) {
                    take = INF;
                }
                dp[i][j] = Math.min(skip, take);
            }
        }

        return dp[m - 1][k];
    }

    class SlidingMedian {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        long sumLeft = 0;
        long sumRight = 0;
        int sizeLeft = 0;
        int sizeRight = 0;

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                sumLeft += val;
                sizeLeft++;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                sumRight += val;
                sizeRight++;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                int count = left.get(val);
                if (count == 1) left.remove(val);
                else left.put(val, count - 1);
                sumLeft -= val;
                sizeLeft--;
            } else {
                int count = right.get(val);
                if (count == 1) right.remove(val);
                else right.put(val, count - 1);
                sumRight -= val;
                sizeRight--;
            }
            balance();
        }

        void balance() {
            while (sizeLeft > sizeRight + 1) {
                int maxLeft = left.lastKey();
                int count = left.get(maxLeft);
                if (count == 1) left.remove(maxLeft);
                else left.put(maxLeft, count - 1);
                sumLeft -= maxLeft;
                sizeLeft--;

                right.put(maxLeft, right.getOrDefault(maxLeft, 0) + 1);
                sumRight += maxLeft;
                sizeRight++;
            }
            while (sizeLeft < sizeRight) {
                int minRight = right.firstKey();
                int count = right.get(minRight);
                if (count == 1) right.remove(minRight);
                else right.put(minRight, count - 1);
                sumRight -= minRight;
                sizeRight--;

                left.put(minRight, left.getOrDefault(minRight, 0) + 1);
                sumLeft += minRight;
                sizeLeft++;
            }
        }

        long getCost() {
            if (sizeLeft == sizeRight) {
                return sumRight - sumLeft;
            } else {
                return sumRight - sumLeft + left.lastKey();
            }
        }
    }
}
