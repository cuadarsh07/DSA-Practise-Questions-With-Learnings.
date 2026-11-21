import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, 1);

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        dfs(0, parent, s, adj, lastSeen, answer);
        return answer;
    }

    private void dfs(int u, int[] parent, String s, List<List<Integer>> adj, int[] lastSeen, int[] answer) {
        int c = s.charAt(u) - 'a';
        int newParent = (lastSeen[c] != -1) ? lastSeen[c] : parent[u];

        int prevSeen = lastSeen[c];
        lastSeen[c] = u;

        for (int v : adj.get(u)) {
            dfs(v, parent, s, adj, lastSeen, answer);
        }

        lastSeen[c] = prevSeen;

        if (newParent != -1) {
            answer[newParent] += answer[u];
        }
    }
}
