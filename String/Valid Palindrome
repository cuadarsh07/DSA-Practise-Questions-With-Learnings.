class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char chLeft = s.charAt(left);
            char chRight = s.charAt(right);

            // skip non-alphanumeric from left
            if (!Character.isLetterOrDigit(chLeft)) {
                left++;
                continue;
            }

            // skip non-alphanumeric from right
            if (!Character.isLetterOrDigit(chRight)) {
                right--;
                continue;
            }

            // compare in lowercase
            if (Character.toLowerCase(chLeft) != Character.toLowerCase(chRight)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
