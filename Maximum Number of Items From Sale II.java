import java.util.*;

class Solution {
    private static class Bundle {
        long cost;
        long gain;
        long limit;

        Bundle(long cost, long gain, long limit) {
            this.cost = cost;
            this.gain = gain;
            this.limit = limit;
        }
    }

    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // mult[f] = number of items whose factor is a multiple of f
        int[] mult = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            for (int m = f; m <= n; m += f) {
                mult[f] += freq[m];
            }
        }

        List<Bundle> bundles = new ArrayList<>(n * 2);
        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            long d = mult[factor] - 1;

            if (d > 0) {
                bundles.add(new Bundle(price, 2L, d));
            }
            bundles.add(new Bundle(price, 1L, Long.MAX_VALUE));
        }

        // Sort descending by gain / cost -> (a.gain * b.cost) vs (b.gain * a.cost)
        bundles.sort((a, b) -> Long.compare(b.gain * a.cost, a.gain * b.cost));

        long rem = budget;
        long total = 0;

        for (Bundle b : bundles) {
            if (rem < b.cost) {
                continue;
            }
            long canBuy = rem / b.cost;
            long take = Math.min(b.limit, canBuy);
            total += take * b.gain;
            rem -= take * b.cost;
            if (rem == 0) {
                break;
            }
        }

        return (int) total;
    }
}
