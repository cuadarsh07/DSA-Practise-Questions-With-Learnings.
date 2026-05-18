import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                int i = q.poll();

                if (i == n - 1) return steps;

                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    q.offer(i + 1);
                }

                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    q.offer(i - 1);
                }

                List<Integer> same = map.get(arr[i]);
                if (same != null) {
                    for (int j : same) {
                        if (!visited[j]) {
                            visited[j] = true;
                            q.offer(j);
                        }
                    }
                    map.remove(arr[i]); // important to avoid TLE
                }
            }

            steps++;
        }

        return -1;
    }
}
