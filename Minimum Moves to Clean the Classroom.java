import java.util.Arrays;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startCell = -1;
        int[] litterCells = new int[10];
        int k = 0;
        
        // Parse the grid and pinpoint the start and litters
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startCell = r * n + c;
                } else if (ch == 'L') {
                    litterCells[k++] = r * n + c;
                }
            }
        }
        
        if (k == 0) return 0; // No litter to collect
        
        int targetMask = (1 << k) - 1;
        int[][] maxEnergy = new int[1 << k][m * n];
        for (int i = 0; i < (1 << k); i++) {
            Arrays.fill(maxEnergy[i], -1);
        }
        
        // Queue size of 10 million is safely well above the reachable unique state counts
        // state = (mask << 15) | (cell << 6) | energy
        int[] q = new int[10000000];
        int head = 0, tail = 0;
        
        int initialState = (0 << 15) | (startCell << 6) | energy;
        q[tail++] = initialState;
        maxEnergy[0][startCell] = energy;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;
        
        while (head < tail) {
            int size = tail - head;
            for (int i = 0; i < size; i++) {
                int state = q[head++];
                int mask = state >> 15;
                int cell = (state >> 6) & 0x1FF; // 9 bits (max 400)
                int currentEnergy = state & 0x3F; // 6 bits (max 50)
                
                int r = cell / n;
                int c = cell % n;
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        char nextChar = classroom[nr].charAt(nc);
                        
                        if (nextChar == 'X') continue;
                        
                        int nextEnergy = currentEnergy - 1;
                        if (nextEnergy < 0) continue;
                        
                        int nextMask = mask;
                        int nextCell = nr * n + nc;
                        
                        if (nextChar == 'R') {
                            nextEnergy = energy;
                        } else if (nextChar == 'L') {
                            // Find which litter it is and update the mask
                            for (int l = 0; l < k; l++) {
                                if (litterCells[l] == nextCell) {
                                    nextMask |= (1 << l);
                                    break;
                                }
                            }
                        }
                        
                        if (nextMask == targetMask) {
                            return moves + 1;
                        }
                        
                        // Prune and enqueue
                        if (nextEnergy > maxEnergy[nextMask][nextCell]) {
                            maxEnergy[nextMask][nextCell] = nextEnergy;
                            q[tail++] = (nextMask << 15) | (nextCell << 6) | nextEnergy;
                        }
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}
