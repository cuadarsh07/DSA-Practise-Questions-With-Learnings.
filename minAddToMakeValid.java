class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int closeNeeded = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                closeNeeded++; // We have an open parenthesis, need a closing one
            } else {
                if (closeNeeded > 0) {
                    closeNeeded--; // We found a closing parenthesis that matches an open one
                } else {
                    openNeeded++; // We have a closing parenthesis without a matching open one
                }
            }
        }
        
        return openNeeded + closeNeeded;
    }
}
