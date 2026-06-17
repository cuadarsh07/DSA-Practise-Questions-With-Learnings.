class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];

        long cur = 0;
        long INF = Long.MAX_VALUE / 4;

        // Step 1: store result length after each operation
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (cur < INF) cur++;
            } else if (ch == '*') {
                if (cur > 0) cur--;
            } else if (ch == '#') {
                if (cur > INF / 2) cur = INF;
                else cur *= 2;
            } else if (ch == '%') {
                // reverse does not change length
            }

            len[i] = cur;
        }

        // k is out of bounds
        if (k >= cur) return '.';

        // Step 2: walk backward and map k to previous string state
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prevLen = (i == 0) ? 0 : len[i - 1];

            if (ch >= 'a' && ch <= 'z') {
                if (k == prevLen) {
                    return ch;
                }
                // otherwise k is in previous part, continue
            } else if (ch == '*') {
                // deletion only removed last char, existing indexes stay same
            } else if (ch == '#') {
                // result = prev + prev
                if (prevLen > 0 && k >= prevLen) {
                    k -= prevLen;
                }
            } else if (ch == '%') {
                // reversed index
                k = prevLen - 1 - k;
            }
        }

        return '.';
    }
}
