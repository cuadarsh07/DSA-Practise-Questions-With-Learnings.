class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            // Move left pointer to next vowel
            while (left < right && !isVowel(arr[left])) {
                left++;
            }
            
            // Move right pointer to previous vowel
            while (left < right && !isVowel(arr[right])) {
                right--;
            }
            
            // Swap vowels
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            
            left++;
            right--;
        }
        
        return new String(arr);
    }
    
    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}
