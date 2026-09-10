class CountCommasInRangeII {
    public long countCommas(long n) {
        long comma = 1, total = 0;
        long start = 1000;

        while (start <= n) {
            // Find end number
            long end = start * 1000 - 1;

            // If n is less than the end number, update end
            if (end > n) {
                end = n;
            }

            // Count numbers in that range
            long num = end - start + 1;

            // Add total commas
            total += num * comma;

            // Move to the next range
            start *= 1000;

            // Next range has one additional comma
            comma++;
        }

        return total;
    }
}