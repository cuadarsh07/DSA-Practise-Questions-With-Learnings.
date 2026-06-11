import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] seen = new boolean[n + 1];

        q.offer(new int[]{1, 0});
        seen[1] = true;

        int maxDepth = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int depth = cur[1];

            maxDepth = Math.max(maxDepth, depth);

            for (int nei : graph[node]) {
                if (!seen[nei]) {
                    seen[nei] = true;
                    q.offer(new int[]{nei, depth + 1});
                }
            }
        }

        return modPow(2, maxDepth - 1);
    }

    private int modPow(long base, int exp) {
        long res = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }

        return (int) res;
    }
}
