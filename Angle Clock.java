class Solution {
    public double angleClock(int hour, int minutes) {
        double minuteAngle = minutes * 6.0;                 // 360 / 60
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5; // 360 / 12 + minute movement
        
        double diff = Math.abs(hourAngle - minuteAngle);
        return Math.min(diff, 360.0 - diff);
    }
}
