class CountRotationsWithExactlyKEqualAdjacentPairs {

    public int countRotations(String s, int k) {
        int score = 0;
        int ans = 0;

        // Calculate the score of the original string
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                score++;
            }
        }

        // Check the original rotation
        if (score == k) {
            ans++;
        }

        // Process the remaining rotations
        for (int i = 0; i < s.length() - 1; i++) {

            // Remove the old adjacent pair
            if (s.charAt(i) == s.charAt(i + 1)) {
                score--;
            }

            // Add the new adjacent pair created at the end
            int lastIndex = (s.length() - 1 + i) % s.length();

            if (s.charAt(i) == s.charAt(lastIndex)) {
                score++;
            }

            // Check if the current rotation has score k
            if (score == k) {
                ans++;
            }
        }

        return ans;
    }
}