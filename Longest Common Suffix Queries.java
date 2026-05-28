class Solution {
    int[][] next;
    int[] bestIdx;
    int[] bestLen;
    int nodeCount = 1;

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int totalLen = 0;
        for (String word : wordsContainer) totalLen += word.length();

        next = new int[totalLen + 1][26];
        bestIdx = new int[totalLen + 1];
        bestLen = new int[totalLen + 1];

        java.util.Arrays.fill(bestIdx, -1);
        java.util.Arrays.fill(bestLen, Integer.MAX_VALUE);

        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = query(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String word, int index) {
        int cur = 0;
        update(cur, word.length(), index);

        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';

            if (next[cur][c] == 0) {
                next[cur][c] = nodeCount++;
            }

            cur = next[cur][c];
            update(cur, word.length(), index);
        }
    }

    private int query(String word) {
        int cur = 0;

        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';

            if (next[cur][c] == 0) {
                break;
            }

            cur = next[cur][c];
        }

        return bestIdx[cur];
    }

    private void update(int node, int len, int index) {
        if (
            len < bestLen[node] ||
            (len == bestLen[node] && index < bestIdx[node])
        ) {
            bestLen[node] = len;
            bestIdx[node] = index;
        }
    }
}
