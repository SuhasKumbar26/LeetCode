import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeIntervals {
    public int[][] merge(int[][] intervals) {

        // Sort based on start
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Temporary result list
        List<int[]> res = new ArrayList<>();

        // First interval is the current interval
        res.add(intervals[0]);

        for (int[] cur : intervals) {

            // Get the previous interval to compare for overlap
            int[] pre = res.get(res.size() - 1);

            // Current interval overlaps with previous interval
            if (cur[0] <= pre[1]) {

                // Keep the farthest end
                pre[1] = Math.max(pre[1], cur[1]);

            } else {

                // No overlap, add as a new interval
                res.add(cur);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}