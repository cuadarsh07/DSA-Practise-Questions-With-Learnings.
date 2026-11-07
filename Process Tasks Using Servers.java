import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        // Queue for free servers: stores {weight, index}
        // Ordered by weight ascending, then index ascending
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        // Queue for busy servers: stores {freeTime, weight, index}
        // Ordered by freeTime ascending, then weight ascending, then index ascending
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });
        
        // Initially, all servers are free
        for (int i = 0; i < servers.length; i++) {
            free.offer(new int[]{servers[i], i});
        }
        
        int m = tasks.length;
        int[] ans = new int[m];
        
        long time = 0; // Use long to prevent integer overflow since time can exceed 2^31-1
        
        for (int j = 0; j < m; j++) {
            // The task j arrives at second j
            time = Math.max(time, (long) j);
            
            // If no free servers are available, we must wait for the earliest one to become free
            if (free.isEmpty()) {
                time = Math.max(time, busy.peek()[0]);
            }
            
            // Move servers that have finished their tasks back to the free queue
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] s = busy.poll();
                free.offer(new int[]{(int)s[1], (int)s[2]});
            }
            
            // Assign the task to the best available free server
            int[] best = free.poll();
            ans[j] = best[1];
            
            // The server becomes busy until time + tasks[j]
            busy.offer(new long[]{time + tasks[j], best[0], best[1]});
        }
        
        return ans;
    }
}
