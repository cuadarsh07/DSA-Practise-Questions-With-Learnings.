import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // dist[u][c] = min weight to reach node u with `c` consecutive identical characters ending at u.
        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // PriorityQueue stores {weight, node, count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Initial state at node 0: count is 1, weight is 0
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int c = (int) curr[2];

            if (d > dist[u][c]) continue;

            if (u == n - 1) {
                return (int) d;
            }

            char chU = labels.charAt(u);

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];
                char chV = labels.charAt(v);

                int nextC;
                if (chV == chU) {
                    nextC = c + 1;
                    if (nextC > k) continue; // Exceeds limit k
                } else {
                    nextC = 1; // Reset count for a different character
                }

                if (d + w < dist[v][nextC]) {
                    dist[v][nextC] = d + w;
                    pq.offer(new long[]{d + w, v, nextC});
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            ans = Math.min(ans, dist[n - 1][c]);
        }

        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }
}
