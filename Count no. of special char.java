class Solution {
    public int numberOfSpecialChars(String word) {
        int[] firstUpper = new int[26];
        int[] lastLower = new int[26];

        Arrays.fill(firstUpper, Integer.MAX_VALUE);
        Arrays.fill(lastLower, -1);

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {
                lastLower[ch - 'a'] = i;
            } else {
                int idx = ch - 'A';
                firstUpper[idx] = Math.min(firstUpper[idx], i);
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 && firstUpper[i] != Integer.MAX_VALUE
                    && lastLower[i] < firstUpper[i]) {
                count++;
            }
        }

        return count;
    }
}
