import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;

        long[][] arr = new long[m + 2][2];

        arr[0][0] = 1;
        arr[0][1] = 0;

        for (int i = 0; i < m; i++) {
            arr[i + 1][0] = restrictions[i][0];
            arr[i + 1][1] = restrictions[i][1];
        }

        arr[m + 1][0] = n;
        arr[m + 1][1] = n - 1L;

        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

        // Forward pass: limit height based on previous restricted building
        for (int i = 1; i < arr.length; i++) {
            long dist = arr[i][0] - arr[i - 1][0];
            arr[i][1] = Math.min(arr[i][1], arr[i - 1][1] + dist);
        }

        // Backward pass: limit height based on next restricted building
        for (int i = arr.length - 2; i >= 0; i--) {
            long dist = arr[i + 1][0] - arr[i][0];
            arr[i][1] = Math.min(arr[i][1], arr[i + 1][1] + dist);
        }

        long ans = 0;

        // Compute max possible peak between every adjacent restricted pair
        for (int i = 1; i < arr.length; i++) {
            long x1 = arr[i - 1][0];
            long h1 = arr[i - 1][1];
            long x2 = arr[i][0];
            long h2 = arr[i][1];

            long dist = x2 - x1;

            long peak = (h1 + h2 + dist) / 2;
            ans = Math.max(ans, peak);
        }

        return (int) ans;
    }
}
