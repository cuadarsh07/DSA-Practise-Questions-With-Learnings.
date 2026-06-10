class Solution {
    int[] nums;
    int n;
    long totalCount, totalSum;

    static class Res {
        long count, sum;
        Res(long c, long s) {
            count = c;
            sum = s;
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        this.nums = nums;
        this.n = nums.length;

        totalCount = (long) n * (n + 1) / 2;
        totalSum = lessThan(1_000_000_001L).sum;

        long lo = 0, hi = 1_000_000_000L;

        while (lo < hi) {
            long mid = (lo + hi + 1) / 2;
            long cntGE = totalCount - lessThan(mid).count;

            if (cntGE >= k) lo = mid;
            else hi = mid - 1;
        }

        long kth = lo;

        Res lessOrEqualKth = lessThan(kth + 1);
        long cntGreater = totalCount - lessOrEqualKth.count;
        long sumGreater = totalSum - lessOrEqualKth.sum;

        return sumGreater + (k - cntGreater) * kth;
    }

    // Counts and sums values of all subarrays with max - min < limit
    private Res lessThan(long limit) {
        long[] maxVal = new long[n + 5];
        long[] minVal = new long[n + 5];
        int[] maxCnt = new int[n + 5];
        int[] minCnt = new int[n + 5];

        int maxH = 0, maxT = 0;
        int minH = 0, minT = 0;

        long sumMax = 0, sumMin = 0;
        long count = 0, sum = 0;

        int left = 0;

        for (int right = 0; right < n; right++) {
            long x = nums[right];

            int cnt = 1;
            while (maxH < maxT && maxVal[maxT - 1] <= x) {
                cnt += maxCnt[maxT - 1];
                sumMax -= maxVal[maxT - 1] * maxCnt[maxT - 1];
                maxT--;
            }
            maxVal[maxT] = x;
            maxCnt[maxT] = cnt;
            sumMax += x * cnt;
            maxT++;

            cnt = 1;
            while (minH < minT && minVal[minT - 1] >= x) {
                cnt += minCnt[minT - 1];
                sumMin -= minVal[minT - 1] * minCnt[minT - 1];
                minT--;
            }
            minVal[minT] = x;
            minCnt[minT] = cnt;
            sumMin += x * cnt;
            minT++;

            while (left <= right && maxH < maxT && minH < minT
                    && maxVal[maxH] - minVal[minH] >= limit) {

                sumMax -= maxVal[maxH];
                maxCnt[maxH]--;
                if (maxCnt[maxH] == 0) maxH++;

                sumMin -= minVal[minH];
                minCnt[minH]--;
                if (minCnt[minH] == 0) minH++;

                left++;
            }

            long len = right - left + 1L;
            count += len;
            sum += sumMax - sumMin;
        }

        return new Res(count, sum);
    }
}
