import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Sort logs based on time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int qLen = queries.length;
        // qArray stores {query_time, original_index}
        int[][] qArray = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            qArray[i][0] = queries[i];
            qArray[i][1] = i;
        }
        
        // Sort queries based on time
        Arrays.sort(qArray, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[qLen];
        int[] count = new int[n + 1];
        int uniqueServers = 0;
        
        int left = 0;
        int right = 0;
        
        for (int[] q : qArray) {
            int time = q[0];
            int idx = q[1];
            
            int minTime = time - x;
            int maxTime = time;
            
            // Expand the window by moving the right pointer
            while (right < logs.length && logs[right][1] <= maxTime) {
                int serverId = logs[right][0];
                if (count[serverId] == 0) {
                    uniqueServers++;
                }
                count[serverId]++;
                right++;
            }
            
            // Shrink the window by moving the left pointer
            while (left < logs.length && logs[left][1] < minTime) {
                int serverId = logs[left][0];
                count[serverId]--;
                if (count[serverId] == 0) {
                    uniqueServers--;
                }
                left++;
            }
            
            // Servers with zero requests = total servers - unique active servers
            ans[idx] = n - uniqueServers;
        }
        
        return ans;
    }
}
