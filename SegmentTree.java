class Solution {
    class SegmentTree {
        int[] prod;
        int[][] count;
        int n, k;
        
        public SegmentTree(int n, int k, int[] nums) {
            this.n = n;
            this.k = k;
            // A size of 4 * n is safely bounding the maximum size required for a segment tree
            prod = new int[4 * n];
            count = new int[4 * n][k];
            build(1, 0, n - 1, nums);
        }
        
        private void build(int node, int start, int end, int[] nums) {
            if (start == end) {
                int val = nums[start] % k;
                prod[node] = val;
                count[node][val] = 1;
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid, nums);
            build(2 * node + 1, mid + 1, end, nums);
            merge(node);
        }
        
        public void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                int v = val % k;
                prod[node] = v;
                for(int i = 0; i < k; i++) count[node][i] = 0;
                count[node][v] = 1;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            merge(node);
        }
        
        private void merge(int node) {
            int left = 2 * node;
            int right = 2 * node + 1;
            prod[node] = (prod[left] * prod[right]) % k;
            
            for (int i = 0; i < k; i++) {
                count[node][i] = count[left][i];
            }
            
            int p = prod[left];
            for (int i = 0; i < k; i++) {
                if (count[right][i] > 0) {
                    int nxt = (p * i) % k;
                    count[node][nxt] += count[right][i];
                }
            }
        }
        
        public int[] query(int node, int start, int end, int l, int r) {
            // returns an array where:
            // index 0 -> total_prod
            // indices 1..k -> are the modulo counts
            if (l <= start && end <= r) {
                int[] res = new int[k + 1];
                res[0] = prod[node];
                for (int i = 0; i < k; i++) res[i + 1] = count[node][i];
                return res;
            }
            int mid = (start + end) / 2;
            if (r <= mid) {
                return query(2 * node, start, mid, l, r);
            } else if (l > mid) {
                return query(2 * node + 1, mid + 1, end, l, r);
            } else {
                int[] leftRes = query(2 * node, start, mid, l, r);
                int[] rightRes = query(2 * node + 1, mid + 1, end, l, r);
                
                int[] res = new int[k + 1];
                res[0] = (leftRes[0] * rightRes[0]) % k;
                for (int i = 0; i < k; i++) res[i + 1] = leftRes[i + 1];
                
                int p = leftRes[0];
                for (int i = 0; i < k; i++) {
                    if (rightRes[i + 1] > 0) {
                        res[(p * i) % k + 1] += rightRes[i + 1];
                    }
                }
                return res;
            }
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n, k, nums);
        
        int q = queries.length;
        int[] ans = new int[q];
        
        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            
            // 1. Point update persists
            st.update(1, 0, n - 1, idx, val);
            
            // 2. Query prefixes counting array[start .. n-1] 
            int[] res = st.query(1, 0, n - 1, start, n - 1);
            
            // Evaluating and storing queried mod outcome 
            ans[i] = res[x + 1];
        }
        
        return ans;
    }
}
