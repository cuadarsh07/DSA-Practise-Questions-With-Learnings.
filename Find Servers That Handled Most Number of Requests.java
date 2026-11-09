import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the number of requests each server successfully handles
        int[] requestsHandled = new int[k];
        
        // TreeSet keeps track of all available servers in sorted order 
        // to easily find the next available one using ceiling()
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        // PriorityQueue stores busy servers as int[] {endTime, serverId}
        // Ordered by the time they become free (endTime)
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int endTime = arrivalTime + load[i];
            
            // Free up any servers that have finished their requests by the current arrivalTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                availableServers.add(busyServers.poll()[1]);
            }
            
            // If no servers are available, the request is dropped
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Try to assign to the (i % k)th server or the next available one
            Integer assignedServer = availableServers.ceiling(i % k);
            
            // If no server >= i % k is available, wrap around to the first available server
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }
            
            // Mark the chosen server as busy and record the request
            availableServers.remove(assignedServer);
            busyServers.offer(new int[]{endTime, assignedServer});
            requestsHandled[assignedServer]++;
        }
        
        // Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int count : requestsHandled) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestsHandled[i] == maxRequests) {
                busiest.add(i);
            }
        }
        
        return busiest;
    }
}
