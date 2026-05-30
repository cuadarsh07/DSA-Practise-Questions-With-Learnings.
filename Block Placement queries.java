import java.util.*;

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int maxX = 0;
        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        TreeSet<Integer> obstacles = new TreeSet<>();
        SegmentTree seg = new SegmentTree(maxX + 2);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];

                Integer prev = obstacles.floor(x);
                Integer next = obstacles.ceiling(x);

                int left = prev == null ? 0 : prev;

                // New gap ending at x
                seg.update(x, x - left);

                // Existing next obstacle's gap shrinks
                if (next != null) {
                    seg.update(next, next - x);
                }

                obstacles.add(x);
            } else {
                int x = q[1];
                int sz = q[2];

                Integer prev = obstacles.floor(x);
                int left = prev == null ? 0 : prev;

                // Free space from last obstacle <= x to x
                int tailGap = x - left;

                // Max complete gap ending at some obstacle <= x
                int bestGap = Math.max(seg.query(1, x), tailGap);

                ans.add(bestGap >= sz);
            }
        }

        return ans;
    }

    class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int size) {
            n = size;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 1, n, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = l + (r - l) / 2;

            if (idx <= mid) {
                update(node * 2, l, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int ql, int qr) {
            if (ql > qr) return 0;
            return query(1, 1, n, ql, qr);
        }

        private int query(int node, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) return 0;

            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = l + (r - l) / 2;

            return Math.max(
                query(node * 2, l, mid, ql, qr),
                query(node * 2 + 1, mid + 1, r, ql, qr)
            );
        }
    }
}
